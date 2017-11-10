package com.sky.cloud.network;

import com.sky.cloud.utils.LogUtils;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

/**
 * retrofit日志拦截器
 * Created by liujian on 2017/11/10.
 */

public class HttpLoggingInterceptor implements Interceptor
{
    private static final Charset UTF8 = Charset.forName("UTF-8");
    final static int NONE = 0;
    final static int HEADERS = 1;
    final static int BODY = 2;
    private int level = 0;
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        if (level == NONE)
        {
            return chain.proceed(request);
        }

        boolean logBody = level == BODY;
        boolean logHeaders = logBody || level == HEADERS;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
        if (!logHeaders && hasRequestBody)
        {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        LogUtils.d(requestStartMessage);

        if (logHeaders)
        {
            if (hasRequestBody)
            {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                if (requestBody.contentType() != null)
                {
                    LogUtils.d("Content-Type: " + requestBody.contentType());
                }
                if (requestBody.contentLength() != -1)
                {
                    LogUtils.d("Content-Length: " + requestBody.contentLength());
                }
            }

            Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++)
            {
                String name = headers.name(i);
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name))
                {
                    LogUtils.d(name + ": " + headers.value(i));
                }
            }

            if (!logBody || !hasRequestBody)
            {
                LogUtils.d("--> END " + request.method());
            }
            else if (bodyEncoded(request.headers()))
            {
                LogUtils.d("--> END " + request.method() + " (encoded body omitted)");
            }
            else
            {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null)
                {
                    charset = contentType.charset(UTF8);
                }

                LogUtils.d("");
                if (isPlaintext(buffer))
                {
                    LogUtils.d(buffer.readString(charset));
                    LogUtils.d("--> END " + request.method()
                            + " (" + requestBody.contentLength() + "-byte body)");
                }
                else
                {
                    LogUtils.d("--> END " + request.method() + " (binary "
                            + requestBody.contentLength() + "-byte body omitted)");
                }
            }
        }

        long startNs = System.nanoTime();
        Response response;
        try
        {
            response = chain.proceed(request);
        }
        catch (Exception e)
        {
            LogUtils.d("<-- HTTP FAILED: " + e);
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        LogUtils.d("<-- " + response.code() + ' ' + response.message() + ' '
                + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", "
                + bodySize + " body" : "") + ')');

        if (logHeaders)
        {
            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++)
            {
                LogUtils.d(headers.name(i) + ": " + headers.value(i));
            }

            if (!logBody || !HttpHeaders.hasBody(response))
            {
                LogUtils.d("<-- END HTTP");
            }
            else if (bodyEncoded(response.headers()))
            {
                LogUtils.d("<-- END HTTP (encoded body omitted)");
            }
            else
            {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null)
                {
                    charset = contentType.charset(UTF8);
                }

                if (!isPlaintext(buffer))
                {
                    LogUtils.d("");
                    LogUtils.d("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
                    return response;
                }

                if (contentLength != 0)
                {
                    LogUtils.d("");
                    LogUtils.d(buffer.clone().readString(charset));
                }

                LogUtils.d("<-- END HTTP (" + buffer.size() + "-byte body)");
            }
        }

        return response;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer)
    {
        try
        {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++)
            {
                if (prefix.exhausted())
                {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint))
                {
                    return false;
                }
            }
            return true;
        }
        catch (EOFException e)
        {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers)
    {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
}

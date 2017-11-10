package com.sky.cloud.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.sky.cloud.R;
import com.sky.cloud.activity.user.LoginActivity;
import com.sky.cloud.base.BaseActivity;


public class MainActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    /**
     *
     * @param view
     */
    public void viewClick(View view)
    {
        startActivity(new Intent(this, LoginActivity.class));
    }
}

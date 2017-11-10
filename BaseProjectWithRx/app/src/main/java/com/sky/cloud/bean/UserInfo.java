package com.sky.cloud.bean;

/**
 * Created by liujian on 2017/11/9.
 */

public class UserInfo {

    /**
     * userInfo : {"userId":1,"userName":"sky","userPassword":"123","userSex":"男","userPhone":"15001883687","userAddress":"上海市闵行区浦江镇","userAge":23,"userIdCard":"","userNickName":"sky","userAccountMoney":0,"userScore":0,"userEmail":"1191682968@qq.com","userNo":"22","roleNum":"1501832905184","roleName":"roleOne","userBirthday":"19920709"}
     *
     */

    /**
     * userId : 1
     * userName : sky
     * userPassword : 123
     * userSex : 男
     * userPhone : 15001883687
     * userAddress : 上海市闵行区浦江镇
     * userAge : 23
     * userIdCard :
     * userNickName : sky
     * userAccountMoney : 0.0
     * userScore : 0
     * userEmail : 1191682968@qq.com
     * userNo : 22
     * roleNum : 1501832905184
     * roleName : roleOne
     * userBirthday : 19920709
     */

    private int userId;
    private String userName;
    private String userPassword;
    private String userSex;
    private String userPhone;
    private String userAddress;
    private int userAge;
    private String userIdCard;
    private String userNickName;
    private double userAccountMoney;
    private int userScore;
    private String userEmail;
    private String userNo;
    private String roleNum;
    private String roleName;
    private String userBirthday;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public double getUserAccountMoney() {
        return userAccountMoney;
    }

    public void setUserAccountMoney(double userAccountMoney) {
        this.userAccountMoney = userAccountMoney;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(String roleNum) {
        this.roleNum = roleNum;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userAge=" + userAge +
                ", userIdCard='" + userIdCard + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", userAccountMoney=" + userAccountMoney +
                ", userScore=" + userScore +
                ", userEmail='" + userEmail + '\'' +
                ", userNo='" + userNo + '\'' +
                ", roleNum='" + roleNum + '\'' +
                ", roleName='" + roleName + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                '}';
    }
}

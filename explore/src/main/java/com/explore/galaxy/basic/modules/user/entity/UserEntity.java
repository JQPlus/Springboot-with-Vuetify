package com.explore.galaxy.basic.modules.user.entity;

import java.util.Date;

public class UserEntity {
    private String userID;

    private String userName;

    private String account;

    private String password;

    private Integer phone;

    private String email;

    private String createdUserID;

    private Date createdTime;

    private String updatedUserID;

    private Date updatedTime;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID == null ? null : userID.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCreatedUserID() {
        return createdUserID;
    }

    public void setCreatedUserID(String createdUserID) {
        this.createdUserID = createdUserID == null ? null : createdUserID.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedUserID() {
        return updatedUserID;
    }

    public void setUpdatedUserID(String updatedUserID) {
        this.updatedUserID = updatedUserID == null ? null : updatedUserID.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
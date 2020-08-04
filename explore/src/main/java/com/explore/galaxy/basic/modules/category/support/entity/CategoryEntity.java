package com.explore.galaxy.basic.modules.category.support.entity;

import java.util.Date;

public class CategoryEntity {
    private String categoryID;

    private String categoryName;

    private String categoryCode;

    private String categoryTypeID;

    private String createdUserID;

    private Date createdTime;

    private String updatedUserID;

    private Date updatedTime;

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID == null ? null : categoryID.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getCategoryTypeID() {
        return categoryTypeID;
    }

    public void setCategoryTypeID(String categoryTypeID) {
        this.categoryTypeID = categoryTypeID == null ? null : categoryTypeID.trim();
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
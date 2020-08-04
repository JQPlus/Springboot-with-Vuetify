package com.explore.galaxy.basic.modules.categoryType.support.entity;

import java.util.Date;

public class CategoryTypeEntity {
    private String categoryTypeId;

    private String categoryTypeName;

    private String categoryTypeCode;

    private String createdUserId;

    private Date createdTime;

    private String updatedUserId;

    private Date updatedTime;

    public String getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(String categoryTypeId) {
        this.categoryTypeId = categoryTypeId == null ? null : categoryTypeId.trim();
    }

    public String getCaetgoryTypeName() {
        return categoryTypeName;
    }

    public void setCaetgoryTypeName(String categoryTypeName) {
        this.categoryTypeName = categoryTypeName == null ? null : categoryTypeName.trim();
    }

    public String getCategoryTypeCode() {
        return categoryTypeCode;
    }

    public void setCategoryTypeCode(String categoryTypeCode) {
        this.categoryTypeCode = categoryTypeCode == null ? null : categoryTypeCode.trim();
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId == null ? null : createdUserId.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId == null ? null : updatedUserId.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
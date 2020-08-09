package com.explore.galaxy.basic.modules.notebook.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;


public class NotebookEntity {
    private String notebookID;

    private BigDecimal specificExpense;

    private Date expenseDate;

    private String expenseType;

    private String createdUserID;

    private Date createdTime;

    private Date updatedTime;

    private String remark;

    //扩展字段
    private String expenseTypeName;

    public String getNotebookID() {
        return notebookID;
    }

    public void setNotebookID(String notebookID) {
        this.notebookID = notebookID == null ? null : notebookID.trim();
    }

    public BigDecimal getSpecificExpense() {
        return specificExpense;
    }

    public void setSpecificExpense(BigDecimal specificExpense) {
        this.specificExpense = specificExpense;
    }


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType == null ? null : expenseType.trim();
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

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExpenseTypeName() {
        return expenseTypeName;
    }

    public void setExpenseTypeName(String expenseTypeName) {
        this.expenseTypeName = expenseTypeName;
    }
}
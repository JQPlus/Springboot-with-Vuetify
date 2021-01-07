package com.explore.galaxy.basic.modules.notebook.service;

import com.explore.galaxy.basic.modules.notebook.entity.NotebookEntity;
import com.explore.galaxy.basic.utils.responseBody.IBasicResponse;

import java.math.BigDecimal;
import java.util.List;

public interface INotebookService {
    int deleteByPrimaryKey(String notebookID);

    IBasicResponse insertSelective(NotebookEntity record);

    NotebookEntity selectByPrimaryKey(String notebookID);

    IBasicResponse updateByPrimaryKeySelective(NotebookEntity record);

    List<NotebookEntity> retrieveDailyExpense(String strYearMonth);

    List<NotebookEntity> retrieveByExpenseDate(String expenseDate);

    List<BigDecimal> retrieveByExpenseDateRange(String startDate, String endDate);

    BigDecimal getMonthExpense(String strYearMonth);

    BigDecimal getDayExpense(String strDay);
}

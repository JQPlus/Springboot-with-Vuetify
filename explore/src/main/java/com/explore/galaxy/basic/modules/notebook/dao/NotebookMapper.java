package com.explore.galaxy.basic.modules.notebook.dao;

import com.explore.galaxy.basic.modules.notebook.entity.NotebookEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface NotebookMapper {
    int deleteByPrimaryKey(String notebookID);

    int insert(NotebookEntity record);

    int insertSelective(NotebookEntity record);

    NotebookEntity selectByPrimaryKey(String notebookID);

    int updateByPrimaryKeySelective(NotebookEntity record);

    int updateByPrimaryKey(NotebookEntity record);

    List<NotebookEntity> retrieveDailyExpense(String strYearMonth);

    List<NotebookEntity> retrieveByExpenseDate(String expenseDate);

    List<BigDecimal> retrieveByExpenseDateRange(String startDate, String endDate);

    BigDecimal getMonthExpense(String strYearMonth);

    BigDecimal getDayExpense(String strDay);
}
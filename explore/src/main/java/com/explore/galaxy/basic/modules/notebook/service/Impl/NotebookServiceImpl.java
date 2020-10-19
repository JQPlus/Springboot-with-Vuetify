package com.explore.galaxy.basic.modules.notebook.service.Impl;

import com.explore.galaxy.basic.modules.notebook.dao.NotebookMapper;
import com.explore.galaxy.basic.modules.notebook.service.INotebookService;
import com.explore.galaxy.basic.modules.notebook.entity.NotebookEntity;
import com.explore.galaxy.basic.utils.uuid.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class NotebookServiceImpl implements INotebookService {
    @Autowired
    private NotebookMapper notebookMapper;

    @Override
    public int deleteByPrimaryKey(String notebookID) {
        if (notebookMapper.selectByPrimaryKey(notebookID) != null) {
            return notebookMapper.deleteByPrimaryKey(notebookID);
        }
        return 0;
    }

    @Override
    public int insertSelective(NotebookEntity record) {
        record.setNotebookID(UUIDUtils.init());
        record.setCreatedTime(new Date());
        return notebookMapper.insertSelective(record);
    }

    @Override
    public NotebookEntity selectByPrimaryKey(String notebookID) {
        return notebookMapper.selectByPrimaryKey(notebookID);
    }

    @Override
    public int updateByPrimaryKeySelective(NotebookEntity record) {
        record.setUpdatedTime(new Date());
        return notebookMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<NotebookEntity> retrieveDailyExpense(String strYearMonth) {
        return notebookMapper.retrieveDailyExpense(strYearMonth);
    }

    @Override
    public List<NotebookEntity> retrieveByExpenseDate(String expenseDate) {
        return notebookMapper.retrieveByExpenseDate(expenseDate);
    }

    @Override
    public List<BigDecimal> retrieveByExpenseDateRange(String startDate, String endDate) {
        return notebookMapper.retrieveByExpenseDateRange(startDate, endDate);
    }

    @Override
    public BigDecimal getMonthExpense(String strYearMonth) {
        return notebookMapper.getMonthExpense(strYearMonth);
    }

    @Override
    public BigDecimal getDayExpense(String strDay) {
        return notebookMapper.getDayExpense(strDay);
    }
}

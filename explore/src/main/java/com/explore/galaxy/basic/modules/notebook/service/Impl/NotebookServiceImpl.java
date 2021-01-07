package com.explore.galaxy.basic.modules.notebook.service.Impl;

import com.explore.galaxy.basic.configuration.redisConfig.CacheNames;
import com.explore.galaxy.basic.modules.notebook.dao.NotebookMapper;
import com.explore.galaxy.basic.modules.notebook.entity.NotebookEntity;
import com.explore.galaxy.basic.modules.notebook.service.INotebookService;
import com.explore.galaxy.basic.utils.responseBody.DataResponse;
import com.explore.galaxy.basic.utils.responseBody.IBasicResponse;
import com.explore.galaxy.basic.utils.responseBody.EResponseType;
import com.explore.galaxy.basic.utils.uuid.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = CacheNames.NOTEBOOK_CACHE)
public class NotebookServiceImpl implements INotebookService {
    @Autowired
    private NotebookMapper notebookMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(String notebookID) {
        try {
            if (notebookMapper.selectByPrimaryKey(notebookID) != null) {
                return notebookMapper.deleteByPrimaryKey(notebookID);
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return 0;
    }

    @Override
    public IBasicResponse insertSelective(NotebookEntity record) {
        IBasicResponse response = new DataResponse();
        try {
            record.setNotebookID(UUIDUtils.init());
            record.setCreatedTime(new Date());
            if (notebookMapper.insertSelective(record) == 1) {
                response.setData(record);
                response.setResponseType(EResponseType.success);
                return response;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    public NotebookEntity selectByPrimaryKey(String notebookID) {
        return notebookMapper.selectByPrimaryKey(notebookID);
    }

    @Override
    @CachePut(key = "'updateByPrimaryKeySelective-' + #record.notebookID",
            condition = "#result != null")
    public IBasicResponse updateByPrimaryKeySelective(NotebookEntity record) {
        IBasicResponse response = new DataResponse();
        try {
            record.setUpdatedTime(new Date());
            if (notebookMapper.updateByPrimaryKeySelective(record) == 1) {
                response.setData(record);
                response.setResponseType(EResponseType.success);
                return response;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Override
    @Cacheable(key = "'retrieveDailyExpense-' + #strYearMonth",
            unless = "#result == null || #result.size() <=0")
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

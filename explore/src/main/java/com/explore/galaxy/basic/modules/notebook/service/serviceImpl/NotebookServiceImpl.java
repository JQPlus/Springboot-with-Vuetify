package com.explore.galaxy.basic.modules.notebook.service.serviceImpl;

import com.explore.galaxy.basic.modules.notebook.dao.NotebookMapper;
import com.explore.galaxy.basic.modules.notebook.service.INotebookService;
import com.explore.galaxy.basic.modules.notebook.support.entity.NotebookEntity;
import com.explore.galaxy.basic.utils.UUIDUtils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotebookServiceImpl implements INotebookService {
    @Autowired
    private NotebookMapper notebookMapper;

    @Override
    public int deleteByPrimaryKey(String notebookID) {
        return notebookMapper.deleteByPrimaryKey(notebookID);
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
    public List<NotebookEntity> retrieveAllNotebook() {
        return notebookMapper.retrieveAllNotebook();
    }

    @Override
    public List<NotebookEntity> retrieveByExpenseDate(String expenseDate) {
        return notebookMapper.retrieveByExpenseDate(expenseDate);
    }

    @Override
    public List<Integer> retrieveByExpenseDateRange(String startDate, String endDate) {
        return notebookMapper.retrieveByExpenseDateRange(startDate, endDate);
    }
}

package com.explore.galaxy.basic.modules.notebook.dao;

import com.explore.galaxy.basic.modules.notebook.support.entity.NotebookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotebookMapper {
    int deleteByPrimaryKey(String notebookID);

    int insert(NotebookEntity record);

    int insertSelective(NotebookEntity record);

    NotebookEntity selectByPrimaryKey(String notebookID);

    int updateByPrimaryKeySelective(NotebookEntity record);

    int updateByPrimaryKey(NotebookEntity record);

    List<NotebookEntity> retrieveAllNotebook();

    List<NotebookEntity> retrieveByExpenseDate(String expenseDate);
}
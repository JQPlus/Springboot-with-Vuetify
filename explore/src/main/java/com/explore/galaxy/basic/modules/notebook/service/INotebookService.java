package com.explore.galaxy.basic.modules.notebook.service;

import com.explore.galaxy.basic.modules.notebook.support.entity.NotebookEntity;

import java.util.List;

public interface INotebookService {
    int deleteByPrimaryKey(String notebookID);

    int insertSelective(NotebookEntity record);

    NotebookEntity selectByPrimaryKey(String notebookID);

    int updateByPrimaryKeySelective(NotebookEntity record);

    List<NotebookEntity> retrieveAllNotebook();

    List<NotebookEntity> retrieveByExpenseDate(String expenseDate);
}

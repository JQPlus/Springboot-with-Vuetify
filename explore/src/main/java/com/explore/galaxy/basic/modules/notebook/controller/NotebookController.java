package com.explore.galaxy.basic.modules.notebook.controller;

import com.explore.galaxy.basic.modules.notebook.service.INotebookService;
import com.explore.galaxy.basic.modules.notebook.entity.NotebookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/notebook")
public class NotebookController {
    @Autowired
    private INotebookService iNotebookService;

    @DeleteMapping("delete")
    public int deleteByPrimaryKey(@RequestBody String notebookID) {
        return iNotebookService.deleteByPrimaryKey(notebookID);
    }

    @PostMapping("insert")
    public int insertSelective(@RequestBody NotebookEntity record) {
        return iNotebookService.insertSelective(record);
    }

    @PostMapping("update")
    public int updateByPrimaryKeySelective(@RequestBody NotebookEntity record) {
        return iNotebookService.updateByPrimaryKeySelective(record);
    }

    @GetMapping("retrieveAllNotebook")
    public List<NotebookEntity> retrieveAllNotebook() {
        return iNotebookService.retrieveAllNotebook();
    }

    @PostMapping("retrieveByExpenseDate")
    public List<NotebookEntity> retrieveByExpenseDate(@RequestBody String expenseDate) {
        return iNotebookService.retrieveByExpenseDate(expenseDate);
    }

    @PostMapping("retrieveByExpenseDateRange")
    List<BigDecimal> retrieveByExpenseDateRange(@RequestBody List<String> currentDate) {
        String startDate = currentDate.get(0);
        String endDate = currentDate.get(1);
        return iNotebookService.retrieveByExpenseDateRange(startDate, endDate);
    }
}
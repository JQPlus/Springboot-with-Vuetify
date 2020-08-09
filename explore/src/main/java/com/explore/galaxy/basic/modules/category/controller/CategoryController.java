package com.explore.galaxy.basic.modules.category.controller;

import com.explore.galaxy.basic.modules.category.service.ICategoryService;
import com.explore.galaxy.basic.modules.category.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("getCategoryByTypeCode")
    public List<CategoryEntity> getCategoryByTypeCode(@RequestParam String categoryCode) {
        return iCategoryService.getCategoryByTypeCode(categoryCode);
    }
}

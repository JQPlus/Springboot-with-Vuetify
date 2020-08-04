package com.explore.galaxy.basic.modules.category.service.impl;

import com.explore.galaxy.basic.modules.category.dao.CategoryMapper;
import com.explore.galaxy.basic.modules.category.service.ICategoryService;
import com.explore.galaxy.basic.modules.category.support.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<CategoryEntity> getCategoryByTypeCode(String categoryCode) {
        return categoryMapper.getCategoryByTypeCode(categoryCode);
    }
}

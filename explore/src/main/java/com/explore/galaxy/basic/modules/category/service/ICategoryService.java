package com.explore.galaxy.basic.modules.category.service;

import com.explore.galaxy.basic.modules.category.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {
   List<CategoryEntity> getCategoryByTypeCode(String categoryCode);
}

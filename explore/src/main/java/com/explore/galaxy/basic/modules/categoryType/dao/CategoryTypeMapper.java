package com.explore.galaxy.basic.modules.categoryType.dao;

import com.explore.galaxy.basic.modules.categoryType.support.entity.CategoryTypeEntity;

public interface CategoryTypeMapper {
    int deleteByPrimaryKey(String categoryTypeId);

    int insert(CategoryTypeEntity record);

    int insertSelective(CategoryTypeEntity record);

    CategoryTypeEntity selectByPrimaryKey(String categoryTypeId);

    int updateByPrimaryKeySelective(CategoryTypeEntity record);

    int updateByPrimaryKey(CategoryTypeEntity record);
}
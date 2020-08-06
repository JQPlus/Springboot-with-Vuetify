package com.explore.galaxy.basic.modules.category.dao;

import com.explore.galaxy.basic.modules.category.support.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(String categoryID);

    int insert(CategoryEntity record);

    int insertSelective(CategoryEntity record);

    CategoryEntity selectByPrimaryKey(String categoryID);

    int updateByPrimaryKeySelective(CategoryEntity record);

    int updateByPrimaryKey(CategoryEntity record);

    List<CategoryEntity> getCategoryByTypeCode(String categoryCode);

}
package com.explore.galaxy.basic.modules.user.dao;

import com.explore.galaxy.basic.modules.user.entity.UserEntity;

public interface UserMapper {
    int deleteByPrimaryKey(String userID);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(String userID);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);
}
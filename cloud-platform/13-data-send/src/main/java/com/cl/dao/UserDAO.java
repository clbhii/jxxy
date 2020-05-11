package com.cl.dao;


import com.cl.model.UserDO;

import java.util.List;
import java.util.Map;

public interface UserDAO {

    int insert(UserDO record);

    UserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKey(UserDO record);

    int deleteByPrimaryKey(Long id);

    List<UserDO> selectList(Map<String, Object> map);

    int countList(Map<String, Object> map);

    UserDO selectByUserNameAndPassword(Map<String, Object> map);

}
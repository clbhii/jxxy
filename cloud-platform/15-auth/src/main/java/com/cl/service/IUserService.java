package com.cl.service;

import com.cl.common.Page;
import com.cl.model.UserDO;

public interface IUserService {

    int insert(UserDO record);

    UserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKey(UserDO record);

    int deleteByPrimaryKey(Long id);

    Page<UserDO> page(String userName, Integer curPage, Integer pageSize);

    UserDO selectByUserNameAndPassword(String userName, String password);
}

package com.cl.controller;

import com.cl.dao.UserDAO;
import com.cl.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/insert")
    public String insert() {
        UserDO userDO = new UserDO();
        userDO.setName("张三");
        userDO.setAge(12);
        userDAO.insert(userDO);
        return "ok";
    }
}

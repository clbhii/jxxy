package com.cl.controller;

import com.cl.common.Contants;
import com.cl.common.Page;
import com.cl.common.Result;
import com.cl.common.ResultSupport;
import com.cl.model.UserDO;
import com.cl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * by cl at 2020/3/28 0028
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/insert")
    @ResponseBody
    public Result<Void> insert(@RequestParam(value = "userName", required = true) String userName
                            , @RequestParam(value = "password", required = false, defaultValue = "123456") String password
                            , @RequestParam(value = "email", required = true) String email) {
        UserDO userDO = new UserDO();
        userDO.setUserName(userName);
        userDO.setPassword(password);
        userDO.setEmail(email);
        userService.insert(userDO);
        return new ResultSupport<Void>(true);
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result<Void> update(@RequestParam(value = "id", required = true) Long id
                            ,@RequestParam(value = "userName", required = true) String userName
                            , @RequestParam(value = "email", required = true) String email) {
        UserDO userDO = userService.selectByPrimaryKey(id);
        userDO.setUserName(userName);
        userDO.setEmail(email);
        userService.updateByPrimaryKey(userDO);
        return new ResultSupport<Void>(true);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Void> update(@RequestParam(value = "id", required = true) Long id) {
        userService.deleteByPrimaryKey(id);
        return new ResultSupport<Void>(true);
    }

    @RequestMapping("/page")
    @ResponseBody
    public Map<String, Object> page(@RequestParam(value = "userName", required = false) String userName,
                                          @RequestParam(value = "page", required = false, defaultValue = "1") int curPage,
                                          @RequestParam(value = "rows", required = false, defaultValue = "10") int pageSize) {
        Page<UserDO> pageUser = userService.page(userName, curPage, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageUser.getTotalNumber());
        map.put("rows", pageUser.getItems());
        return map;
    }
    @RequestMapping("/login")
    @ResponseBody
    public Result<Void> login(@RequestParam(value = "userName", required = true) String userName
                       ,@RequestParam(value = "password", required = true) String password
                       , HttpServletRequest request){
        UserDO userDO = userService.selectByUserNameAndPassword(userName, password);
        Result<Void> result = new ResultSupport<Void>(true);
        if(userDO == null) {
            result.setSuccess(false);
            result.setMessage("用户名或密码有误");
        }else {
            HttpSession session = request.getSession();
            session.setAttribute(Contants.USER_SESSION, userDO);
//            session.setMaxInactiveInterval(30);
        }
        return result;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result<Void> logout(HttpServletRequest request){

        HttpSession session = request.getSession();
        session.setAttribute(Contants.USER_SESSION, null);
        Result<Void> result = new ResultSupport<Void>(true);
        return result;
    }
}

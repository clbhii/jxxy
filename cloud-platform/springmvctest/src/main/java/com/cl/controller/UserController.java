package com.cl.controller;


import com.cl.dao.UserDAO;
import com.cl.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserDAO userDAO;

	@RequestMapping("/insert")
	public String insertUser() {
		System.out.println("hello 世界");
		UserDO userDO = new UserDO();
		userDO.setName("张三");
		userDO.setAge(11);
		userDAO.insert(userDO);
		return "hello";
	}

	@RequestMapping("/show")
	public ModelAndView show(){
		UserDO userDO = new UserDO();
		userDO.setName("cl");
		userDO.setAge(20);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("show");
		modelAndView.addObject("userInfo", userDO);
		return modelAndView;
	}

	@RequestMapping("/getUser")
	@ResponseBody
	public UserDTO getUser() {
		UserDTO userDO = new UserDTO();
		userDO.setName("cl");
		userDO.setAge(20);
		return userDO;
	}
	@RequestMapping("/get/{id}")
	public String getUserId(@PathVariable("id") String id){
		System.out.println(id);
		return "hello";
	}

	@RequestMapping("/insertUser")
	public String insertUser(String name, int age) {
		System.out.println("hello 世界");
		UserDO userDO = new UserDO();
		userDO.setName(name);
		userDO.setAge(age);
		userDAO.insert(userDO);
		return "hello";
	}

}


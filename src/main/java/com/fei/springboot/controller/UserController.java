package com.fei.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fei.springboot.domain.User;
import com.fei.springboot.service.UserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}
	/**
	 * 测试插入
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String add(String id,String userName){
		User u = new User();
		u.setId(id);
		u.setUserName(userName);
		this.userService.insertUser(u);
		return u.getId()+"    " + u.getUserName();
	}
	/**
	 * 测试分页插件
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public String queryPage(){
		PageInfo<User> page = this.userService.queryPage("tes", 1, 2);
		System.out.println("总页数=" + page.getPages());
		System.out.println("总记录数=" + page.getTotal()) ;
		for(User u : page.getList()){
			System.out.println(u.getId() + " \t " + u.getUserName());
		}
		return "success";
	}
	
}

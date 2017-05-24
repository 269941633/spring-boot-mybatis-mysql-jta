package com.fei.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fei.springboot.dao.db01.UserMapper;
import com.fei.springboot.dao.db02.Db02UserMapper;
import com.fei.springboot.domain.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional(propagation=Propagation.REQUIRED,readOnly=true,rollbackFor=Exception.class)
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private Db02UserMapper db02UserMapper;
	
	//注意：方法的@Transactional会覆盖类上面声明的事务
	//Propagation.REQUIRED ：有事务就处于当前事务中，没事务就创建一个事务
	//isolation=Isolation.DEFAULT：事务数据库的默认隔离级别
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
	public void insertUser(User u){
		this.userMapper.insert(u);
		this.db02UserMapper.insert(u);
		//如果类上面没有@Transactional,方法上也没有，哪怕throw new RuntimeException,数据库也会成功插入数据
	//	throw new RuntimeException("测试插入事务");
	}
	
	public PageInfo<User> queryPage(String userName,int pageNum,int pageSize){
		Page<User> page = PageHelper.startPage(pageNum, pageSize);
		//PageHelper会自动拦截到下面这查询sql
		this.userMapper.query(userName);
		return page.toPageInfo();
	}
	
	

}

package com.fei.springboot.dao.db01;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fei.springboot.domain.User;

@Mapper
public interface UserMapper {

	@Insert("insert sys_user(id,user_name) values(#{id},#{userName})")
	void insert(User u);
	
	//注：方法名和要UserMapper.xml中的id一致
	List<User> query(@Param("userName")String userName);
	

}

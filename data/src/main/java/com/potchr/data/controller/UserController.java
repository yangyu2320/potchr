package com.potchr.data.controller;

import com.potchr.data.user.entity.ErpUser;
import com.potchr.data.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Stream;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/8/14 18:42</p>
 */
@RestController
@RequestMapping(value = "/user")
public class UserController
{
	@Autowired
	private UserService userService;

	@GetMapping("/list")
	@ResponseBody
	public String getUser(HttpServletRequest request)
	{
		return userService.getAllUsers().toString();
	}

	@PostMapping("/add")
	public ErpUser addUser(@RequestBody ErpUser user)
	{
		return userService.save(user);
	}
}

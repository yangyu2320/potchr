package com.potchr.data.user.service;

import com.potchr.data.user.entity.ErpUser;
import com.potchr.data.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/8/13 14:30</p>
 */
@Service
public class UserService
{
	@Autowired
	private UserRepository userRepository;

//	@Cacheable(cacheNames = "User", key = "'User_ALL'")
	public List<ErpUser> getAllUsers()
	{
		return userRepository.queryCache();
	}

	@Cacheable(cacheNames = "User", key = "#id")
	public ErpUser queryById(Integer id)
	{
		return userRepository.findById(id).orElse(null);
	}

	@Caching(evict = { @CacheEvict(cacheNames = "User", key = "'User_ALL'"), @CacheEvict(cacheNames = "User", key = "#user.getId()") })
	public ErpUser save(ErpUser user)
	{
		return userRepository.save(user);
	}
}

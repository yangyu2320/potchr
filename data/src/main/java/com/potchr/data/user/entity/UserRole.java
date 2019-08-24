package com.potchr.data.user.entity;

import javax.persistence.*;
import java.io.Serializable;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/8/9 15:13</p>
 */
@Entity
public class UserRole implements Serializable
{
	private static final long serialVersionUID = -8770812027714795662L;
	@Id
	@Column(nullable = false)
	private Integer id;
	@Column(nullable = false)
	private Integer userId;
	@Column(nullable = false)
	private String  roleCode;

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public String getRoleCode()
	{
		return roleCode;
	}

	public void setRoleCode(String roleCode)
	{
		this.roleCode = roleCode;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return "{" + "id:" + id + ", userId:" + userId + ", roleCode:'" + roleCode + '\'' + '}';
	}
}

package com.potchr.data.user.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
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
 * <p>创建日期：2019/8/9 15:12</p>
 */
@Entity
@Table(name = "user")
public class ErpUser extends User
{
	private static final long serialVersionUID = -8537313148471993494L;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private List<UserRole> roles;

	public List<UserRole> getRoles()
	{
		return roles == null ? new ArrayList<>() : roles;
	}

	public void setRoles(List<UserRole> roles)
	{
		this.roles = roles;
	}

	@Override
	public String toString()
	{
		return "{" + "roles:" + getRoles() + "}, " + super.toString();
	}
}

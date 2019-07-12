package com.potchr.data.user.entity;

import com.potchr.data.entity.Area;

import javax.persistence.*;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/7/11 11:48</p>
 */
@Entity
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(length = 18, nullable = false)
	private String  loginName;
	@Column(length = 20, nullable = false)
	private String  firstName;
	@Column(length = 20, nullable = false)
	private String  lastName;
	@Column(length = 16, nullable = false)
	private String  password;
	@Column(length = 11)
	private String	mobile;
	@Embedded
	private Area    area;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public Area getArea()
	{
		return area;
	}

	public void setArea(Area area)
	{
		this.area = area;
	}

	@Override
	public String toString()
	{
		return "User{" + "id=" + id + ", loginName='" + loginName + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", password='" + password + '\'' + ", area=" + area
				+ '}';
	}
}

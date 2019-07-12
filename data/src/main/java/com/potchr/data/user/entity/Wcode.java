package com.potchr.data.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/7/12 17:03</p>
 */
@Entity
public class Wcode
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer wcode;

	private String wname;

	public Integer getWcode()
	{
		return wcode;
	}

	public void setWcode(Integer wcode)
	{
		this.wcode = wcode;
	}

	public String getWname()
	{
		return wname;
	}

	public void setWname(String wname)
	{
		this.wname = wname;
	}

	@Override
	public String toString()
	{
		return "Wcode{" + "wcode='" + wcode + '\'' + ", wname='" + wname + '\'' + '}';
	}
}

package com.potchr.data.bcode.entity;

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
 * <p>创建日期：2019/8/9 16:46</p>
 */
@Entity
public class Bcode
{
	@Id
	private String bcode;

	private String bname;

	public String getBcode()
	{
		return bcode;
	}

	public void setBcode(String bcode)
	{
		this.bcode = bcode;
	}

	public String getBname()
	{
		return bname;
	}

	public void setBname(String bname)
	{
		this.bname = bname;
	}

	@Override
	public String toString()
	{
		return "{" + "bcode:'" + bcode + '\'' + ", bname:'" + bname + '\'' + '}';
	}
}

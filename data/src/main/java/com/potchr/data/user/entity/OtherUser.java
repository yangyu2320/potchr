package com.potchr.data.user.entity;

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
 * <p>创建日期：2019/8/13 10:50</p>
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "sheet_code", length = 20)
@Table(name = "user")
public class OtherUser
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(length = 18)
	private String  idCardNo;

	@Column(length = 20, nullable = false)
	private String sheetCode;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getIdCardNo()
	{
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo)
	{
		this.idCardNo = idCardNo;
	}

	@Override
	public String toString()
	{
		return "{" + "id:" + id + ", idCardNo:'" + idCardNo + '\'' + '}';
	}
}

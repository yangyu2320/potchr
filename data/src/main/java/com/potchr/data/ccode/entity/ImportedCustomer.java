package com.potchr.data.ccode.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/8/20 11:45</p>
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "imported_customer_test")
public class ImportedCustomer extends Customer
{
	private static final long serialVersionUID = -2343014398228460018L;
}

package com.potchr.authorization;

import java.util.Collection;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/8/15 16:10</p>JdbcTemplate
 */
public interface Limit
{
	String getField();

	Collection<String> getValues();
}

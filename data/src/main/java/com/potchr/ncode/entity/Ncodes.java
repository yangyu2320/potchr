package com.potchr.ncode.entity;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
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
 * <p>创建日期：2019/8/22 14:34</p>
 */
@XmlRootElement(name = "Ncodes")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ncodes")
public class Ncodes implements Serializable
{
	private static final long        serialVersionUID = -832350874034749660L;
	@XmlElement(name = "ncode")
	private              List<Ncode> ncodes;

	public List<Ncode> getNcodes()
	{
		return ncodes;
	}

	public void setNcodes(List<Ncode> ncodes)
	{
		this.ncodes = ncodes;
	}
}

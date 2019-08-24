package com.potchr.ncode.entity;

import javax.xml.bind.annotation.*;
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
 * <p>创建日期：2019/8/22 14:30</p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ncode")
public class Ncode implements Serializable
{
	private static final long   serialVersionUID = 7959318556984896760L;
	@XmlElement
	private              String ncode;
	@XmlElement
	private              String nname;
	@XmlElement
	private              String type;
	@XmlElement
	private              String pcode;
	@XmlElement
	private              String path;

	public String getNcode()
	{
		return ncode;
	}

	public void setNcode(String ncode)
	{
		this.ncode = ncode;
	}

	public String getNname()
	{
		return nname;
	}

	public void setNname(String nname)
	{
		this.nname = nname;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getPcode()
	{
		return pcode;
	}

	public void setPcode(String pcode)
	{
		this.pcode = pcode;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	@Override
	public String toString()
	{
		return "{" + "ncode:'" + ncode + '\'' + ", nname:'" + nname + '\'' + ", type:'" + type + '\'' + ", pcode:'" + pcode + '\'' + ", path:'" + path + '\'' + '}';
	}
}

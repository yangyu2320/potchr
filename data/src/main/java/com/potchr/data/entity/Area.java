package com.potchr.data.entity;

import javax.persistence.Embeddable;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/7/11 15:33</p>
 */
@Embeddable
public class Area
{
	private String country;
	private String province;
	private String city;
	private String county;

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCounty()
	{
		return county;
	}

	public void setCounty(String county)
	{
		this.county = county;
	}

	@Override
	public String toString()
	{
		return "Area{" + "country='" + country + '\'' + ", province='" + province + '\'' + ", city='" + city + '\'' + ", county='" + county + '\'' + '}';
	}
}

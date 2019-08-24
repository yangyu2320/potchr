package com.potchr.data.ccode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>
 * 其他说明：
 * </p>
 * <p>作者：yangy</p>
 * <p>审核：</p>
 * <p>重构：</p>
 * <p>创建日期：2019/8/22 14:07</p>
 */
@Entity
public class SnmCcode implements Serializable
{
	private static final long       serialVersionUID = -8153540424586986355L;
	@Id
	@Column(length = 32)
	private              String     ccode;
	@Column(length = 64)
	private              String     cname;
	@Column(length = 32)
	private              String     ecname;
	@Column(length = 16)
	private              String     industry;
	@Column(length = 16)
	private              String     nature;
	@Column(length = 16)
	private              String     source;
	@Column(length = 16)
	private              String     type;
	@Column(precision = 18, scale = 2)
	private              BigDecimal capital;
	@Column(length = 16)
	private              String     concern;
	@Column(length = 18)
	private              String     taxno;
	@Column(length = 64)
	private              String     ncode;
	@Column(length = 64)
	private              String     legalperson;
	@Column(length = 16)
	private              String     enterprisetype;
	private              Date       foundingtime;
	@Column(length = 500)
	private              String     homepage;
	@Column(length = 128)
	private              String     address;
	@Column(length = 128)
	private              String     telphone;
	@Column(length = 16)
	private              String     postcode;
	@Column(length = 16)
	private              String     puborpri;
	@Column(length = 32)
	private              String     bcode;
	@Column(length = 32)
	private              String     wcode;
	private              Date       optiontime;
	@Column(length = 8)
	private              String     status;
	@Column(length = 32)
	private              String     sheetcode;
	@Column(length = 256)
	private              String     remark;
	@Column(length = 32)
	private              String     vprepare;
	private              Date       predate;
	@Column(length = 32)
	private              String     modifier;
	private              Date       modifydate;
	@Column(length = 18)
	private              String     taxno_n7;
	@Column(length = 64)
	private              String     ccodegroup;
	private              Integer    isretains;
	@Column(length = 64)
	private              String     email;
	private              Integer    isdeletes;
	@Column(length = 256)
	private              String     busiscope;
	@Column(length = 32)
	private              String     cuicode;

	public String getCcode()
	{
		return ccode;
	}

	public void setCcode(String ccode)
	{
		this.ccode = ccode;
	}

	public String getCname()
	{
		return cname;
	}

	public void setCname(String cname)
	{
		this.cname = cname;
	}

	public String getEcname()
	{
		return ecname;
	}

	public void setEcname(String ecname)
	{
		this.ecname = ecname;
	}

	public String getIndustry()
	{
		return industry;
	}

	public void setIndustry(String industry)
	{
		this.industry = industry;
	}

	public String getNature()
	{
		return nature;
	}

	public void setNature(String nature)
	{
		this.nature = nature;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public BigDecimal getCapital()
	{
		return capital;
	}

	public void setCapital(BigDecimal capital)
	{
		this.capital = capital;
	}

	public String getConcern()
	{
		return concern;
	}

	public void setConcern(String concern)
	{
		this.concern = concern;
	}

	public String getTaxno()
	{
		return taxno;
	}

	public void setTaxno(String taxno)
	{
		this.taxno = taxno;
	}

	public String getNcode()
	{
		return ncode;
	}

	public void setNcode(String ncode)
	{
		this.ncode = ncode;
	}

	public String getLegalperson()
	{
		return legalperson;
	}

	public void setLegalperson(String legalperson)
	{
		this.legalperson = legalperson;
	}

	public String getEnterprisetype()
	{
		return enterprisetype;
	}

	public void setEnterprisetype(String enterprisetype)
	{
		this.enterprisetype = enterprisetype;
	}

	public Date getFoundingtime()
	{
		return foundingtime;
	}

	public void setFoundingtime(Date foundingtime)
	{
		this.foundingtime = foundingtime;
	}

	public String getHomepage()
	{
		return homepage;
	}

	public void setHomepage(String homepage)
	{
		this.homepage = homepage;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getTelphone()
	{
		return telphone;
	}

	public void setTelphone(String telphone)
	{
		this.telphone = telphone;
	}

	public String getPostcode()
	{
		return postcode;
	}

	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
	}

	public String getPuborpri()
	{
		return puborpri;
	}

	public void setPuborpri(String puborpri)
	{
		this.puborpri = puborpri;
	}

	public String getBcode()
	{
		return bcode;
	}

	public void setBcode(String bcode)
	{
		this.bcode = bcode;
	}

	public String getWcode()
	{
		return wcode;
	}

	public void setWcode(String wcode)
	{
		this.wcode = wcode;
	}

	public Date getOptiontime()
	{
		return optiontime;
	}

	public void setOptiontime(Date optiontime)
	{
		this.optiontime = optiontime;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getSheetcode()
	{
		return sheetcode;
	}

	public void setSheetcode(String sheetcode)
	{
		this.sheetcode = sheetcode;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getVprepare()
	{
		return vprepare;
	}

	public void setVprepare(String vprepare)
	{
		this.vprepare = vprepare;
	}

	public Date getPredate()
	{
		return predate;
	}

	public void setPredate(Date predate)
	{
		this.predate = predate;
	}

	public String getModifier()
	{
		return modifier;
	}

	public void setModifier(String modifier)
	{
		this.modifier = modifier;
	}

	public Date getModifydate()
	{
		return modifydate;
	}

	public void setModifydate(Date modifydate)
	{
		this.modifydate = modifydate;
	}

	public String getTaxno_n7()
	{
		return taxno_n7;
	}

	public void setTaxno_n7(String taxno_n7)
	{
		this.taxno_n7 = taxno_n7;
	}

	public String getCcodegroup()
	{
		return ccodegroup;
	}

	public void setCcodegroup(String ccodegroup)
	{
		this.ccodegroup = ccodegroup;
	}

	public Integer getIsretains()
	{
		return isretains;
	}

	public void setIsretains(Integer isretains)
	{
		this.isretains = isretains;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Integer getIsdeletes()
	{
		return isdeletes;
	}

	public void setIsdeletes(Integer isdeletes)
	{
		this.isdeletes = isdeletes;
	}

	public String getBusiscope()
	{
		return busiscope;
	}

	public void setBusiscope(String busiscope)
	{
		this.busiscope = busiscope;
	}

	public String getCuicode()
	{
		return cuicode;
	}

	public void setCuicode(String cuicode)
	{
		this.cuicode = cuicode;
	}
}

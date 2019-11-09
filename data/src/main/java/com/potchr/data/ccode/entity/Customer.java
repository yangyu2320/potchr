package com.potchr.data.ccode.entity;

import javax.persistence.*;
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
 * <p>创建日期：2019/8/20 11:26</p>
 */
@MappedSuperclass
public class Customer implements Serializable {
    private static final long serialVersionUID = -8173680981054642943L;
    @Id
    @GeneratedValue
    private Long customerId;
    private String customerName;
    private String status;
    private String legalPerson;
    private String registeredCapitalDesc;
    private String foundDate;
    private String province;
    private String city;
    private String phoneNumber;
    private String morePhoneNumber;
    private String email;
    private String creditCode;
    private String taxCode;
    private String regcode;
    private String orgCode;
    private String enterpriceType;
    private String industry;
    private String homePage;
    private String address;
    @Column(length = 3000)
    private String businessScope;
    private String customsCode;
    @Lob
    private String remark;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getRegisteredCapitalDesc() {
        return registeredCapitalDesc;
    }

    public void setRegisteredCapitalDesc(String registeredCapitalDesc) {
        this.registeredCapitalDesc = registeredCapitalDesc;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMorePhoneNumber() {
        return morePhoneNumber;
    }

    public void setMorePhoneNumber(String morePhoneNumber) {
        this.morePhoneNumber = morePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getRegcode() {
        return regcode;
    }

    public void setRegcode(String regcode) {
        this.regcode = regcode;
    }

    public String getEnterpriceType() {
        return enterpriceType;
    }

    public void setEnterpriceType(String enterpriceType) {
        this.enterpriceType = enterpriceType;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

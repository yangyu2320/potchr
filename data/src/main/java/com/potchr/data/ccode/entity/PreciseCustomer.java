package com.potchr.data.ccode.entity;

import javax.persistence.*;
import java.io.Serializable;
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
 * <p>创建日期：2019/8/20 11:47</p>
 */
@Entity
@Access(AccessType.PROPERTY)
public class PreciseCustomer extends Customer {
    private static final long serialVersionUID = -4365418500444856403L;
    private Date foundDateDate;
    private String ncode;

    public Date getFoundDateDate() {
        return foundDateDate;
    }

    public void setFoundDateDate(Date foundDateDate) {
        this.foundDateDate = foundDateDate;
    }

    public String getNcode() {
        return ncode;
    }

    public void setNcode(String ncode) {
        this.ncode = ncode;
    }
}

package com.potchr.data.user.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
 * <p>创建日期：2019/8/9 15:13</p>
 */
@Embeddable
public class UserRoleId implements Serializable {
    private static final long serialVersionUID = -8770812027714795662L;
    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private String roleCode;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}

package com.potchr.trade.order.pur.entity;

import com.potchr.trade.comm.ProductAttrs;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 采购订单产品项
 *
 * @author yangyu
 * @since 2021/7/6
 */
@MappedSuperclass
public abstract class PurOrderProductItem implements Serializable {

    @Serial
    private static final long serialVersionUID = -5863608933865162955L;

    //采购订单产品项ID
    @Id
    @Column(length = 32)
    protected String id;

    //序号
    protected Integer idx;

    //产品ID
    @Column(length = 32)
    protected String productId;

    //产品属性
    @Embedded
    protected ProductAttrs productAttrs;

    //订单数量
    @Column(precision = 14, scale = 6)
    protected BigDecimal orderQty;

    //订单数量
    @Column(precision = 14, scale = 6)
    protected BigDecimal qtyo;

    //订单数量折统计数量换算比例
    @Column(precision = 14, scale = 6)
    protected BigDecimal qosRate;

    //统计数量
    @Column(precision = 14, scale = 6)
    protected BigDecimal qtys;

    //数量单位
    @Column(length = 3)
    protected String qtyUnit;

    //单价
    @Column(precision = 17, scale = 8)
    protected BigDecimal upric;

    //金额
    @Column(precision = 11, scale = 2)
    protected BigDecimal amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ProductAttrs getProductAttrs() {
        return productAttrs;
    }

    public void setProductAttrs(ProductAttrs productAttrs) {
        this.productAttrs = productAttrs;
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public BigDecimal getQtyo() {
        return qtyo;
    }

    public void setQtyo(BigDecimal qtyo) {
        this.qtyo = qtyo;
    }

    public BigDecimal getQosRate() {
        return qosRate;
    }

    public void setQosRate(BigDecimal qosRate) {
        this.qosRate = qosRate;
    }

    public BigDecimal getQtys() {
        return qtys;
    }

    public void setQtys(BigDecimal qtys) {
        this.qtys = qtys;
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public void setQtyUnit(String qtyUnit) {
        this.qtyUnit = qtyUnit;
    }

    public BigDecimal getUpric() {
        return upric;
    }

    public void setUpric(BigDecimal upric) {
        this.upric = upric;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

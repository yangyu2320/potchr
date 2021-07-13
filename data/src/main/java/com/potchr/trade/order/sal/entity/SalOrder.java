package com.potchr.trade.order.sal.entity;

import com.potchr.trade.comm.Currency;
import com.potchr.trade.comm.DeliveryType;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 销售订单
 *
 * @author yangyu
 * @since 2021/7/6
 */
@MappedSuperclass
public abstract class SalOrder<ProductItem extends SalOrderProductItem> implements Serializable {

    @Serial
    private static final long serialVersionUID = -6310756069482337626L;

    //订单ID
    @Id
    @Column(length = 16)
    protected String id;

    //订单编号
    @Column(length = 16)
    protected String code;

    //客户
    @Column(length = 16)
    protected String customer;

    //订单日期
    @Temporal(TemporalType.DATE)
    protected LocalDate orderDate;

    //订单有效期
    @Temporal(TemporalType.DATE)
    protected LocalDate expiryDate;

    //币种
    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    protected Currency currency;

    //订单总金额
    @Column(precision = 11, scale = 2)
    protected BigDecimal amount;

    //溢装率
    @Column(precision = 5, scale = 2)
    protected BigDecimal overShipRate;

    //短装率
    @Column(precision = 5, scale = 2)
    protected BigDecimal shortShipRate;

    //交货日期
    @Temporal(TemporalType.DATE)
    protected LocalDate deliveryDate;

    //交货方式
    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    protected DeliveryType deliveryType;

    //订单明细
    @OneToMany
    protected List<ProductItem> productItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getOverShipRate() {
        return overShipRate;
    }

    public void setOverShipRate(BigDecimal overShipRate) {
        this.overShipRate = overShipRate;
    }

    public BigDecimal getShortShipRate() {
        return shortShipRate;
    }

    public void setShortShipRate(BigDecimal shortShipRate) {
        this.shortShipRate = shortShipRate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }
}

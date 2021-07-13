package com.potchr.trade.comm;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

/**
 * 商品属性
 *
 * @author yangyu
 * @since 2021/7/6
 */
@Embeddable
public final class ProductAttrs implements Serializable {

    @Serial
    private static final long serialVersionUID = 7885877975720516037L;

    @Column(length = 32)
    private String attr01;

    @Column(length = 32)
    private String attr02;

    @Column(length = 32)
    private String attr03;

    @Column(length = 32)
    private String attr04;

    @Column(length = 32)
    private String attr05;

    @Column(length = 32)
    private String attr06;

    @Column(length = 32)
    private String attr08;

    @Column(length = 32)
    private String attr09;

    @Column(length = 32)
    private String attr10;

    public String getAttr01() {
        return attr01;
    }

    public void setAttr01(String attr01) {
        this.attr01 = attr01;
    }

    public String getAttr02() {
        return attr02;
    }

    public void setAttr02(String attr02) {
        this.attr02 = attr02;
    }

    public String getAttr03() {
        return attr03;
    }

    public void setAttr03(String attr03) {
        this.attr03 = attr03;
    }

    public String getAttr04() {
        return attr04;
    }

    public void setAttr04(String attr04) {
        this.attr04 = attr04;
    }

    public String getAttr05() {
        return attr05;
    }

    public void setAttr05(String attr05) {
        this.attr05 = attr05;
    }

    public String getAttr06() {
        return attr06;
    }

    public void setAttr06(String attr06) {
        this.attr06 = attr06;
    }

    public String getAttr08() {
        return attr08;
    }

    public void setAttr08(String attr08) {
        this.attr08 = attr08;
    }

    public String getAttr09() {
        return attr09;
    }

    public void setAttr09(String attr09) {
        this.attr09 = attr09;
    }

    public String getAttr10() {
        return attr10;
    }

    public void setAttr10(String attr10) {
        this.attr10 = attr10;
    }
}

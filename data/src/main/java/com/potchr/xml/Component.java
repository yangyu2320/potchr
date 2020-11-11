package com.potchr.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Component {

    //标题
    @XmlAttribute(required = true)
    private String title;

    //名称
    @XmlAttribute(required = true)
    private String name;

    //颜色
    @XmlAttribute
    private String color;

    //宽
    @XmlAttribute
    private String width;

    //高
    @XmlAttribute
    private String height;

    public String title() {
        return title;
    }

    public void title(String title) {
        this.title = title;
    }

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public String color() {
        return color;
    }

    public void color(String color) {
        this.color = color;
    }

    public String width() {
        return width;
    }

    public void width(String width) {
        this.width = width;
    }

    public String height() {
        return height;
    }

    public void height(String height) {
        this.height = height;
    }

    public abstract void render(StringBuffer buffer);
}

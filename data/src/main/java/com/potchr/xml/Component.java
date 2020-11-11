package com.potchr.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Component")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Component {

    //名称
    @XmlAttribute(required = true)
    public String name;

    //标题
    @XmlAttribute(required = true)
    public String title;

    //前景色
    @XmlAttribute
    public String color;

    //背景色
    @XmlAttribute
    public String backgroundColor;

    //字体大小
    @XmlAttribute
    public Integer fontSize;

    //字体族
    @XmlAttribute
    public String fontFamily;

    //宽
    @XmlAttribute
    public Integer width;

    //高
    @XmlAttribute
    public Integer height;

    @XmlAttribute
    public Integer layoutWidth = 1;

    @XmlAttribute
    public Integer layoutHeight = 1;

    //隐藏
    @XmlAttribute
    public Boolean hidden = false;



    public abstract void render(StringBuffer buffer);
}

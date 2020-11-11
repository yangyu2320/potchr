package com.potchr.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Component")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Component {

    //标题
    @XmlAttribute(required = true)
    public String title;

    //名称
    @XmlAttribute(required = true)
    public String name;

    //颜色
    @XmlAttribute
    public String color;

    //宽
    @XmlAttribute
    public String width;

    //高
    @XmlAttribute
    public String height;

    //隐藏
    @XmlAttribute
    public Boolean hidden = false;



    public abstract void render(StringBuffer buffer);
}

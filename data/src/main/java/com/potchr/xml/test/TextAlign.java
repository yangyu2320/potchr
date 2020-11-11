package com.potchr.xml.test;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(value = String.class)
public enum TextAlign {
    @XmlEnumValue("left") LEFT,
    @XmlEnumValue("center") CENTER,
    @XmlEnumValue("right") RIGHT;
}

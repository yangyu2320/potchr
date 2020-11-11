package com.potchr.xml.test;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(value = String.class)
public enum Gender {
    @XmlEnumValue("male") MALE,
    @XmlEnumValue("female") FEMALE;
}

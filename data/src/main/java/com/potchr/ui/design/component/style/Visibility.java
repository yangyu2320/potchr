package com.potchr.ui.design.component.style;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Visibility {
    @XmlEnumValue("collapse") COLLAPSE,
    @XmlEnumValue("hidden") HIDDEN,
    @XmlEnumValue("inherit") INHERIT,
    @XmlEnumValue("initial") INITIAL,
    @XmlEnumValue("unset") UNSET,
    @XmlEnumValue("visible") VISIBLE;
}

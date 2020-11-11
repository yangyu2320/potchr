package com.potchr.xml.panel;

import com.potchr.xml.Container;

import javax.xml.bind.annotation.*;

@XmlType(name = "DockLayout")
@XmlAccessorType(XmlAccessType.FIELD)
public class DockPane extends Container {

    @XmlAttribute(required = true)
    public DockPosition position;

    @Override
    public void render(StringBuffer buffer) {

    }

    @XmlEnum
    @XmlType(name = "DockPosition")
    public enum DockPosition {
        top,
        bottom,
        left,
        right
    }
}

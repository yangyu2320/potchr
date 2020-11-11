package com.potchr.xml.button;

import javax.xml.bind.annotation.*;

@XmlType(name = "IconButton")
@XmlAccessorType(XmlAccessType.FIELD)
public class IconButton extends Button {

    @XmlAttribute
    public ButtonIcon icon;

    @Override
    public void render(StringBuffer buffer) {

    }

    @XmlEnum
    public static enum ButtonIcon {
        add,
        delete,
        edit,
        save,
        refresh
    }
}

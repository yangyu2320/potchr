package com.potchr.xml.panel;

import com.potchr.xml.Container;
import com.potchr.xml.button.IconButton;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "ToolBar")
@XmlAccessorType(XmlAccessType.FIELD)
public class ToolBar extends Container {

    @XmlElement(name = "Button")
    public List<ToolBarButton> buttons;

    @Override
    public void render(StringBuffer buffer) {

    }

    @XmlType(name = "ToolBarButton")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ToolBarButton extends IconButton {
    }
}

package com.potchr.ui.design.component;

import com.potchr.ui.design.component.style.Visibility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;

/**
 * UI设计-组件
 *
 * @author yangyu
 * @since 2019-02-28
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Component {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private Integer width;

    @XmlAttribute
    private Integer height;

    @XmlAttribute
    private Visibility visibility;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    abstract public List<Component> getSubComponents();

    abstract public void setSubComponents(List<Component> subComponents);
}

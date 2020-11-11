package com.potchr.xml;

import com.potchr.xml.layout.*;
import com.potchr.xml.table.GridTable;
import com.potchr.xml.table.RecordTable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Container extends Component {

    @XmlElements({
            @XmlElement(name = "GridTable", type = GridTable.class),
            @XmlElement(name = "RecordTable", type = RecordTable.class),
            @XmlElement(name = "BorderLayout", type = BorderLayout.class),
            @XmlElement(name = "GridLayout", type = GridLayout.class),
            @XmlElement(name = "TabbedPaneLayout", type = TabbedPaneLayout.class),
            @XmlElement(name = "VerticalLayout", type = VerticalLayout.class),
            @XmlElement(name = "HorizintalLayout", type = HorizintalLayout.class)
    })
    final private List<Component> components = new ArrayList<>();

    public void appendComponent(Component component) {
        Objects.requireNonNull(component, "组件不可为空！");
        this.components.add(component);
    }

    public void removeComonent(Component component) {
        if (component == null) {
            return;
        }
        this.components.remove(component);
    }

    public List<Component> listComponents() {
        return this.components;
    }
}

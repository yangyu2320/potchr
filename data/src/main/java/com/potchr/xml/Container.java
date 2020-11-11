package com.potchr.xml;

import com.potchr.xml.layout.*;
import com.potchr.xml.table.GridTable;
import com.potchr.xml.table.RecordTable;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "Container")
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
    public List<Component> components = new ArrayList<>();

    @Override
    public void render(StringBuffer buffer) {
        this.components.forEach(component -> component.render(buffer));
    }
}

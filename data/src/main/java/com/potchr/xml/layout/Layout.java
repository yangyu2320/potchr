package com.potchr.xml.layout;

import com.potchr.xml.Component;
import com.potchr.xml.Container;
import com.potchr.xml.panel.DockPane;
import com.potchr.xml.panel.SearchPane;
import com.potchr.xml.panel.ToolBar;
import com.potchr.xml.table.GridTable;
import com.potchr.xml.table.RecordTable;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @see BorderLayout
 * @see GridLayout
 * @see TabbedPaneLayout
 * @see VerticalLayout
 * @see HorizontalLayout
 */
@XmlType(name = "Layout")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Layout extends Container{
    @XmlElements({
            @XmlElement(name = "BorderLayout", type = BorderLayout.class),
            @XmlElement(name = "GridLayout", type = GridLayout.class),
            @XmlElement(name = "TabbedPaneLayout", type = TabbedPaneLayout.class),
            @XmlElement(name = "VerticalLayout", type = VerticalLayout.class),
            @XmlElement(name = "HorizontalLayout", type = HorizontalLayout.class),
            @XmlElement(name = "GridTable", type = GridTable.class),
            @XmlElement(name = "RecordTable", type = RecordTable.class),
            @XmlElement(name = "DockPane", type = DockPane.class),
            @XmlElement(name = "ToolBar", type = ToolBar.class),
            @XmlElement(name = "SearchPane", type = SearchPane.class)
    })
    public List<Component> components = new ArrayList<>();

    @Override
    public void render(StringBuffer buffer) {
        this.components.forEach(component -> component.render(buffer));
    }
}

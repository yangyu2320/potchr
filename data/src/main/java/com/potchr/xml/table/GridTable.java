package com.potchr.xml.table;

import com.potchr.xml.Component;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "GridTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class GridTable extends Component {

    @XmlElements({
            @XmlElement(name = "Column", type = GridTableColumn.class)
    })
    final public List<Component> components = new ArrayList<>();

    @Override
    public void render(StringBuffer buffer) {

    }

    @XmlType(name = "GridTableColumn")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GridTableColumn extends TableColumn{

        @Override
        public void render(StringBuffer buffer) {

        }
    }
}

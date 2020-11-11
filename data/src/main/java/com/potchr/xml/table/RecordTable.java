package com.potchr.xml.table;

import com.potchr.xml.Component;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "RecordTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecordTable extends Component {

    @XmlElements({
            @XmlElement(name = "Column", type = RecordTableColumn.class)
    })
    public List<Component> components = new ArrayList<>();

    @Override
    public void render(StringBuffer buffer) {

    }

    @XmlType(name = "RecordTableColumn")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class RecordTableColumn extends TableColumn {

        @Override
        public void render(StringBuffer buffer) {

        }
    }
}

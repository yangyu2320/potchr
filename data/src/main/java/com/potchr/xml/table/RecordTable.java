package com.potchr.xml.table;

import com.potchr.xml.Component;
import com.potchr.xml.button.IconButton;
import com.potchr.xml.util.Matrix;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlType(name = "RecordTable")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecordTable extends Component {

    @XmlAttribute
    public Integer columns = 4;

    @XmlElements({
            @XmlElement(name = "Column", type = RecordTableColumn.class),
            @XmlElement(name = "IconButton", type = IconButton.class)
    })
    public List<Component> components = new ArrayList<>();

    @Override
    public void render(StringBuffer buffer) {

    }

    private List<List<Component>> buildComponentMatrix() {
        List<List<Component>> matrix = new ArrayList<>();
        if (this.components.size() == 0) {
            return matrix;
        }
        return new Matrix(columns, this.components).toMatrix();
    }

    @XmlType(name = "RecordTableColumn")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class RecordTableColumn extends TableColumn {

        @Override
        public void render(StringBuffer buffer) {

        }
    }
}

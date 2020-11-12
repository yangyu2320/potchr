package com.potchr.xml.table;

import com.potchr.xml.Component;
import com.potchr.xml.button.IconButton;
import com.potchr.xml.util.Matrix;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

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
        buffer.append("<table class='").append("record-table").append("'><tbody>");
        final List<List<Component>> matrix = buildComponentMatrix();
        for (List<Component> row : matrix) {
            buffer.append("<tr>");
            for (Component component : row) {
                component.render(buffer);
            }
            buffer.append("</tr>");
        }
        buffer.append("</tbody></table>");
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

        @XmlAttribute
        public Boolean labelHidden = false;

        @Override
        public void render(StringBuffer buffer) {
            if (!labelHidden) {
                buffer.append("<td rowspan=").append(this.layoutHeight).append("><label><span").append(forceInput ? " class='force'" : "").append(">*</span>").append(this.name).append("</label></td>");
            }
            int colSpan = this.layoutWidth * 2 - 1 + (labelHidden ? 1 : 0);
            buffer.append("<td colspan=").append(colSpan).append(" rowspan=").append(this.layoutHeight).append("><input type='").append(type.htmlType()).append("'></td>");
        }
    }
}

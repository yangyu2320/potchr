package com.potchr.xml.table;


import com.potchr.xml.Component;

import javax.xml.bind.annotation.*;

@XmlType(name = "TableColumn")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class TableColumn extends Component {

    @XmlAttribute
    public Boolean readOnly = false;

    @XmlAttribute
    public Boolean forceInput = false;

    @XmlAttribute
    public TableColumnType type = TableColumnType.text;

    @XmlEnum
    @XmlType(name = "TableColumnType")
    public enum TableColumnType {

        text("text");

        private String htmlType;

        TableColumnType(String htmlType) {
            this.htmlType = htmlType;
        }

        public String htmlType() {
            return this.htmlType;
        }
    }
}

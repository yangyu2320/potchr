package com.potchr.xml.table;


import com.potchr.xml.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class TableColumn extends Component {

    @XmlAttribute
    private String title;

    @XmlAttribute
    private boolean readOnly;
}

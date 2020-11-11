package com.potchr.xml.panel;

import com.potchr.xml.Component;
import com.potchr.xml.Container;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(name = "SearchPane")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchPane extends Container {

    @XmlElement(name = "Item")
    public List<SearchPaneItem> items;

    @Override
    public void render(StringBuffer buffer) {

    }

    @XmlType(name = "SearchPaneItem")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SearchPaneItem extends Component {

        @Override
        public void render(StringBuffer buffer) {

        }
    }
}

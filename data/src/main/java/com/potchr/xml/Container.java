package com.potchr.xml;

import com.potchr.xml.layout.*;
import com.potchr.xml.panel.DockPane;
import com.potchr.xml.panel.ToolBar;
import com.potchr.xml.table.GridTable;
import com.potchr.xml.table.RecordTable;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "Container")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Container extends Component {
}

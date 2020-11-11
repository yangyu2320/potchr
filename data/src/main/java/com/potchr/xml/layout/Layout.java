package com.potchr.xml.layout;

import com.potchr.xml.Container;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @see BorderLayout
 * @see GridLayout
 * @see TabbedPaneLayout
 * @see VerticalLayout
 * @see HorizintalLayout
 */
@XmlType(name = "Layout")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Layout extends Container{

}

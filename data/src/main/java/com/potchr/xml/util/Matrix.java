package com.potchr.xml.util;

import com.potchr.xml.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Matrix {

    private int columns;

    private List<Component> allComponents;

    private List<List<Component>> matrix;

    public Matrix (int columns, List<Component> components) {
        this.columns = columns;
        this.allComponents = components;
    }

    public List<List<Component>> toMatrix() {
        if (this.matrix == null) {
            appendRow();
            for (Component component : allComponents) {
                fill(component);
            }
        }
        return this.matrix;
    }

    private void fill(Component component) {
        NextRegion nextRegion = findNextRegion();
        Objects.requireNonNull(nextRegion, "不应该为空,如果为空，则算法有问题！");
        if (nextRegion.width < component.layoutWidth) {
            component.layoutWidth = nextRegion.width;
        }
        for (int i = 0; i < component.layoutWidth; i++) {
            for (int j = 0; j < component.layoutHeight; j++) {
                List<Component> row = this.matrix.size() > nextRegion.rowStart + j ? this.matrix.get(nextRegion.rowStart + j) : appendRow();
                row.set(nextRegion.colStart + i, component);
            }
        }
    }

    private NextRegion findNextRegion() {
        for (int rowNum = 0; rowNum < this.matrix.size(); rowNum++) {
            List<Component> row = this.matrix.get(rowNum);
            for (int colNum = 0; colNum < row.size(); colNum++) {
                Component component = row.get(colNum);
                if (component == null) {
                    int width = 1;
                    for (int i = colNum + 1; i < row.size(); i++) {
                        if (row.get(i) != null) {
                            break;
                        }
                        width += 1;
                    }
                    return new NextRegion(colNum, rowNum, width);
                }
            }
        }
        appendRow();
        return new NextRegion(0, this.matrix.size() - 1, columns);
    }

    private List<Component> appendRow() {
        if (this.matrix == null) {
            this.matrix = new ArrayList<>();
        }
        List<Component> row = new ArrayList<>();
        for (int i = 0; i < this.columns; i++) {
            row.add(null);
        }
        this.matrix.add(row);
        return row;
    }

    private static class NextRegion {

        public int colStart;

        public int rowStart;

        public int width;

        public NextRegion(int colStart, int rowStart, int width) {
            this.colStart = colStart;
            this.rowStart = rowStart;
            this.width = width;
        }
    }
}

package com.potchr.xml.util;

import com.potchr.xml.Component;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private int columns;

    private List<Component> allComponents;

    private List<List<Component>> matrix = new ArrayList<>();

    private EmptyComponent emptyComponent = new EmptyComponent();

    public Matrix(int columns, List<Component> components) {
        this.columns = columns;
        this.allComponents = components;
    }

    public List<List<Component>> toMatrix() {
        for (Component component : allComponents) {
            fill(component);
        }
        for (List<Component> components : this.matrix) {
            for (int i = components.size() - 1; i >= 0; i--) {
                if (components.get(i) == emptyComponent) {
                    components.remove(i);
                }
            }
        }
        return this.matrix;
    }

    private void fill(Component component) {
        NextRegion nextRegion = findNextRegion();
        if (nextRegion.width < component.layoutWidth) {
            component.layoutWidth = nextRegion.width;
        }
        for (int i = 0; i < component.layoutWidth; i++) {
            for (int j = 0; j < component.layoutHeight; j++) {
                List<Component> row = this.matrix.size() > nextRegion.rowStart + j ? this.matrix.get(nextRegion.rowStart + j) : appendRow();
                row.set(nextRegion.colStart + i, (i + j == 0) ? component : emptyComponent);
            }
        }
    }


    private NextRegion findNextRegion() {
        if (this.matrix.size() == 0) {
            appendRow();
        }
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

    private static class EmptyComponent extends Component {

        @Override
        public void render(StringBuffer buffer) {
            throw new UnsupportedOperationException();
        }
    }
}

package com.potchr.data;

import com.potchr.xml.Component;
import com.potchr.xml.table.RecordTable;
import com.potchr.xml.util.Matrix;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DataApplicationTests {

    private SnowflakeShardingKeyGenerator keyGenerator = new SnowflakeShardingKeyGenerator();

    @Test
    public void testMatrix() {
        List<Component> componentList = new ArrayList<>();
        RecordTable.RecordTableColumn c1 = new RecordTable.RecordTableColumn();
        c1.name = "合同号";
        RecordTable.RecordTableColumn c2 = new RecordTable.RecordTableColumn();
        c2.name = "公司";
        RecordTable.RecordTableColumn c3 = new RecordTable.RecordTableColumn();
        c3.name = "部门";
        RecordTable.RecordTableColumn c4 = new RecordTable.RecordTableColumn();
        c4.name = "人员";
        RecordTable.RecordTableColumn c5 = new RecordTable.RecordTableColumn();
        c5.name = "客商";
        c5.layoutWidth = 2;
        RecordTable.RecordTableColumn c6 = new RecordTable.RecordTableColumn();
        c6.name = "备注";
        c6.layoutWidth = 2;
        c6.layoutHeight = 2;
        RecordTable.RecordTableColumn c7 = new RecordTable.RecordTableColumn();
        c7.name = "委托方";
        c7.layoutHeight = 3;
        c7.layoutWidth = 3;
        RecordTable.RecordTableColumn c8 = new RecordTable.RecordTableColumn();
        c8.name = "测试1";
        RecordTable.RecordTableColumn c9 = new RecordTable.RecordTableColumn();
        c9.name = "测试2";
        RecordTable.RecordTableColumn c10 = new RecordTable.RecordTableColumn();
        c10.name = "测试3";
        RecordTable.RecordTableColumn c11 = new RecordTable.RecordTableColumn();
        c11.name = "测试4";
        componentList.add(c1);
        componentList.add(c2);
        componentList.add(c3);
        componentList.add(c4);
        componentList.add(c5);
        componentList.add(c6);
        componentList.add(c7);
        componentList.add(c8);
        componentList.add(c9);
        componentList.add(c10);
        componentList.add(c11);
        List<List<Component>> lists = new Matrix(4, componentList).toMatrix();
        System.out.println(lists.size());
        for (List<Component> list : lists) {
            for (Component component : list) {
                System.out.print(component.name);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}

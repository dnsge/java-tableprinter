package org.dnsge.util.tableprinter.column;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TableColumnTest {

    @Test
    void add() {
        TableColumn<Integer> tableColumn = new TableColumn<>("Header", 1, 2, 46, 12345);
        assertEquals(Integer.valueOf(12345), tableColumn.get(3));

        assertThrows(IndexOutOfBoundsException.class, () -> tableColumn.get(4));

        tableColumn.add(919294);
        assertEquals(Integer.valueOf(12345), tableColumn.get(3));
        assertEquals(Integer.valueOf(919294), tableColumn.get(4));
    }

    @Test
    void get() {
        TableColumn<Integer> tableColumn = new TableColumn<>("Header", 1, 2, 46, 12345);

        assertEquals(Integer.valueOf(1), tableColumn.get(0));
        assertEquals(Integer.valueOf(2), tableColumn.get(1));
        assertEquals(Integer.valueOf(46), tableColumn.get(2));
        assertEquals(Integer.valueOf(12345), tableColumn.get(3));

        assertThrows(IndexOutOfBoundsException.class, () -> tableColumn.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> tableColumn.get(4));
    }

    @Test
    void longestItemLength() {
        TableColumn<Integer> tableColumn = new TableColumn<>("Header", 1, 2, 46, 223);

        assertEquals(6, tableColumn.longestItemLength());
        tableColumn.add(99999999);
        assertEquals(8, tableColumn.longestItemLength());

        class StringWrapper {
            private String name;

            public StringWrapper(String name) {
                this.name = name;
            }

            public String toString() {
                return "StringWrapper (" + name + ")";
            }
        }

        TableColumn<StringWrapper> stringWrapperTableColumn = new TableColumn<>("SuperLongNameOfAHeader",
                new StringWrapper("a"), new StringWrapper("b"));
        assertEquals(22, stringWrapperTableColumn.longestItemLength());
        stringWrapperTableColumn.add(new StringWrapper("Super long String wrapper!!"));
        assertEquals(43, stringWrapperTableColumn.longestItemLength());
    }

    @Test
    void getColumnName() {
        TableColumn<Integer> tableColumn = new TableColumn<>("Header", 1, 2, 46, 223);
        assertEquals("Header", tableColumn.getColumnName());
    }

    @Test
    void getColumnData() {
        TableColumn<Integer> tableColumn = new TableColumn<>("Header", 1, 2, 46, 223);
        assertEquals(Arrays.asList(1, 2, 46, 223), tableColumn.getColumnData());
    }

    @Test
    void length() {
        TableColumn<Integer> tableColumn = new TableColumn<>("Header", 1, 2, 46, 223);
        assertEquals(4, tableColumn.length());
        tableColumn.add(5);
        assertEquals(5, tableColumn.length());
    }
}
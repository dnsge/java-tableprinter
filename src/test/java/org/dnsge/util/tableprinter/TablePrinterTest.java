package org.dnsge.util.tableprinter;

import org.dnsge.util.tableprinter.column.TableColumn;
import org.dnsge.util.tableprinter.row.TableItem;
import org.dnsge.util.tableprinter.row.TableRow;
import org.dnsge.util.tableprinter.row.TableRowItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TablePrinterTest {

    @Test
    void columnsToStringTable() {

        TableColumn<Integer> numberColumn = new TableColumn<>("#",
                1, 2, 345, 56, null, 18, 19, 20);

        TableColumn<String> idColumn = new TableColumn<>("Identifier",
                "aaa", "bbb", "ccc", null, "ddd", null, "eee");

        TableColumn<String> monthColumn = new TableColumn<>("Month",
                "January", "February", "July", "September", "December", null, "October", "May");

        TableColumn<String> countryColumn = new TableColumn<>("Country",
                "United States", "Canada", "China", null, "France", "Portugal", "Russia", null);

        assertArrayEquals(new String[]{
                "#   | Identifier | Month     | Country      ",
                "--------------------------------------------",
                "1   | aaa        | January   | United States",
                "2   | bbb        | February  | Canada       ",
                "345 | ccc        | July      | China        ",
                "56  |            | September |              ",
                "    | ddd        | December  | France       ",
                "18  |            |           | Portugal     ",
                "19  | eee        | October   | Russia       ",
                "20  |            | May       |              "
        }, TablePrinter.columnsToStringTable(numberColumn, idColumn, monthColumn, countryColumn));

    }

    @Test
    void rowsToStringTable() {
        TableRow tableRow1 = new TableRow(List.of("A", "b", "c"), List.of("1", "234", "5678910"));
        TableRow tableRow2 = new TableRow(List.of("A", "b", "c"), List.of("56", "2", "1824.5"));

        assertArrayEquals(new String[]{
                "A  | b   | c      ",
                "------------------",
                "1  | 234 | 5678910",
                "56 | 2   | 1824.5 ",
        }, TablePrinter.rowsToStringTable(tableRow1, tableRow2));
    }

    @Test
    @DisplayName("rowsToStringTable() fail")
    void rowsToStringTableFail() {
        TableRow tableRow1 = new TableRow(List.of("A", "b", "c"), List.of("1", "234", "5678910"));
        TableRow tableRow2 = new TableRow(List.of("1", "2", "3"), List.of("56", "2", "1824.5"));

        assertThrows(IllegalArgumentException.class, () -> TablePrinter.rowsToStringTable(tableRow1, tableRow2));
    }

    @Test
    void objectsToRowArray() {

        class Person implements TableRowItem {

            @TableItem(header = "Name")
            final String name;
            @TableItem(header = "Age")
            public final Integer age;
            @TableItem(header = "Workplace")
            public final String workplace;
            private final String password;

            public Person(String name, Integer age, String workplace, String password) {
                this.name = name;
                this.age = age;
                this.workplace = workplace;
                this.password = password;
            }

            @TableItem(header = "Birth year")
            public int birthYear() {
                return 2018 - age;
            }

        }

        Person p1 = new Person("Amelia", 28, "Google", "secretPassword");
        Person p2 = new Person("David", 16, null, "abc12345");
        Person p3 = new Person("Joey", 63, "Disney", "abc");

        assertArrayEquals(new String[]{
                "Name   | Age | Workplace | Birth year",
                "-------------------------------------",
                "Amelia | 28  | Google    | 1990      ",
                "David  | 16  |           | 2002      ",
                "Joey   | 63  | Disney    | 1955      "
        }, TablePrinter.rowsToStringTable(TablePrinter.objectsToRowArray(p1, p2, p3)));

    }
}

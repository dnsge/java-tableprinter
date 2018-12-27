package org.dnsge.util.tableprinter.printer;

import org.dnsge.util.tableprinter.column.TableColumn;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColumnPrinterTest {

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

        assertEquals(List.of(
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
        ), ColumnPrinter.columnsToStringTable(numberColumn, idColumn, monthColumn, countryColumn));
    }

}

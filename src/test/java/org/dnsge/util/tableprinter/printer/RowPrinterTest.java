package org.dnsge.util.tableprinter.printer;

import org.dnsge.util.tableprinter.row.TableRow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RowPrinterTest {

    @Test
    void rowsToStringTable() {
        TableRow tableRow1 = new TableRow(List.of("A", "b", "c"), List.of("1", "234", "5678910"));
        TableRow tableRow2 = new TableRow(List.of("A", "b", "c"), List.of("56", "2", "1824.5"));

        assertEquals(List.of(
                "A  | b   | c      ",
                "------------------",
                "1  | 234 | 5678910",
                "56 | 2   | 1824.5 "
        ), RowPrinter.rowsToStringTable(tableRow1, tableRow2));
    }

    @Test
    @DisplayName("rowsToStringTable() fail")
    void rowsToStringTableFail() {
        TableRow tableRow1 = new TableRow(List.of("A", "b", "c"), List.of("1", "234", "5678910"));
        TableRow tableRow2 = new TableRow(List.of("1", "2", "3"), List.of("56", "2", "1824.5"));

        assertThrows(IllegalArgumentException.class, () -> RowPrinter.rowsToStringTable(tableRow1, tableRow2));
    }

}

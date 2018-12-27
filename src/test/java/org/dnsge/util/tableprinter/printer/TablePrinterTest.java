package org.dnsge.util.tableprinter.printer;

import org.dnsge.util.tableprinter.column.TableColumn;
import org.dnsge.util.tableprinter.table.DataSource;
import org.dnsge.util.tableprinter.table.Table;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TablePrinterTest {

    @Test
    void tableToStringTable() {
        TableColumn<Integer> numberColumn = new TableColumn<>("Number",
                43, 44, 45, 46, 47);
        TableColumn<String> nameColumn = new TableColumn<>("Name",
                "George W. Bush", "Barack Obama", "Donald Trump", null, "Taylor Swift");
        TableColumn<String> partyColumn = new TableColumn<>("Party",
                "Republican", "Democrat", "Republican", "Democrat", "Independent");
        TableColumn<String> partyColumn2 = new TableColumn<>("Party",
                "Republican", "Democrat", "Republican", "Democrat", "Independent");
        TableColumn<String> birthLocation = new TableColumn<>("Birthplace",
                "New Haven, CT", "Honolulu, HI", "New York City, NY", null, "Reading, PA");

        DataSource dataSource = DataSource.fromColumns(numberColumn, nameColumn, partyColumn, partyColumn2, birthLocation);
        Table table = new Table(dataSource);
        assertEquals(List.of(
                "Number │ Name           │ Party       │ Party       │ Birthplace       ",
                "───────┼────────────────┼─────────────┼─────────────┼──────────────────",
                "43     │ George W. Bush │ Republican  │ Republican  │ New Haven, CT    ",
                "44     │ Barack Obama   │ Democrat    │ Democrat    │ Honolulu, HI     ",
                "45     │ Donald Trump   │ Republican  │ Republican  │ New York City, NY",
                "46     │                │ Democrat    │ Democrat    │                  ",
                "47     │ Taylor Swift   │ Independent │ Independent │ Reading, PA      "
        ), TablePrinter.tableToStringTable(table));
    }

}
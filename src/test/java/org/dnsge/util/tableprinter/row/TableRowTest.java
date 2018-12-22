package org.dnsge.util.tableprinter.row;

import org.dnsge.util.tableprinter.TableHeader;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TableRowTest {

    @Test
    public void tableRowCreationList() {
        List<String> headers = Arrays.asList("Name", "Age", "Other");
        List<String> values = Arrays.asList("Daniel", null, "Something");

        TableRow tableRow = new TableRow(headers, values);
        assertEquals(Arrays.asList("Daniel", "", "Something"), tableRow.getCellValues());
        assertEquals(new TableHeader("Name", "Age", "Other"), tableRow.getHeader());
    }

}
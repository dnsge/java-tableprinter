package org.dnsge.util.tableprinter.table;

import org.dnsge.util.tableprinter.column.TableColumn;
import org.dnsge.util.tableprinter.row.TableRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataSourceTest {

    private TableRow r1, r2, r3;
    private TableColumn<String> c1, c2, c3;

    @BeforeEach
    void setup() {
        TableHeader header = new TableHeader("a", "bb", "ccc");
        r1 = new TableRow(header, List.of("1", "2", "3"));
        r2 = new TableRow(header, List.of("44", "55", "66"));
        r3 = new TableRow(header, List.of("777", "888", "999"));
        c1 = new TableColumn<>("a", "1", "44", "777");
        c2 = new TableColumn<>("bb", "2", "55", "888");
        c3 = new TableColumn<>("ccc", "3", "66", "999");
    }

    @Test
    void fromRows() {
        DataSource source = DataSource.fromRows(r1, r2, r3);
        assertEquals(List.of(r1, r2, r3), source.getRowData());
        assertEquals(new TableHeader("a", "bb", "ccc"), source.getTableHeader());
    }

    @Test
    void fromColumns() {
        DataSource source = DataSource.fromColumns(c1, c2, c3);
        assertEquals(List.of(r1, r2, r3), source.getRowData());
        assertEquals(new TableHeader("a", "bb", "ccc"), source.getTableHeader());
    }

    @Test
    void isEmpty() {
        DataSource source1 = DataSource.fromColumns();
        assertTrue(source1.isEmpty());

        source1 = DataSource.fromRows();
        assertTrue(source1.isEmpty());

        DataSource source2 = DataSource.fromColumns(c1);
        assertFalse(source2.isEmpty());

        source2 = DataSource.fromRows(r1);
        assertFalse(source2.isEmpty());
    }

}

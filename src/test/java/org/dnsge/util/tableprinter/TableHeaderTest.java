package org.dnsge.util.tableprinter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TableHeaderTest {

    @Test
    void headerCount() {
        TableHeader tableHeader1 = new TableHeader();
        assertEquals(0, tableHeader1.headerCount());

        TableHeader tableHeader2 = new TableHeader("a", "b", "c");
        assertEquals(3, tableHeader2.headerCount());
    }

    @Test
    void getHeaderValues() {
        TableHeader tableHeader1 = new TableHeader();
        assertEquals(List.of(), tableHeader1.getHeaderValues());

        TableHeader tableHeader2 = new TableHeader("a", "b", "c");
        assertEquals(List.of("a", "b", "c"), tableHeader2.getHeaderValues());
    }

    @Test
    void equals() {
        TableHeader tableHeader1 = new TableHeader();
        assertEquals(new TableHeader(), tableHeader1);

        TableHeader tableHeader2 = new TableHeader("a", "b", "c");
        assertEquals(new TableHeader("a", "b", "c"), tableHeader2);

        TableHeader tableHeader3 = new TableHeader("A", "b", "c");
        assertNotEquals(new TableHeader("a", "b", "c"), tableHeader3);
    }

}
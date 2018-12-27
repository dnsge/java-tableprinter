package org.dnsge.util.tableprinter;

import org.dnsge.util.tableprinter.row.TableRow;
import org.dnsge.util.tableprinter.table.TableAlignment;
import org.dnsge.util.tableprinter.table.TableHeader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableUtilsTest {

    @Test
    void padToLength() {
        assertEquals("Test      ", TableUtils.padToLength("Test", 10));
        assertEquals("One Hundred    ", TableUtils.padToLength("One Hundred", 15));
    }

    @Test
    void longestLengthByColumns() {
        TableHeader tableHeader = new TableHeader("First", "Second", "Super long Word");
        TableRow t1 = new TableRow(tableHeader, List.of("aaaaaaaa", "bbb", "cc"));
        TableRow t2 = new TableRow(tableHeader, List.of("aaaaaaaa", "bbb", "c"));
        TableRow t3 = new TableRow(tableHeader, List.of("aaaaaaaa", "bbbbbbbbb", "ccc"));

        assertArrayEquals(new int[] {
                8, 9, 15
        }, TableUtils.longestLengthByColumns(List.of(t1, t2, t3)));
    }

    @Test
    void padAccordingToAlignment() {
        assertEquals("Test      ", TableUtils.padAccordingToAlignment("Test", 10, TableAlignment.LEFT));
        assertEquals("      Test", TableUtils.padAccordingToAlignment("Test", 10, TableAlignment.RIGHT));
        assertEquals("   Test   ", TableUtils.padAccordingToAlignment("Test", 10, TableAlignment.CENTER));
    }

    @Test
    void verticalDividerOf() {
        assertEquals("----------", TableUtils.verticalDividerOf(10, '-'));
        assertEquals("+++++", TableUtils.verticalDividerOf(5, '+'));
        assertEquals("", TableUtils.verticalDividerOf(0, '+'));
    }

    @Test
    void verifyValidity() {
        List<TableRow> invalidRows = List.of(
                new TableRow(new TableHeader("a", "b"), List.of("1", "2")),
                new TableRow(new TableHeader("a", "b", "c"), List.of("1", "2", "3"))
        );

        assertThrows(IllegalArgumentException.class, () -> TableUtils.verifyValidity(invalidRows));
    }

    @Test
    void maxOfThree() {
        assertEquals(10, TableUtils.maxOfThree(2, 3, 10));
        assertEquals(10, TableUtils.maxOfThree(2, 10, 3));
        assertEquals(10, TableUtils.maxOfThree(10, 2, 3));
    }

}
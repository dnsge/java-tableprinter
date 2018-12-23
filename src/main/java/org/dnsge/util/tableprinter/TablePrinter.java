/*
 * MIT License
 *
 * Copyright (c) 2018 Daniel Sage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.dnsge.util.tableprinter;

import org.dnsge.util.tableprinter.column.TableColumn;
import org.dnsge.util.tableprinter.row.RowFactory;
import org.dnsge.util.tableprinter.row.TableRow;
import org.dnsge.util.tableprinter.row.TableRowItem;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class to print different objects in the form of a table
 *
 * @author Daniel Sage
 * @version 1.5
 */
public class TablePrinter {

    private TablePrinter() {

    }

    /**
     * Creates a {@link List<String>} of lines from some {@link TableColumn TableColumns}
     *
     * @param columns {@code TableColumn[]} of table columns to make the table from
     * @return {@link List<String>} of the lines of the table
     * @see TableColumn
     */
    public static List<String> columnsToStringTable(TableColumn... columns) {
        // Convert the columns to rows
        return rowsToStringTable(RowFactory.makeRowsFromColumns(columns));
    }

    /**
     * Creates a {@link List<String>} of lines from a {@link List<TableColumn>}
     *
     * @param columns {@link List<TableColumn>} to use
     * @return {@link List<String>} of lines
     * @see TableColumn
     */
    public static List<String> columnsToStringTable(List<TableColumn> columns) {
        return rowsToStringTable(RowFactory.makeRowsFromColumns(columns));
    }

    /**
     * Prints a {@code TableColumn[]} to {@link System#out}
     *
     * @param columns {@code TableColumn[]} of table columns to print
     */
    public static void printColumns(TableColumn... columns) {
        printColumns(System.out, columns);
    }

    /**
     * Prints a {@code TableColumn[]} to a {@code PrintStream}
     *
     * @param stream  {@code PrintStream} to print to
     * @param columns {@code TableColumn[]} of table columns to print
     * @see PrintStream
     */
    public static void printColumns(PrintStream stream, TableColumn... columns) {
        stream.println(String.join("\n", columnsToStringTable(columns)));
    }

    /**
     * Creates a {@link List<String>} of lines from some {@code TableRows}
     *
     * @param rows {@code TableRow[]} of table rows to make the table from
     * @return {@link List<String>} of lines
     * @see TableRow
     */
    public static List<String> rowsToStringTable(TableRow... rows) {
        return rowsToStringTable(Arrays.asList(rows));
    }

    /**
     * Creates a {@link List<String>} of lines from a {@link List<TableRow>}
     *
     * @param rows {@link List<TableRow>} to use
     * @return {@link List<String>} of lines
     */
    public static List<String> rowsToStringTable(List<TableRow> rows) {
        if (rows.size() == 0)
            return List.of("Empty Table");

        verifyValidity(rows);

        List<String> lines = new ArrayList<>();

        TableHeader header = rows.get(0).getHeader();
        int[] longestByColumn = new int[header.headerCount()];

        // Find the longest per column
        for (TableRow row : rows) {
            int i = 0;
            for (String cellValue : row.getCellValues()) {
                try {
                    longestByColumn[i] = maxOfThree(
                            longestByColumn[i],
                            cellValue.length(),
                            header.getHeaderValues().get(i).length());
                } catch (NullPointerException ignored) {
                }
                i++;
            }
        }

        List<String> paddedHeaders = new ArrayList<>();

        // Pad the headers
        int columnNumber = 0;
        for (String h : header.getHeaderValues()) {
            paddedHeaders.add(padToLength(h, longestByColumn[columnNumber]));
            columnNumber++;
        }

        // Header
        lines.add(String.join(" | ", paddedHeaders));
        // Dashes
        lines.add(dashDivider(lines.get(0).length()));

        // Generate rows
        for (TableRow row : rows) {
            List<String> thisRow = new ArrayList<>();

            int columnNum = 0;
            for (String cellValue : row.getCellValues()) {
                thisRow.add(padToLength(cellValue, longestByColumn[columnNum++]));
            }

            lines.add(String.join(" | ", thisRow));
        }

        return lines;
    }

    /**
     * Prints a {@code TableRow[]} to {@link System#out}
     *
     * @param rows {@code TableRow[]} of table rows of one type to print
     */
    public static void printRows(TableRow... rows) {
        printRows(Arrays.asList(rows));
    }

    /**
     * Prints a {@code TableRow[]} to a {@code PrintStream}
     *
     * @param stream {@code PrintStream} to print to
     * @param rows   {@code TableRow[]} of rows to print
     * @see PrintStream
     */
    public static void printRows(PrintStream stream, TableRow... rows) {
        printRows(stream, Arrays.asList(rows));
    }

    /**
     * Prints a {@link List<TableRow>} to {@link System#out}
     *
     * @param rows {@link List<TableRow>} of rows to print
     */
    public static void printRows(List<TableRow> rows) {
        printRows(System.out, rows);
    }

    public static void printRows(PrintStream stream, List<TableRow> rows) {
        stream.println(String.join("\n", rowsToStringTable(rows)));
    }

    /**
     * Creates a {@link List<TableRow>} from an some {@link TableRowItem TableRowItems}
     *
     * @param rows Array of {@link TableRowItem TableRowItems}
     * @return {@link List<TableRow>} from the {@link TableRowItem} array
     */
    public static List<TableRow> objectsToRowList(TableRowItem... rows) {
        return Stream.of(rows).map(RowFactory::makeRowFromTableRowItem).collect(Collectors.toList());
    }

    /**
     * Prints a {@link TableRowItem TableRowItems} to a {@link PrintStream}
     *
     * @param stream {@link PrintStream} to print to
     * @param items  Array of {@link TableRowItem TableRowItems} to print
     */
    public static void printObjects(PrintStream stream, TableRowItem... items) {
        printRows(stream, objectsToRowList(items));
    }

    /**
     * Prints an array of {@link TableRowItem TableRowItems} to {@link System#out}
     *
     * @param items Array of {@link TableRowItem TableRowItems} to print
     */
    public static void printObjects(TableRowItem... items) {
        printObjects(System.out, items);
    }

    /**
     * Pads a string with spaces at the end to a desired length
     *
     * @param input         String to pad
     * @param desiredLength Desired length of resulting string
     * @return Padded String
     */
    private static String padToLength(String input, int desiredLength) {
        if (input == null)
            input = "";

        int spacesNeeded = desiredLength - input.length();

        if (spacesNeeded <= 0)
            return input;

        StringBuilder outputBuilder = new StringBuilder(spacesNeeded);
        for (int i = 0; i < spacesNeeded; i++) {
            outputBuilder.append(" ");
        }

        return input + outputBuilder.toString();
    }

    /**
     * Creates a dash divider of a specific length
     *
     * @param length Length of desired dash divider
     * @return String of dashes
     */
    private static String dashDivider(int length) {
        StringBuilder dashDividerBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            dashDividerBuilder.append("-");
        }
        return dashDividerBuilder.toString();
    }

    private static void verifyValidity(List<TableRow> rows) {
        TableHeader checkHeader = rows.get(0).getHeader();
        for (Iterator<TableRow> iterator = rows.listIterator(1); iterator.hasNext(); ) {
            if (!iterator.next().getHeader().equals(checkHeader)) {
                throw new IllegalArgumentException("TableRows don't share the same TableHeader");
            }
        }
    }

    private static int maxOfThree(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

}

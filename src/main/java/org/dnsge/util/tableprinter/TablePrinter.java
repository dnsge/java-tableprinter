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
import java.util.List;
import java.util.stream.Stream;

/**
 * Class to print different objects in the form of a table
 *
 * @author Daniel Sage
 * @version 1.3
 */
public class TablePrinter {

    private TablePrinter() {

    }

    /**
     * Creates a {@code String[]} of lines from some {@code TableColumns}
     *
     * @param columns {@code TableColumn[]} of table columns to make the table from
     * @return {@code String[]} of the lines of the table
     * @see TableColumn
     */
    public static String[] columnsToStringTable(TableColumn... columns) {
        // Convert the columns to rows
        return rowsToStringTable(RowFactory.makeRows(columns));
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
        String[] lines = columnsToStringTable(columns);
        stream.println(String.join("\n", lines));
    }

    /**
     * Creates a {@code String[]} of lines from some {@code TableRows} of the same type
     *
     * @param rows {@code TableRow[]} of table rows to make the table from
     * @return {@code String[]} of the lines of the table
     * @see TableRow
     */
    public static String[] rowsToStringTable(TableRow... rows) {
        if (rows.length == 0)
            return new String[]{"Empty Table"};

        verifyValidity(rows);

        String[] lines = new String[rows.length + 2];

        TableHeader header = rows[0].getHeader();
        int[] longestByColumn = new int[header.headerCount()];

        // Find the longest per column
        for (TableRow row : rows) {
            int i = 0;
            for (String fieldValue : row.getCellValues()) {
                try {
                    longestByColumn[i] = Math.max(Math.max(longestByColumn[i], fieldValue.length()), header.getHeaderValues().get(i).length());
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
        lines[0] = String.join(" | ", paddedHeaders);
        // Dashes
        lines[1] = dashDivider(lines[0].length());

        // Generate rows
        int rowNumber = 2;
        for (TableRow row : rows) {
            String[] thisRow = new String[row.getCellValues().size()];
            for (int columnNum = 0; columnNum < row.getCellValues().size(); columnNum++) {
                thisRow[columnNum] = padToLength(row.getCellValues().get(columnNum), longestByColumn[columnNum]);
            }

            lines[rowNumber] = String.join(" | ", thisRow);
            rowNumber++;
        }

        return lines;
    }

    /**
     * Prints a {@code TableRow[]} to {@link System#out}
     *
     * @param rows {@code TableRow[]} of table rows of one type to print
     */
    public static void printRows(TableRow... rows) {
        printRows(System.out, rows);
    }

    /**
     * Prints a {@code TableRow[]} to a {@code PrintStream}
     *
     * @param stream {@code PrintStream} to print to
     * @param rows   {@code TableRow[]} of table rows of one type to print
     * @see PrintStream
     */
    public static void printRows(PrintStream stream, TableRow... rows) {
        String[] lines = rowsToStringTable(rows);
        stream.println(String.join("\n", lines));
    }

    /**
     * Creates an array of {@link TableRow TableRows} from an
     * array of {@link TableRowItem TableRowItems}
     *
     * @param rows Array of {@link TableRowItem TableRowItems}
     * @return Array of {@link TableRow TableRows}
     */
    public static TableRow[] objectsToRowArray(TableRowItem... rows) {
        return Stream.of(rows).map(RowFactory::makeRow).toArray(TableRow[]::new);
    }

    /**
     * Prints a {@link TableRowItem TableRowItems} to a
     * {@link PrintStream}
     *
     * @param stream {@link PrintStream} to print to
     * @param items  Array of {@link TableRowItem TableRowItems} to print
     */
    public static void printObjects(PrintStream stream, TableRowItem... items) {
        printRows(stream, objectsToRowArray(items));
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
     * @param input         String to pad
     * @param desiredLength Desired length of resulting string
     * @return Padded String
     */
    private static String padToLength(String input, int desiredLength) {
        if (input == null)
            input = "";

        int spacesNeeded = desiredLength - input.length();

        if (spacesNeeded < 0)
            return input;

        StringBuilder outputBuilder = new StringBuilder(spacesNeeded);
        for (int i = 0; i < spacesNeeded; i++) {
            outputBuilder.append(" ");
        }

        return input + outputBuilder.toString();
    }

    /**
     * @param columns Array of columns
     * @return The length of the longest column
     */
    private static int longestColumnLength(TableColumn... columns) {
        TableColumn longest = columns[0];
        for (TableColumn column : columns) {
            if (column.length() > longest.length()) {
                longest = column;
            }
        }

        return longest.length();
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

    private static void verifyValidity(TableRow... rows) {
        TableHeader checkHeader = rows[0].getHeader();
        for (int i = 1; i < rows.length; i++) {
            if (!rows[i].getHeader().equals(checkHeader)) {
                throw new IllegalArgumentException("TableRows don't share the same TableHeader");
            }
        }
    }

}

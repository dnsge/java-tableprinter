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

package org.dnsge.util.tableprinter.printer;

import org.dnsge.util.tableprinter.TableUtils;
import org.dnsge.util.tableprinter.row.TableRow;
import org.dnsge.util.tableprinter.table.Table;
import org.dnsge.util.tableprinter.table.TableAlignment;
import org.dnsge.util.tableprinter.table.TableHeader;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with static methods to print {@link Table} objects
 *
 * @author Daniel Sage
 * @version 2.0
 */
public class TablePrinter {

    private TablePrinter() {

    }

    /**
     * Creates a {@link List} of Strings of lines from a {@link Table}
     *
     * @param table {@link Table} to make the table from
     * @return {@link List} of Strings of lines
     * @see TableRow
     */
    public static List<String> tableToStringTable(Table table) {
        List<TableRow> data = table.getDataSource().getRowData();
        List<String> stringTable = new ArrayList<>();

        List<String> tableHeaderStringList = generateTableHeader(table);
        String tableHeaderString = String.join(" " + table.getVerticalSeparator() + " ", tableHeaderStringList);
        String horizontalDivider;
        if (table.useIntersectionChar()) {
            horizontalDivider = generateIntersectedDivider(table, tableHeaderStringList);
        } else {
            horizontalDivider = TableUtils.verticalDividerOf(tableHeaderString.length(), table.getVerticalSeparator());
        }
        stringTable.add(tableHeaderString);
        stringTable.add(horizontalDivider);

        for (int i = 0; i < data.size(); i++) {
            stringTable.add(generateRowOfTable(table, i));
        }

        return stringTable;
    }

    /**
     * Prints a {@link Table} to a {@link PrintStream}
     *
     * @param stream {@link PrintStream} to print to
     * @param table  {@link Table} to print
     */
    public static void printTable(PrintStream stream, Table table) {
        stream.println(String.join("\n", tableToStringTable(table)));
    }

    /**
     * Prints a {@link Table} to {@link System#out}
     *
     * @param table {@link Table} to print
     */
    public static void printTable(Table table) {
        printTable(System.out, table);
    }

    /**
     * Creates the table header for a {@link Table}
     *
     * @param table {@link Table} to make the table header for
     * @return {@link List} of Strings that represent each padded header item
     */
    private static List<String> generateTableHeader(Table table) {
        TableHeader header = table.getTableHeader();
        TableAlignment alignment = table.getTableAlignment();

        int[] longestByColumn = TableUtils.longestLengthByColumns(table.getDataSource().getRowData());
        List<String> strings = new ArrayList<>();

        int i = 0;
        for (String value : header.getHeaderValues()) {
            strings.add(TableUtils.padAccordingToAlignment(value, longestByColumn[i], alignment));
            i++;
        }

        return strings;
    }

    /**
     * Creates the string row of a {@link Table} at a certain row index
     *
     * @param table    {@link Table} to get the row from
     * @param rowIndex Index of the row to get
     * @return String representation of the certain row
     */
    private static String generateRowOfTable(Table table, int rowIndex) {
        TableAlignment alignment = table.getTableAlignment();

        int[] longestByColumn = TableUtils.longestLengthByColumns(table.getDataSource().getRowData());
        List<String> strings = new ArrayList<>();

        int i = 0;
        for (String value : table.getDataSource().getRowData().get(rowIndex).getCellValues()) {
            strings.add(TableUtils.padAccordingToAlignment(value, longestByColumn[i], alignment));
            i++;
        }

        return String.join(" " + table.getVerticalSeparator() + " ", strings);
    }

    /**
     * Creates an intersected divider for a {@link Table}
     *
     * @param table                 {@link Table} to make it for
     * @param tableHeaderStringList {@link List} of strings that represents the header
     * @return String of the vertical divider with intersected chars
     */
    private static String generateIntersectedDivider(Table table, List<String> tableHeaderStringList) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> horizontalSeparatorList = new ArrayList<>();

        for (String header : tableHeaderStringList) {
            for (int i = 0; i < header.length(); i++) {
                stringBuilder.append(table.getHorizontalSeparator());
            }
            horizontalSeparatorList.add(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }
        String sep = String.valueOf(table.getHorizontalSeparator()) + table.getIntersectionChar() + table.getHorizontalSeparator();
        return String.join(sep, horizontalSeparatorList);
    }

}

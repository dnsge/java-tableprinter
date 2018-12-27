package org.dnsge.util.tableprinter.printer;

import org.dnsge.util.tableprinter.TableUtils;
import org.dnsge.util.tableprinter.row.TableRow;
import org.dnsge.util.tableprinter.table.TableHeader;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class with static methods to print {@link TableRow} objects
 *
 * @author Daniel Sage
 * @version 2.0
 */
public class RowPrinter {

    private RowPrinter() {

    }

    /**
     * Creates a {@link List} of Strings of lines from some {@code TableRows}
     *
     * @param rows {@code TableRow[]} of table rows to make the table from
     * @return {@link List} of Strings of lines
     * @see TableRow
     */
    public static List<String> rowsToStringTable(TableRow... rows) {
        return rowsToStringTable(Arrays.asList(rows));
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
     * Prints a {@link List} of {@link TableRow TableRows} to {@link System#out}
     *
     * @param rows {@link List} of {@link TableRow TableRows} to print
     */
    public static void printRows(List<TableRow> rows) {
        printRows(System.out, rows);
    }

    /**
     * Prints a {@link List} of {@link TableRow TableRows} to a {@link PrintStream}
     *
     * @param stream {@link PrintStream} to print to
     * @param rows   {@link List} of {@link TableRow TableRows} to print
     */
    public static void printRows(PrintStream stream, List<TableRow> rows) {
        stream.println(String.join("\n", rowsToStringTable(rows)));
    }

    /**
     * Creates a {@link List} of Strings of lines from a {@link List} of {@link TableRow TableRows}. This method
     * is used by each printer method to create the string table.
     *
     * @param rows {@link List} of {@link TableRow TableRows} to use
     * @return {@link List} of Strings of lines
     */
    public static List<String> rowsToStringTable(List<TableRow> rows) {
        final char verticalBar = '|';
        final char horizontalBar = '-';

        if (rows.size() == 0)
            return List.of("Empty Table");

        TableUtils.verifyValidity(rows);

        List<String> lines = new ArrayList<>();

        TableHeader header = rows.get(0).getHeader();
        int[] longestByColumn = TableUtils.longestLengthByColumns(rows);
        List<String> paddedHeaders = new ArrayList<>();

        // Pad the headers
        int columnNumber = 0;
        for (String h : header.getHeaderValues()) {
            paddedHeaders.add(TableUtils.padToLength(h, longestByColumn[columnNumber]));
            columnNumber++;
        }

        // Header
        lines.add(String.join(" " + verticalBar + " ", paddedHeaders));
        // Dashes
        lines.add(TableUtils.verticalDividerOf(lines.get(0).length(), horizontalBar));

        // Generate string rows
        for (TableRow row : rows) {
            List<String> thisRow = new ArrayList<>();

            int columnNum = 0;
            for (String cellValue : row.getCellValues()) {
                thisRow.add(TableUtils.padToLength(cellValue, longestByColumn[columnNum++]));
            }

            lines.add(String.join(" " + verticalBar + " ", thisRow));
        }

        return lines;
    }

}

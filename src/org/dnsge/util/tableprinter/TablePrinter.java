package org.dnsge.util.tableprinter;

import org.dnsge.util.tableprinter.column.TableColumn;
import org.dnsge.util.tableprinter.row.RowMakeable;
import org.dnsge.util.tableprinter.row.TableRow;

import java.io.PrintStream;

/**
 * Class to print different objects in the form of a table
 *
 * @author Daniel Sage
 * @version 1.2
 */
public class TablePrinter {

    /**
     * Creates a {@code String[]} of lines from some {@code TableColumns}
     *
     * @param columns {@code TableColumn[]} of table columns to make the table from
     * @return {@code String[]} of the lines of the table
     * @see TableColumn
     */
    public static String[] columnsToStringTable(TableColumn... columns) {
        int columnNumber = 0;
        int longestColumnLength = longestColumnLength(columns);

        String[] lines = new String[longestColumnLength + 2];
        int[] longestByColumn = new int[columns.length];

        // Generate table header
        String[] header = new String[columns.length];
        for (TableColumn column : columns) {
            longestByColumn[columnNumber] = column.longestItemLength();
            header[columnNumber] = padToLength(column.getColumnName(), longestByColumn[columnNumber]);

            columnNumber++;
        }
        lines[0] = String.join(" | ", header);
        // Create header horizontal line
        lines[1] = dashDivider(lines[0].length());

        // Create lines for the rest of the table
        for (int lineNum = 0; lineNum < longestColumnLength; lineNum++) {
            StringBuilder sb = new StringBuilder();
            columnNumber = 0;
            for (TableColumn column : columns) {
                try {
                    if (column.get(lineNum) == null) {
                        sb.append(padToLength("", longestByColumn[columnNumber]));
                    } else {
                        sb.append(padToLength(column.get(lineNum).toString(), longestByColumn[columnNumber]));
                    }
                } catch (IndexOutOfBoundsException e) {
                    sb.append(padToLength("", longestByColumn[columnNumber]));
                }

                sb.append(" | ");
                columnNumber++;
            }
            sb.setLength(sb.length() - 2);
            lines[lineNum + 2] = sb.toString();
        }

        return lines;
    }

    /**
     * Prints a {@code TableColumn[]} to {@code System.out}
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

        String[] lines = new String[rows.length + 2];

        int columnCount = rows[0].getCellValues().length;
        int[] longestByColumn = new int[columnCount];
        String[] headers = rows[0].getHeaders();

        // Find the longest per column
        for (TableRow row : rows) {
            int i = 0;
            for (String fieldValue : row.getCellValues()) {
                try {
                    longestByColumn[i] = Math.max(Math.max(longestByColumn[i], fieldValue.length()), row.getHeaders()[i].length());
                } catch (NullPointerException ignored) { }
                i++;
            }
        }

        int columnNumber = 0;
        for (String header : headers) {
            headers[columnNumber] = padToLength(header, longestByColumn[columnNumber]);
            columnNumber++;
        }

        lines[0] = String.join(" | ", headers);
        lines[1] = dashDivider(lines[0].length());

        // Generate rows
        int rowNumber = 2;
        for (TableRow row : rows) {
            String[] thisRow = new String[row.getCellValues().length];
            for (int columnNum = 0; columnNum < row.getCellValues().length; columnNum++) {
                thisRow[columnNum] = padToLength(row.getCellValues()[columnNum], longestByColumn[columnNum]);
            }
            lines[rowNumber] = String.join(" | ", thisRow);
            rowNumber++;
        }

        return lines;
    }

    /**
     * Prints a {@code TableRow[]} to {@code System.out}
     *
     * @param rows {@code TableRow[]} of table rows of one type to print
     */
    public static void printRows(TableRow... rows) {
        printRows(System.out, rows);
    }

    /**
     * Prints a {@code TableRow[]} to a {@code PrintStream}
     *
     * @param stream  {@code PrintStream} to print to
     * @param rows {@code TableRow[]} of table rows of one type to print
     * @see PrintStream
     */
    public static void printRows(PrintStream stream, TableRow... rows) {
        String[] lines = rowsToStringTable(rows);
        stream.println(String.join("\n", lines));
    }

    /**
     * Creates a {@code String[]} of lines from some Objects that implement {@code RowMakeable}
     *
     * @param <T> Type of Objects being used
     * @param objects {@code RowMakeable[]} of {@code RowMakeable} Objects to make the table from
     * @return {@code String[]} of the lines of the table
     * @see RowMakeable
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <T extends RowMakeable> String[] objectsToStringTable(T... objects) {
        TableRow[] tableRows = new TableRow[objects.length];

        int rowNumber = 0;
        for (T object : objects) {
            tableRows[rowNumber] = new TableRow(object.makeRow());
            rowNumber++;
        }

        return rowsToStringTable(tableRows);
    }

    /**
     * Prints a {@code RowMakeable[]} to {@code System.out}
     *
     * @param <T> Type of Objects being used
     * @param objects {@code RowMakeable[]} of {@code RowMakeable} Objects to print
     * @see RowMakeable
     */
    @SafeVarargs
    public static <T extends RowMakeable> void printObjects(T... objects) {
        printObjects(System.out, objects);
    }

    /**
     * Prints a {@code RowMakeable[]} to a {@code PrintStream}
     *
     * @param <T> Type of Objects being used
     * @param stream  {@code PrintStream} to print to
     * @param objects {@code RowMakeable[]} of {@code RowMakeable} Objects to print
     * @see PrintStream
     * @see RowMakeable
     */
    @SafeVarargs
    public static <T extends RowMakeable> void printObjects(PrintStream stream, T... objects) {
        String[] lines = objectsToStringTable(objects);
        stream.println(String.join("\n", lines));
    }

    /**
     * @param input String to pad
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
        for (int i = 0; i < spacesNeeded; i++){
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
        for (int i = 0; i < length + 1; i++){
            dashDividerBuilder.append("-");
        }
        return dashDividerBuilder.toString();
    }

}

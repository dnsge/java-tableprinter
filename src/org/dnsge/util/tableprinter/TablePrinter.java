package org.dnsge.util.tableprinter;

import org.dnsge.util.tableprinter.column.TableColumn;
import org.dnsge.util.tableprinter.row.TableRow;
import org.dnsge.util.tableprinter.row.TableRowDetail;

public class TablePrinter {

    /**
     * @param columns Array of table columns to make the table from
     * @return String Array of the lines of the table
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
     * @param columns Array of table columns to print
     */
    public static void printColumns(TableColumn... columns) {
        String[] lines = columnsToStringTable(columns);
        System.out.println(String.join("\n", lines));
    }

    @SafeVarargs
    public static <T> String[] rowsToStringTable(TableRow<T>... rows) {
        if (rows.length == 0)
            return new String[]{"Empty Table"};

        String[] lines = new String[rows.length + 2];

        int columnCount = rows[0].getFieldValues().length;
        int[] longestByColumn = new int[columnCount];

        // Find the longest per column
        for (int columnNumb = 0; columnNumb < columnCount; columnNumb++) {
            longestByColumn[columnNumb] = rows[0].getGeneratedFroms()[columnNumb].getHeaderTitle().length();
            for (TableRow<T> row : rows) {
                try {
                    int thisLength = row.getFieldValues()[columnNumb].length();
                    if (thisLength > longestByColumn[columnNumb]) {
                        longestByColumn[columnNumb] = thisLength;
                    }
                } catch (NullPointerException ignored) {}
            }
        }

        String[] headers = new String[columnCount];
        int i = 0;
        for (TableRowDetail trd : rows[0].getGeneratedFroms()) {
            headers[i] = padToLength(trd.getHeaderTitle(), longestByColumn[i]);
            i++;
        }

        lines[0] = String.join(" | ", headers);
        lines[1] = dashDivider(lines[0].length());

        i = 2;
        for (TableRow<T> row : rows) {
            String[] thisRow = new String[row.getFieldValues().length];
            for (int columnNum = 0; columnNum < row.getFieldValues().length; columnNum++) {
                thisRow[columnNum] = padToLength(row.getFieldValues()[columnNum], longestByColumn[columnNum]);
            }
            lines[i] = String.join(" | ", thisRow);
            i++;
        }

        return lines;
    }

    @SafeVarargs
    public static <T> void printRows(TableRow<T>... rows) {
        String[] lines = rowsToStringTable(rows);
        System.out.println(String.join("\n", lines));
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

    private static String dashDivider(int length) {
        StringBuilder dashDividerBuilder = new StringBuilder(length);
        for (int i = 0; i < length + 1; i++){
            dashDividerBuilder.append("-");
        }
        return dashDividerBuilder.toString();
    }

}

package org.dnsge.util.tableprinter;

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

        StringBuilder headerBuilder = new StringBuilder();
        int charCounter = 0;

        // Generate table header
        for (TableColumn column : columns) {
            longestByColumn[columnNumber] = column.longestItemLength();

            String padded = padToLength(column.getColumnName(), longestByColumn[columnNumber]);

            headerBuilder.append(padded);
            headerBuilder.append(" | ");
            charCounter += padded.length() + 2;

            columnNumber++;
        }

        headerBuilder.setLength(headerBuilder.length() - 2);
        lines[0] = headerBuilder.toString();

        // Create header horizontal line
        StringBuilder headerLineBuilder = new StringBuilder(charCounter);
        for (int i = 0; i < charCounter + 1; i++){
            headerLineBuilder.append("-");
        }
        lines[1] = headerLineBuilder.toString();

        // Create lines for the rest of the table
        for (int lineNum = 0; lineNum < longestColumnLength; lineNum++) {
            StringBuilder sb = new StringBuilder();
            columnNumber = 0;
            for (TableColumn column : columns) {
                sb.append(padToLength(column.get(lineNum).toString(), longestByColumn[columnNumber]));
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

    /**
     * @param input String to pad
     * @param desiredLength Desired length of resulting string
     * @return Padded String
     */
    private static String padToLength(String input, int desiredLength) {
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



}

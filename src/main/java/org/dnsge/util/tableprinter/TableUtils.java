package org.dnsge.util.tableprinter;

import org.apache.commons.lang3.StringUtils;
import org.dnsge.util.tableprinter.row.TableRow;
import org.dnsge.util.tableprinter.table.TableAlignment;
import org.dnsge.util.tableprinter.table.TableHeader;

import java.util.Iterator;
import java.util.List;

/**
 * Class that contains static methods used to perform basic operations
 *
 * @author Daniel Sage
 * @version 2.0
 */
public class TableUtils {

    private TableUtils() {

    }

    /**
     * Pads a string with spaces at the end to a desired length (right padding)
     *
     * @param input         String to pad
     * @param desiredLength Desired length of resulting string
     * @return Padded String
     */
    public static String padToLength(String input, int desiredLength) {
        if (input == null)
            input = "";

        return StringUtils.rightPad(input, desiredLength);
    }

    /**
     * Creates an array of integers that represents the longest item by column number from a
     * {@link List} of {@link TableRow TableRows}
     *
     * @param rows {@link List} of {@link TableRow TableRows}
     * @return Array of ints representing the longest item by column
     */
    public static int[] longestLengthByColumns(List<TableRow> rows) {
        if (rows.size() == 0) {
            return new int[0];
        }

        TableHeader header = rows.get(0).getHeader();
        int[] longestByColumn = new int[header.headerCount()];

        for (TableRow row : rows) {
            int i = 0;
            for (String cellValue : row.getCellValues()) {
                try {
                    longestByColumn[i] = TableUtils.maxOfThree(
                            longestByColumn[i],
                            cellValue.length(),
                            header.getHeaderValues().get(i).length());
                } catch (NullPointerException ignored) {
                }
                i++;
            }
        }

        return longestByColumn;
    }

    /**
     * Pads a string to a certain length according to a {@link TableAlignment}
     *
     * @param toPad     String to pad
     * @param length    Length to pad to
     * @param alignment {@link TableAlignment} to pad according to
     * @return Padded String
     */
    public static String padAccordingToAlignment(String toPad, int length, TableAlignment alignment) {
        switch (alignment) {
            case LEFT:
                return StringUtils.rightPad(toPad, length);
            case CENTER:
                return StringUtils.center(toPad, length);
            case RIGHT:
                return StringUtils.leftPad(toPad, length);
        }
        return "";
    }

    /**
     * Creates a vertical divider of a specific length
     *
     * @param length        Length of desired vertical divider
     * @param horizontalBar Char to use in the bar
     * @return String of the vertical divider
     */
    public static String verticalDividerOf(int length, char horizontalBar) {
        StringBuilder dashDividerBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            dashDividerBuilder.append(horizontalBar);
        }
        return dashDividerBuilder.toString();
    }

    /**
     * Verifies that the {@link TableRow TableRows} all have the same {@link TableHeader}
     *
     * @param rows {@link List} of {@link TableRow TableRows} to verify
     * @throws IllegalArgumentException if not valid
     */
    public static void verifyValidity(List<TableRow> rows) {
        if (rows.size() == 0) {
            return;
        }
        
        TableHeader checkHeader = rows.get(0).getHeader();
        for (Iterator<TableRow> iterator = rows.listIterator(1); iterator.hasNext(); ) {
            if (!iterator.next().getHeader().equals(checkHeader)) {
                throw new IllegalArgumentException("TableRows don't share the same TableHeader");
            }
        }
    }

    /**
     * Gets the maximum of three numbers using {@link Math#max(int, int)}
     *
     * @param a int 1
     * @param b int 2
     * @param c int 3
     * @return Maximum of the three numbers
     */
    public static int maxOfThree(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

}

package org.dnsge.util.tableprinter.printer;

import org.dnsge.util.tableprinter.row.RowFactory;
import org.dnsge.util.tableprinter.row.TableRowItem;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/**
 * Class with static methods to print {@link TableRowItem} objects
 *
 * @author Daniel Sage
 * @version 2.0
 */
public class ObjectPrinter {

    private ObjectPrinter() {

    }

    /**
     * Creates a {@link List} of Strings of lines from a {@link List} of {@link TableRowItem TableRowItems}. This method
     * is used by each printer method to create the string table.
     *
     * @param items {@link List} of {@link TableRowItem TableRowItems} to use
     * @return {@link List} of Strings of lines
     */
    public static List<String> objectsToStringTable(List<TableRowItem> items) {
        return RowPrinter.rowsToStringTable(RowFactory.objectsToRowList(items));
    }

    /**
     * Creates a {@link List} of Strings of lines from some {@code TableRowItems}
     *
     * @param items {@code TableRowItem[]} of table row items to make the table from
     * @return {@link List} of Strings of lines
     * @see TableRowItem
     */
    public static List<String> objectsToStringTable(TableRowItem... items) {
        return objectsToStringTable(Arrays.asList(items));
    }

    /**
     * Prints a {@link TableRowItem TableRowItems} to a {@link PrintStream}
     *
     * @param stream {@link PrintStream} to print to
     * @param items  Array of {@link TableRowItem TableRowItems} to print
     */
    public static void printObjects(PrintStream stream, TableRowItem... items) {
        printObjects(stream, Arrays.asList(items));
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
     * Prints a {@link List} of {@link TableRowItem TableRowItems} to a {@link PrintStream}
     *
     * @param stream {@link PrintStream} to print to
     * @param items  {@link List} of {@link TableRowItem TableRowItems} to print
     */
    public static void printObjects(PrintStream stream, List<TableRowItem> items) {
        stream.println(String.join("\n", objectsToStringTable(items)));
    }

    /**
     * Prints a {@link List} of {@link TableRowItem TableRowItems} to {@link System#out}
     *
     * @param items {@link List} of {@link TableRowItem TableRowItems} to print
     */
    public static void printObjects(List<TableRowItem> items) {
        printObjects(System.out, items);
    }

}

package org.dnsge.util.tableprinter.printer;

import org.dnsge.util.tableprinter.column.TableColumn;
import org.dnsge.util.tableprinter.row.RowFactory;

import java.io.PrintStream;
import java.util.List;

/**
 * Class with static methods to print {@link TableColumn} objects
 *
 * @author Daniel Sage
 * @version 2.0
 */
public class ColumnPrinter {

    private ColumnPrinter() {

    }

    /**
     * Creates a {@link List} of Strings of lines from some {@link TableColumn TableColumns}
     *
     * @param columns {@code TableColumn[]} of table columns to make the table from
     * @return {@link List} of Strings of the lines of the table
     * @see TableColumn
     */
    public static List<String> columnsToStringTable(TableColumn... columns) {
        // Convert the columns to rows
        return RowPrinter.rowsToStringTable(RowFactory.makeRowsFromColumns(columns));
    }

    /**
     * Creates a {@link List} of Strings of lines from a {@link List} of {@link TableColumn TableColumns}
     *
     * @param columns {@link List} of {@link TableColumn TableColumns} to use
     * @return {@link List} of Strings of lines
     * @see TableColumn
     */
    public static List<String> columnsToStringTable(List<TableColumn> columns) {
        // Convert the columns to rows
        return RowPrinter.rowsToStringTable(RowFactory.makeRowsFromColumns(columns));
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
     * Prints a {@link List} of {@link TableColumn TableColumns} to a {@link PrintStream}
     *
     * @param stream  {@link PrintStream} to print to
     * @param columns {@link List} of {@link TableColumn TableColumns} to print
     */
    public static void printColumns(PrintStream stream, List<TableColumn> columns) {
        stream.println(String.join("\n", columnsToStringTable(columns)));
    }

    /**
     * Prints a {@link List} of {@link TableColumn TableColumns} to {@link System#out}
     *
     * @param columns {@link List} of {@link TableColumn TableColumns} to print
     */
    public static void printColumns(List<TableColumn> columns) {
        printColumns(System.out, columns);
    }

}

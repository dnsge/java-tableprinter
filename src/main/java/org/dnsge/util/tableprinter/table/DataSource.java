package org.dnsge.util.tableprinter.table;

import org.dnsge.util.tableprinter.TableUtils;
import org.dnsge.util.tableprinter.column.TableColumn;
import org.dnsge.util.tableprinter.row.RowFactory;
import org.dnsge.util.tableprinter.row.TableRow;

import java.util.Arrays;
import java.util.List;

/**
 * Class that represents a data source for a {@link Table} object
 *
 * @author Daniel Sage
 * @version 2.0
 */
public class DataSource {

    private List<TableRow> rowData;
    private TableHeader tableHeader;
    private boolean isEmpty;

    private DataSource() {

    }

    /**
     * Creates a DataSource from a {@link List} of {@link TableRow TableRows}
     *
     * @param rows {@link List} of {@link TableRow TableRows} to use
     * @return A new DataSource from the rows
     */
    public static DataSource fromRows(List<TableRow> rows) {
        DataSource ds = new DataSource();
        ds.setRowData(rows);

        return ds;
    }

    /**
     * Creates a DataSource from an array of {@link TableRow TableRows}
     *
     * @param rows Array of {@link TableRow TableRows} to use
     * @return A new DataSource from the rows
     */
    public static DataSource fromRows(TableRow... rows) {
        return fromRows(Arrays.asList(rows));
    }

    /**
     * Creates a DataSource from a {@link List} of {@link TableColumn TableColumns}
     *
     * @param columns {@link List} of {@link TableColumn TableColumns} to use
     * @return A new DataSource from the columns
     */
    public static DataSource fromColumns(List<TableColumn> columns) {
        DataSource ds = new DataSource();
        ds.setRowData(RowFactory.makeRowsFromColumns(columns));

        return ds;
    }

    /**
     * Creates a DataSource from an array of {@link TableColumn TableColumns}
     *
     * @param columns Array of {@link TableColumn TableColumns} to use
     * @return A new DataSource from the columns
     */
    public static DataSource fromColumns(TableColumn... columns) {
        return fromColumns(Arrays.asList(columns));
    }

    /**
     * Returns the data as a {@link List} of {@link TableRow TableRows}.
     *
     * @return The data as a {@link List} of {@link TableRow TableRows}
     */
    public List<TableRow> getRowData() {
        return rowData;
    }

    /**
     * Returns the data's {@link TableHeader}.
     *
     * @return The data's {@link TableHeader}
     */
    public TableHeader getTableHeader() {
        return tableHeader;
    }

    /**
     * Returns whether the data is empty.
     *
     * @return Whether the data is empty
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Sets the data
     *
     * @param rowData {@link List} of {@link TableRow TableRows} for the data
     */
    private void setRowData(List<TableRow> rowData) {
        TableUtils.verifyValidity(rowData);
        this.rowData = rowData;
        this.isEmpty = rowData.isEmpty();

        if (!isEmpty) {
            this.tableHeader = rowData.get(0).getHeader();
        }
    }

}

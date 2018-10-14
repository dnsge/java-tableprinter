package org.dnsge.util.tableprinter.column;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that represents a Column in a table
 *
 * @param <T> Type of elements in the {@code TableColumn}
 * @author Daniel Sage
 * @version 1.2
 */
public class TableColumn<T> {
    private final String columnName;
    private final ArrayList<T> columnData;

    /**
     * Create an empty {@code TableColumn} with a name
     *
     * @param columnName Name of the column
     */
    public TableColumn(String columnName) {
        this.columnName = columnName;
        this.columnData = new ArrayList<>();
    }

    /**
     * Create a {@code TableColumn} with an array of preexisting values
     *
     * @param columnName Name of the column
     * @param values Values in the column
     */
    public TableColumn(String columnName, T[] values) {
        this.columnName = columnName;
        this.columnData = new ArrayList<>(Arrays.asList(values));
    }

    /**
     * @param value Value to add to the bottom of the Column
     */
    public void add(T value) {
        columnData.add(value);
    }

    /**
     * @param index Index of item in column to get
     * @return Item at index
     */
    public T get(int index) {
        return columnData.get(index);
    }

    /**
     * @return Length of the longest string representation of the items
     */
    public int longestItemLength() {
        T longest = columnData.get(0);
        for(T item : columnData) {
            if (item == null)
                continue;

            if (item.toString().length() > longest.toString().length()) {
                longest = item;
            }
        }

        return longest.toString().length() > columnName.length() ? longest.toString().length() : columnName.length();
    }

    /**
     * @return name of the column
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @return data inside the column
     */
    public List<T> getColumnData() {
        return columnData;
    }

    /**
     * @return number of items in the column
     */
    public int length() {
        return columnData.size();
    }
}

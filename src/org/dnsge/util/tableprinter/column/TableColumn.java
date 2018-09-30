package org.dnsge.util.tableprinter.column;

import java.util.ArrayList;
import java.util.Arrays;

public class TableColumn<T> {
    private final String columnName;
    private final ArrayList<T> columnData;

    public TableColumn(String columnName) {
        this.columnName = columnName;
        this.columnData = new ArrayList<>();
    }

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

    public String getColumnName() {
        return columnName;
    }

    public ArrayList<T> getColumnData() {
        return columnData;
    }

    public int length() {
        return columnData.size();
    }
}

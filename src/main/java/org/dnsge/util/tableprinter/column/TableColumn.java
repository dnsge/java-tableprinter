/*
 * MIT License
 *
 * Copyright (c) 2018 Daniel Sage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.dnsge.util.tableprinter.column;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
    private final List<T> columnData;

    /**
     * Creates an empty {@code TableColumn} with a name
     *
     * @param columnName Name of the column
     */
    public TableColumn(String columnName) {
        this.columnName = columnName;
        this.columnData = new ArrayList<>();
    }

    /**
     * Creates a {@code TableColumn} with an array of preexisting values
     *
     * @param columnName Name of the column
     * @param values     Values in the column
     */
    @SafeVarargs
    public TableColumn(String columnName, T... values) {
        this(columnName, Arrays.asList(values));
    }

    /**
     * Creates a {@code TableColumn} with an array of preexisting values
     *
     * @param columnName Name of the column
     * @param values     Values in the column
     */
    public TableColumn(String columnName, Collection<T> values) {
        this.columnName = columnName;
        this.columnData = new ArrayList<>(values);
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
        for (T item : columnData) {
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

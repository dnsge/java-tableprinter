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

package org.dnsge.util.tableprinter.row;

import java.util.ArrayList;

/**
 * Factory to generate {@code TableRow} objects from a {@code RowGenerationSpecification}
 *
 * @param <T> Type of objects being fed in to the factory
 * @author Daniel Sage
 * @version 1.2
 *
 * @see TableRow
 * @see RowGenerationSpecification
 */
public final class TableRowFactory<T> {
    private final RowGenerationSpecification<T> specification;

    /**
     * Create a new Factory with the desired {@code RowGenerationSpecification}
     *
     * @param specification {@code RowGenerationSpecification} to be used when making the {@code TableRows}
     */
    public TableRowFactory(RowGenerationSpecification<T> specification) {
        this.specification = specification;
    }

    /**
     * Make a {@code TableRow} from an Object
     *
     * @param object Object to create the row from
     * @return {@code TableRow} representing the desired Object
     */
    public TableRow makeTableRow(T object) {
        String[] r = new String[specification.getTableValueFetchers().length];
        String[] headers = new String[specification.getTableValueFetchers().length];

        int i = 0;
        for (TableValueFetcher<T> field : specification.getTableValueFetchers()) {
            NameValue nv =  field.get(object);
            try {
                r[i] = nv.getValue().toString();
            } catch (NullPointerException e) {
                r[i] = null;
            }
            headers[i] = nv.getName();
            i++;
        }

        return new TableRow(r, headers);
    }

    /**
     * Make a {@code TableRow[]} from an Array of Objects
     *
     * @param objects Objects to create the rows from
     * @return {@code TableRow[]} of the desired Objects
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final TableRow[] makeTableRows(T... objects) {
        ArrayList<TableRow> r = new ArrayList<>();

        for (T object : objects) {
            r.add(makeTableRow(object));
        }

        return r.toArray(new TableRow[0]);
    }
}

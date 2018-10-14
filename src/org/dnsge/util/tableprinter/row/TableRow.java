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

/**
 * Class that represents a row in a table
 *
 * @author Daniel Sage
 * @version 1.2
 */
public final class TableRow {
    private final String[] cellValues;
    private final String[] headers;

    /**
     * Create a {@code TableRow} from predefined values and headers
     *
     * @param cellValues {@code String[]} of values for each cell in the row
     * @param headers {@code String[]} of headers for each cell in the row
     */
    public TableRow(String[] cellValues, String[] headers) {
        String[] fixedFieldValues = new String[cellValues.length];
        for (int i = 0; i < cellValues.length; i++) {
            fixedFieldValues[i] = cellValues[i] == null ? "" : cellValues[i];
        }

        this.cellValues = fixedFieldValues;
        this.headers = headers;
    }

    /**
     * Create a {@code TableRow} from a {@code NameValue[]} representing values and headers
     *
     * @param createFrom {@code NameValue[]} representing values and headers
     */
    public TableRow(NameValue[] createFrom) {
        cellValues = new String[createFrom.length];
        headers = new String[createFrom.length];

        int i = 0;
        for (NameValue nv : createFrom) {
            try {
                cellValues[i] = nv.getValue().toString();
            } catch (NullPointerException e) {
                cellValues[i] = "";
            }
            headers[i] = nv.getName();
            i++;
        }
    }

    /**
     * @return values of all cells
     */
    public String[] getCellValues() {
        return cellValues;
    }

    /**
     * @return headers of all cells
     */
    public String[] getHeaders() {
        return headers;
    }
}

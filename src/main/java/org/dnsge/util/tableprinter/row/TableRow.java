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

import org.dnsge.util.tableprinter.TableHeader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that represents a row in a table
 *
 * @author Daniel Sage
 * @version 1.2
 */
public final class TableRow {

    private final TableHeader header;
    private final List<String> cellValues;

    /**
     * Create a {@code TableRow} from predefined values and header
     *
     * @param header     {@link TableHeader} with header for each cell in the row
     * @param cellValues {@code String[]} of values for each cell in the row
     */
    public TableRow(TableHeader header, String[] cellValues) {
        this(header, Arrays.asList(cellValues));
    }

    /**
     * Create a {@code TableRow} from predefined values and header
     *
     * @param header     {@link TableHeader} with header for each cell in the row
     * @param cellValues {@link List<String>} of values for each cell in the row
     * @throws IllegalArgumentException if the number of header items != the number of cell values
     */
    public TableRow(TableHeader header, List<String> cellValues) {
        if (header.headerCount() != cellValues.size()) {
            throw new IllegalArgumentException("The number of header items is not the same as the number of cell values");
        }

        this.header = header;
        this.cellValues = new ArrayList<>(cellValues).stream().map(s -> s == null ? "" : s)
                .collect(Collectors.toList());
    }

    public TableRow(List<String> headerValues, List<String> cellValues) {
        this(new TableHeader(headerValues), cellValues);
    }

    /**
     * @return values of all cells
     */
    public List<String> getCellValues() {
        return cellValues;
    }

    /**
     * @return header of all cells
     */
    public TableHeader getHeader() {
        return header;
    }
}

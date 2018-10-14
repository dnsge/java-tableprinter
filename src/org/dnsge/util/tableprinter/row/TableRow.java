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

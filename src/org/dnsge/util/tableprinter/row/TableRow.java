package org.dnsge.util.tableprinter.row;

import java.util.Arrays;

public class TableRow<T> {
    private final T object;
    private final String[] fieldValues;
    private final TableRowDetail[] generatedFroms;

    public TableRow(T object, String[] fieldValues, TableRowDetail[] generatedFroms) {
        this.object = object;
        this.fieldValues = fieldValues;
        this.generatedFroms = generatedFroms;
    }

    public String[] getFieldValues() {
        return fieldValues;
    }

    public String getFieldValuesString() {
        return Arrays.toString(fieldValues);
    }

    public T getObject() {
        return object;
    }

    public TableRowDetail[] getGeneratedFroms() {
        return generatedFroms;
    }
}

package org.dnsge.util.tableprinter.row;

import java.util.Arrays;

public class TableRow<T> {
    private final T object;
    private final String[] fieldValues;
    private final RowConstructionSpecification generatedFroms;

    public TableRow(T object, String[] fieldValues, RowConstructionSpecification generatedFroms) {
        this.object = object;

        String[] fixedFieldValues = new String[fieldValues.length];
        for (int i = 0; i < fieldValues.length; i++) {
            fixedFieldValues[i] = fieldValues[i] == null ? "" : fieldValues[i];
        }

        this.fieldValues = fixedFieldValues;
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

    public RowConstructionSpecification getGeneratedFroms() {
        return generatedFroms;
    }
}

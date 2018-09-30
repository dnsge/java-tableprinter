package org.dnsge.util.tableprinter.row;

public class TableRowRawValue implements TableRowDetail {
    private final String headerTitle;
    private final String fieldValue;

    public TableRowRawValue(String headerTitle, String fieldValue) {
        this.headerTitle = headerTitle;
        this.fieldValue = fieldValue;
    }

    @Override
    public String apply(Object o) {
        return fieldValue;
    }

    @Override
    public String getHeaderTitle() {
        return headerTitle;
    }

    public String getFieldValue() {
        return fieldValue;
    }

}

package org.dnsge.util.tableprinter.row;

import java.lang.reflect.Field;

public class TableRowFieldValue implements TableRowDetail {
    private final String headerTitle;
    private final String fieldName;

    public TableRowFieldValue(String headerTitle, String fieldName) {
        this.headerTitle = headerTitle;
        this.fieldName = fieldName;
    }

    @Override
    public String apply(Object o) throws NoSuchFieldException, IllegalAccessException {
        Field f = o.getClass().getDeclaredField(fieldName);
        return f.get(o).toString();
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }
}

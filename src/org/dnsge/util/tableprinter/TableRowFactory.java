package org.dnsge.util.tableprinter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class TableRowFactory<T> {
    private final TableRowDetail[] fields;

    public TableRowFactory(TableRowDetail... fields) {
        this.fields = fields;
    }

    public TableRow<T> makeTableRow(T object) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String[] r = new String[fields.length];

        int i = 0;
        for (TableRowDetail field : fields) {
            try {
                r[i] = field.apply(object);
            } catch (NullPointerException e) {
                r[i] = null;
            }
            i++;
        }

        return new TableRow<>(object, r, fields);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final TableRow<T>[] makeTableRows(T... objects) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ArrayList<TableRow<T>> r = new ArrayList<>();

        for (T object : objects) {
            r.add(makeTableRow(object));
        }

        return r.toArray(new TableRow[0]);
    }
}

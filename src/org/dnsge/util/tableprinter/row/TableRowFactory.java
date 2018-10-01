package org.dnsge.util.tableprinter.row;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class TableRowFactory<T> {
    private final RowConstructionSpecification fields;

    public TableRowFactory(TableRowDetail... fields) {
        this.fields = new RowConstructionSpecification(fields);
    }

    public TableRow<T> makeTableRow(T object) {
        String[] r = new String[fields.length()];

        int i = 0;
        for (TableRowDetail field : fields.constructionSpecification) {
            try {
                r[i] = field.apply(object);
            } catch (NullPointerException e) {
                r[i] = null;
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                return null;
            }
            i++;
        }

        return new TableRow<>(object, r, fields);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final TableRow<T>[] makeTableRows(T... objects) {
        ArrayList<TableRow<T>> r = new ArrayList<>();

        for (T object : objects) {
            r.add(makeTableRow(object));
        }

        return r.toArray(new TableRow[0]);
    }

    public static <S extends RowConstructable> TableRow<S> makeRowFromObject(S object) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        RowConstructionSpecification specification = object.getConstructionSpecification();
        String[] r = new String[specification.length()];

        int i = 0;
        for (TableRowDetail field : specification.constructionSpecification) {
            try {
                r[i] = field.apply(object);
            } catch (NullPointerException e) {
                r[i] = null;
            }
            i++;
        }

        return new TableRow<>(object, r, specification);
    }
}

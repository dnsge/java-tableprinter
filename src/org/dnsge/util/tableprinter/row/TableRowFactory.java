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
     * @param specification {@code RowGenerationSpecification} to be used when making the {@code TableRow}s
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

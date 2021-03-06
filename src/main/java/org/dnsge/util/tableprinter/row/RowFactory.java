package org.dnsge.util.tableprinter.row;

import org.dnsge.util.tableprinter.column.TableColumn;
import org.dnsge.util.tableprinter.table.TableHeader;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class with static methods that creates {@link TableRow TableRows} from
 * different Objects
 *
 * @param <T> Type of objects that will be made into a {@link TableRow}
 * @author Daniel Sage
 * @version 2.0
 */
public class RowFactory<T> {

    private final RowMappingProtocol<T> protocol;

    /**
     * Creates a new RowFactory that creates rows based off of a {@link RowMappingProtocol}
     *
     * @param protocol {@link RowMappingProtocol} that will be used to create rows
     */
    public RowFactory(RowMappingProtocol<T> protocol) {
        this.protocol = protocol;
    }

    /**
     * Creates a {@link TableRow} from an object of type {@code T}
     *
     * @param object Object to make into a {@link TableRow}
     * @return new {@link TableRow}
     */
    public TableRow makeRow(T object) {
        return new TableRow(protocol.getTableHeader(), protocol.apply(object));
    }

    /**
     * Creates multiple {@link TableRow TableRows} from objects of type {@code T}
     *
     * @param objects Array of objects of type {@code T} to be made into rows
     * @return Array of new {@link TableRow TableRows}
     * @see #makeRow(Object)
     */
    @SafeVarargs
    public final List<TableRow> makeRows(T... objects) {
        return makeRows(Arrays.asList(objects));
    }

    public final List<TableRow> makeRows(List<T> objects) {
        List<TableRow> rowList = new ArrayList<>();
        for (T object : objects) {
            rowList.add(makeRow(object));
        }

        return rowList;
    }

    /**
     * Creates a new {@link TableRow} from a {@link TableRowItem} Object
     *
     * @param item {@link TableRowItem} to make the row from
     * @return he new {@link TableRow}T
     */
    public static TableRow makeRowFromTableRowItem(TableRowItem item) {
        List<String> headers = getHeaderDeclarations(item.getClass());
        List<String> values = getAnnotatedValues(item);

        return new TableRow(new TableHeader(headers), values);
    }

    /**
     * Creates an Array of {@link TableRow TableRows} from an Array of {@link TableRowItem TableRowItems}
     *
     * @param items {@link TableRowItem TableRowItems} to make the rows from
     * @return The new {@link TableRow TableRows}
     */
    public static List<TableRow> makeRowsFromColumns(TableColumn... items) {
        return RowFactory.makeRowsFromColumns(Arrays.asList(items));
    }

    /**
     * Creates a {@link List} of {@link TableRow TableRows} from
     * some {@link TableRowItem TableRowItems}
     *
     * @param rows Array of {@link TableRowItem TableRowItems}
     * @return {@link List} of {@link TableRow TableRows} from the {@link TableRowItem} array
     */
    public static List<TableRow> objectsToRowList(TableRowItem... rows) {
        return Stream.of(rows).map(RowFactory::makeRowFromTableRowItem).collect(Collectors.toList());
    }

    /**
     * Creates a {@link List} of {@link TableRow TableRows} from a {@link List} of {@link TableRowItem TableRowItems}
     *
     * @param rows {@link List} of {@link TableRowItem TableRowItems}
     * @return {@link List} of {@link TableRow TableRows} from the {@link TableRowItem} array
     */
    public static List<TableRow> objectsToRowList(List<TableRowItem> rows) {
        return rows.stream().map(RowFactory::makeRowFromTableRowItem).collect(Collectors.toList());
    }

    /**
     * Creates a {@link List} of {@link TableRow TableRows} from a {@link List} of {@link TableColumn TableColumns}
     *
     * @param items {@link List} of {@link TableColumn TableColumns}
     * @return {@link List} of {@link TableRow TableRows}
     */
    public static List<TableRow> makeRowsFromColumns(List<TableColumn> items) {
        if (items.size() == 0) {
            return List.of();
        }

        List<TableRow> rowList = new ArrayList<>();

        for (int i = 0; i < longestColumn(items).length(); i++) {
            rowList.add(makeRowFromColumns(i, items));
        }

        return rowList;
    }

    /**
     * Creates a {@link TableRow} from a {@link List} of {@link TableColumn TableColumns}
     * at a certain index
     *
     * @param index Row index to look at
     * @param items {@link List} of {@link TableColumn TableColumns} to make the row from
     * @return The new {@link TableRow}
     */
    private static TableRow makeRowFromColumns(int index, Collection<TableColumn> items) {
        List<String> headers = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (TableColumn tc : items) {
            headers.add(tc.getColumnName());
            try {
                values.add(Objects.requireNonNullElse(tc.get(index), "").toString());
            } catch (IndexOutOfBoundsException e) {
                values.add("");
            }

        }

        return new TableRow(new TableHeader(headers), values);
    }

    /**
     * Gets the longest {@link TableColumn} by {@link TableColumn#length()}
     *
     * @param items {@link List} of {@link TableColumn TableColumns} to look through
     * @return Longest {@link TableColumn}
     */
    private static TableColumn longestColumn(List<TableColumn> items) {
        TableColumn longest = null;
        for (TableColumn tc : items) {
            if (tc.length() >= (longest == null ? 0 : longest.length())) {
                longest = tc;
            }
        }
        return longest;
    }

    /**
     * Extracts the {@link TableItem} annotation header values from an {@link AccessibleObject}
     *
     * @param ao {@link AccessibleObject} to extract from
     * @return List of the annotation headers
     */
    private static List<String> extractAnnotationHeaders(AccessibleObject ao) {
        List<String> rList = new ArrayList<>();
        if (ao.isAnnotationPresent(TableItem.class)) {
            String header = ao.getAnnotation(TableItem.class).header();
            if (header.equals("")) {
                if (ao instanceof Field) {
                    rList.add(((Field) ao).getName());
                } else if (ao instanceof Method) {
                    rList.add(((Method) ao).getName());
                } else {
                    rList.add("");
                }
            } else {
                rList.add(header);
            }
        }

        return rList;
    }

    /**
     * Gets a list of header declarations using the {@link TableItem}
     * annotation
     *
     * @param clazz Class to look at
     * @return List of Strings representing the header names
     */
    private static List<String> getHeaderDeclarations(Class<?> clazz) {
        List<String> rList = new ArrayList<>();

        for (Field f : clazz.getDeclaredFields()) {
            rList.addAll(extractAnnotationHeaders(f));
        }

        for (Method m : clazz.getDeclaredMethods()) {
            rList.addAll(extractAnnotationHeaders(m));
        }

        return rList;
    }

    /**
     * Gets a List of Strings from values extracted from {@link TableItem} annotations
     *
     * @param obj Object to extract from
     * @return List of Strings detailing the values
     */
    private static List<String> getAnnotatedValues(Object obj) {
        List<String> rList = new ArrayList<>();

        for (Field f : obj.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(TableItem.class)) {
                f.setAccessible(true);
                Object o;

                try {
                    o = f.get(obj);
                } catch (IllegalAccessException ignored) {
                    continue;
                }

                if (o != null) {
                    rList.add(o.toString());
                } else {
                    rList.add("");
                }
            }
        }

        for (Method m : obj.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(TableItem.class)) {

                m.setAccessible(true);
                if (m.getParameterCount() == 0) {
                    Object result;
                    try {
                        result = m.invoke(obj);
                    } catch (IllegalAccessException | InvocationTargetException ignore) {
                        continue;
                    }
                    if (result != null) {
                        rList.add(result.toString());
                    } else {
                        rList.add("");
                    }
                } else {
                    throw new IllegalArgumentException(String.format("Methods annotated with TableItem can't have any parameters (Method %s in class %s)", m.getName(), obj.getClass().getName()));
                }
            }
        }

        return rList;
    }

}

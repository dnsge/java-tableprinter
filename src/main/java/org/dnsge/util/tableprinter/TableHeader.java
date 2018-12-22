package org.dnsge.util.tableprinter;

import java.util.*;

/**
 * Class that represents a header on a table
 *
 * @author Daniel Sage
 * @version 1.0
 */
public class TableHeader {

    private final List<String> headerValues;

    /**
     * Creates a new TableHeader
     *
     * @param headerValues {@code Collection} of Strings for values
     */
    public TableHeader(Collection<String> headerValues) {
        this.headerValues = new ArrayList<>(headerValues);
    }

    /**
     * Creates a new TableHeader
     *
     * @param headerValues Array of Strings for values
     */
    public TableHeader(String... headerValues) {
        this(Arrays.asList(headerValues));
    }

    /**
     * Returns number of headers held by this TableHeader.
     * @return number of headers held by this TableHeader
     */
    public int headerCount() {
        return headerValues.size();
    }

    /**
     * Returns a list of the header values
     * @return a list of the header values
     */
    public List<String> getHeaderValues() {
        return headerValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableHeader that = (TableHeader) o;
        return Objects.equals(headerValues, that.headerValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headerValues);
    }

}

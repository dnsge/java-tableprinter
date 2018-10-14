package org.dnsge.util.tableprinter.row;

/**
 * Class that specifies how to generate a row from a {@code TableValueFetcher[]}
 *
 * @param <T> Type of Object to be used with this specification
 * @author Daniel Sage
 * @version 1.2
 */
public final class RowGenerationSpecification<T> {
    private TableValueFetcher<T>[] tableValueFetchers;

    /**
     * Create a {@code RowGenerationSpecification} from the desired {@code TableValueFetchers}
     *
     * @param tableValueFetchers {@code TableValueFetcher[]} of fetchers to use
     * @see TableValueFetcher
     */
    @SafeVarargs
    public RowGenerationSpecification (TableValueFetcher<T>... tableValueFetchers) {
        this.tableValueFetchers = tableValueFetchers;
    }

    /**
     * @return the {@code TableValueFetcher[]} for this specification
     */
    public TableValueFetcher<T>[] getTableValueFetchers() {
        return tableValueFetchers;
    }

}

package org.dnsge.util.tableprinter.row;

import org.dnsge.util.tableprinter.TableHeader;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Specifies how a {@link RowFactory} should make a {@link TableRow}
 * from an object of type {@code T}
 *
 * @param <T> Type of object that is used as input
 * @author Daniel Sage
 * @version 1.0
 */
public class RowMappingProtocol<T> {

    private TableHeader tableHeader;
    private Function<T, List<?>> mappingFucntion;

    /**
     * Creates a new RowMappingProtocol with a {@link TableHeader} and a
     * {@link Function} that takes an input of type {@code T} and returns a
     * list of Objects
     *
     * @param tableHeader {@link TableHeader}
     * @param mappingFucntion {@link Function} that maps an object of type {@code T}
     */
    public RowMappingProtocol(TableHeader tableHeader, Function<T, List<?>> mappingFucntion) {
        this.tableHeader = tableHeader;
        this.mappingFucntion = mappingFucntion;
    }

    /**
     * Applies the mapping function onto an object of type {@code T}
     *
     * @param input Object to map from
     * @return {@link List<String>} of results (Object::toString is called on each result)
     */
    public List<String> apply(T input) {
        List<?> applied = mappingFucntion.apply(input);
        return applied.stream().map(o -> o == null ? "" : o)
                .map(Object::toString).collect(Collectors.toList());
    }

    /**
     * Returns the {@link TableHeader} for this RowMappingProtocol.
     *
     * @return the {@link TableHeader} for this RowMappingProtocol
     */
    public TableHeader getTableHeader() {
        return tableHeader;
    }

}

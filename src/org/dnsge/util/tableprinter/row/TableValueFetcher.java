package org.dnsge.util.tableprinter.row;

/**
 * Interface that is used to get a {@code NameValue} from an Object
 *
 * @param <T> Type of object to fetch a value from
 * @author Daniel Sage
 * @version 1.2
 * @see NameValue
 */
public interface TableValueFetcher<T> {

    /**
     * Gets a {@code NameValue} from an Object
     *
     * @param object Object to use to get the value from
     * @return a {@code NameValue} pair from the Object
     * @see NameValue
     */
    NameValue get(T object);

}

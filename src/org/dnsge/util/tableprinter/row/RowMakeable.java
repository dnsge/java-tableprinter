package org.dnsge.util.tableprinter.row;

/**
 * Interface that is used to create a {@code TableRow} from an Object
 *
 * @author Daniel Sage
 * @version 1.2
 * @see NameValue
 * @see TableRow
 */
public interface RowMakeable {

    /**
     * Gets a {@code NameValue[]} holding the values needed to make a {@code TableRow}
     *
     * @return {@code NameValue[]} with desired values
     * @see NameValue
     * @see TableRow
     */
    NameValue[] makeRow();

}

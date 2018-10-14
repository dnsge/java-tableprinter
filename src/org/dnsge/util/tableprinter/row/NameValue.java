package org.dnsge.util.tableprinter.row;

/**
 * Class that represents a pair of a {@code String} name and a value
 *
 * @param <T> Type of the value
 * @author Daniel Sage
 * @version 1.2
 */
public final class NameValue<T> {

    private String name;
    private T value;

    /**
     * Create a NameValue from a {@code String} name and {@code T} value
     *
     * @param name Name of NameValue
     * @param value Value of NameValue
     */
    public NameValue(String name, T value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return the NameValue's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the NameValue's value
     */
    public T getValue() {
        return value;
    }

    /**
     * Creates a NameValue without the {@code new} operator
     *
     * @param name Name of new {@code NameValue}
     * @param value Value of new {@code NameValue}
     * @param <V> Type of the value
     * @return new {@code NameValue} object
     */
    public static <V> NameValue<V> nameValue(String name, V value) {
        return new NameValue<>(name, value);
    }
}

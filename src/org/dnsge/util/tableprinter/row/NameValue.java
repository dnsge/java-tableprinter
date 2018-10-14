/*
 * MIT License
 *
 * Copyright (c) 2018 Daniel Sage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

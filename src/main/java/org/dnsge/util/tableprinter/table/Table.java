package org.dnsge.util.tableprinter.table;

/**
 * Class that represents a table that can be printed. Essentially a bean
 *
 * @author Daniel Sage
 * @version 2.0
 */
public class Table {

    private final DataSource dataSource;
    private final TableHeader tableHeader;
    private TableAlignment tableAlignment;

    private char verticalSeparator = '│';
    private char horizontalSeparator = '─';
    private char intersectionChar = '┼';
    private boolean useIntersectionChar = true;

    /**
     * Creates a new Table from a {@link DataSource} object
     *
     * @param dataSource {@link DataSource} object to use
     */
    public Table(DataSource dataSource) {
        this.dataSource = dataSource;
        this.tableHeader = dataSource.getTableHeader();
        this.tableAlignment = TableAlignment.LEFT;
    }

    /**
     * Returns this Table's {@link DataSource}.
     *
     * @return This Table's {@link DataSource}
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Returns this Table's {@link TableAlignment}.
     *
     * @return This Table's {@link TableAlignment}
     */
    public TableAlignment getTableAlignment() {
        return tableAlignment;
    }

    /**
     * Sets this Table's {@link TableAlignment}.
     *
     * @param tableAlignment The new {@link TableAlignment}
     */
    public void setTableAlignment(TableAlignment tableAlignment) {
        this.tableAlignment = tableAlignment;
    }

    /**
     * Returns this Table's {@link TableHeader}.
     *
     * @return This Table's {@link TableHeader}
     */
    public TableHeader getTableHeader() {
        return tableHeader;
    }

    /**
     * Returns this Table's char that is used as a horizontal separator. Default is '─'.
     *
     * @return This Table's horizontal separator
     */
    public char getHorizontalSeparator() {
        return horizontalSeparator;
    }

    /**
     * Sets this Table's char that is used as a horizontal separator.
     *
     * @param horizontalSeparator This Table's new horizontal separator
     */
    public void setHorizontalSeparator(char horizontalSeparator) {
        this.horizontalSeparator = horizontalSeparator;
    }

    /**
     * Returns this Table's char that is used as an intersection character. Default is '┼'.
     *
     * @return This Table's intersection character
     */
    public char getIntersectionChar() {
        return intersectionChar;
    }

    /**
     * Sets this Table's char that is used as an intersection character.
     *
     * @param intersectionChar This Table's new intersection character
     */
    public void setIntersectionChar(char intersectionChar) {
        this.intersectionChar = intersectionChar;
    }

    /**
     * Returns whether this Table should use a specialized intersection character (if true), or if
     * the Table should use the regular horizontal separator (if false).
     *
     * @return Whether this Table should use a specialized intersection character
     */
    public boolean useIntersectionChar() {
        return useIntersectionChar;
    }

    /**
     * Sets whether this Table should use a specialized intersection character.
     *
     * @param useIntersectionChar Whether this Table should use a specialized intersection character
     * @see #useIntersectionChar()
     */
    public void setUseIntersectionChar(boolean useIntersectionChar) {
        this.useIntersectionChar = useIntersectionChar;
    }

    /**
     * Returns this Table's char that is used as a vertical separator. Default is '│'.
     *
     * @return This Table's horizontal separator
     */
    public char getVerticalSeparator() {
        return verticalSeparator;
    }

    /**
     * Sets this Table's char that is used as a vertical separator.
     *
     * @param verticalSeparator This Table's new vertical separator
     */
    public void setVerticalSeparator(char verticalSeparator) {
        this.verticalSeparator = verticalSeparator;
    }

}

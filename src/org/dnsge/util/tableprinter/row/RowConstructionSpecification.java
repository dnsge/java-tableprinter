package org.dnsge.util.tableprinter.row;

public class RowConstructionSpecification {

    public final TableRowDetail[] constructionSpecification;
    public final static RowConstructionSpecification emptySpecification =
            new RowConstructionSpecification();

    public RowConstructionSpecification(TableRowDetail... constructionSpecification) {
        this.constructionSpecification = constructionSpecification;
    }

    public int length() {
        return constructionSpecification.length;
    }

}

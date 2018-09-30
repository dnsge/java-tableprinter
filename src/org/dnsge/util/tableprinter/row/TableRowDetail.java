package org.dnsge.util.tableprinter.row;

import java.lang.reflect.InvocationTargetException;

public interface TableRowDetail {

    String headerTitle = "Untitled";
    String apply(Object o)
            throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
    String getHeaderTitle();

}

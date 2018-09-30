package org.dnsge.util.tableprinter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TableRowMethodResult<T> implements TableRowDetail {

    private final String headerTitle;
    private final String methodName;
    private final T[] arguments;

    @SafeVarargs
    public TableRowMethodResult(String headerTitle, String methodName, T... arguments) {
        this.headerTitle = headerTitle;
        this.methodName = methodName;
        this.arguments = arguments;
    }

    public TableRowMethodResult(String headerTitle, String methodName) {
        this.headerTitle = headerTitle;
        this.methodName = methodName;
        this.arguments = null;
    }

    @Override
    public String apply(Object o) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (arguments != null) {
            Class[] argumentsClass = new Class[arguments.length];

            int i = 0;
            for (T argument : arguments) {
                argumentsClass[i] = argument.getClass();
                i++;
            }

            Method m = o.getClass().getDeclaredMethod(methodName, argumentsClass);
            m.setAccessible(true);
            return m.invoke(o, (Object[])arguments).toString();
        } else {
            Method m = o.getClass().getDeclaredMethod(methodName);
            m.setAccessible(true);
            return m.invoke(o).toString();
        }
    }

    public String getFieldName() {
        return methodName;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

}

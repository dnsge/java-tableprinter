package org.dnsge.util.tableprinter.row;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that details a field/method that can be used to
 * populate a {@link TableRow} Object
 *
 * @author Daniel Sage
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface TableItem {

    /**
     * Specifies the header of the column for the field/method this
     * Annotation is detailing
     *
     * @return Header name
     */
    String header() default "";

}

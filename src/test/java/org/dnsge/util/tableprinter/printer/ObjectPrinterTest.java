package org.dnsge.util.tableprinter.printer;

import org.dnsge.util.tableprinter.row.TableItem;
import org.dnsge.util.tableprinter.row.TableRowItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObjectPrinterTest {

    private Person p1, p2, p3;

    class Person implements TableRowItem {

        @TableItem(header = "Name")
        final String name;
        @TableItem(header = "Age")
        public final Integer age;
        @TableItem(header = "Workplace")
        public final String workplace;
        private final String password;

        public Person(String name, Integer age, String workplace, String password) {
            this.name = name;
            this.age = age;
            this.workplace = workplace;
            this.password = password;
        }

        @TableItem(header = "Birth year")
        public int birthYear() {
            return 2018 - age;
        }

    }

    @BeforeEach
    void setup() {
        p1 = new Person("Amelia", 28, "Google", "secretPassword");
        p2 = new Person("David", 16, null, "abc12345");
        p3 = new Person("Joey", 63, "Disney", "abc");
    }

    @Test
    void objectsToStringTable() {
        assertEquals(List.of(
                "Name   | Age | Workplace | Birth year",
                "-------------------------------------",
                "Amelia | 28  | Google    | 1990      ",
                "David  | 16  |           | 2002      ",
                "Joey   | 63  | Disney    | 1955      "
        ), ObjectPrinter.objectsToStringTable(p1, p2, p3));
    }

}

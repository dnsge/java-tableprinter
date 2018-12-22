package org.dnsge.util.tableprinter.row;

import org.dnsge.util.tableprinter.TableHeader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RowFactoryTest {

    static class Person implements TableRowItem {

        @TableItem(header = "Name")
        final String name;
        @TableItem(header = "Age")
        public final Integer age;
        @TableItem(header = "Workplace")
        public final String workplace;
        @TableItem(header = "Password!")
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

    @Test
    void makeRow() {
        Person person1 = new Person("Joe", 45, "Google", "abc123!$!");
        Person person2 = new Person("Jeff", 1, null, null);

        TableRow row1 = RowFactory.makeRow(person1);
        TableRow row2 = RowFactory.makeRow(person2);

        assertEquals(Arrays.asList("Joe", "45", "Google", "abc123!$!", "1973"), row1.getCellValues(),
                "Cell values are incorrect");
        assertEquals(Arrays.asList("Jeff", "1", "", "", "2017"), row2.getCellValues(),
                "Cell values are incorrect");
        assertEquals(new TableHeader("Name", "Age", "Workplace", "Password!", "Birth year"), row1.getHeader(),
                "Row headers are incorrect");

    }

    @Test
    @DisplayName("makeRow() fail")
    void makeRowFail() {
        class Fail implements TableRowItem {

            Fail() {
            }

            @TableItem
            public int takesArgs(int a) {
                return 0;
            }

        }

        assertThrows(IllegalArgumentException.class, () ->
                RowFactory.makeRow(new Fail()), "Row creation passed with methods w/ args");
    }

}
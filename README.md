# java-tableprinter
*Generate formatted tables with easy-to-read formatting*

 - Allows the use of Generics for ease of use
 - Simple
 - Custom headers
 - Supports empty cells
 - Make tables from rows or from columns

Column Example: 

```java
import org.dnsge.util.tableprinter.column.TableColumn;
import org.dnsge.util.tableprinter.TablePrinter;

public class TableColumnUsage {

    public static void main(String[] args) {

        TableColumn<String> nameColumn = new TableColumn<>("Name", new String[]{
                "George W. Bush",
                "Barack Obama",
                "Donald Trump",
                null,
                "Taylor Swift",
        });

        TableColumn<Integer> numberColumn = new TableColumn<>("#", new Integer[] {
                43,
                44,
                45,
                47,
                48,
        });

        TableColumn<String> partyColumn = new TableColumn<>("Party", new String[]{
                "Republican",
                "Democrat",
                "Republican",
                "Democrat",
                "Independent",
        });

        TableColumn<String> birthLocation = new TableColumn<>("Birthplace", new String[]{
                "New Haven, CT",
                "Honolulu, HI",
                "New York City, NY",
                null,
                "Reading, PA",
        });

        TablePrinter.printColumns(numberColumn, nameColumn, partyColumn, birthLocation);
    }

}
```
Output: 

```
#  | Name           | Party       | Birthplace
-----------------------------------------------------
43 | George W. Bush | Republican  | New Haven, CT
44 | Barack Obama   | Democrat    | Honolulu, HI
45 | Donald Trump   | Republican  | New York City, NY
47 |                | Democrat    |
48 | Taylor Swift   | Independent | Reading, PA
```

Row Example:
```java
import org.dnsge.util.tableprinter.TablePrinter;
import org.dnsge.util.tableprinter.row.TableRowFactory;
import org.dnsge.util.tableprinter.row.field.TableRowFieldValue;
import org.dnsge.util.tableprinter.row.field.TableRowMethodResult;

import java.lang.reflect.InvocationTargetException;

public class TableRowUsage {
    
    public static class Person {
        public final String name;
        public final int age;
        public final String workplace;
        private final int secretId;

        public Person(String name, int age, String workplace, int secretId) {
            this.name = name;
            this.age = age;
            this.workplace = workplace;
            this.secretId = secretId;
        }

        public int getSecretId() {
            return secretId;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Person bobby = new Person("Bobby Bob", 28, "Google", 77744);
        Person jimmy = new Person("Jimmy Joe", null, "Apple", 12345);
        Person donald = new Person("Donald Duck", 63, "Disney", 65536);

        TableRowFactory<Person> tf = new TableRowFactory<>(
                new TableRowFieldValue("Name", "name"),
                new TableRowFieldValue("Age", "age"),
                new TableRowFieldValue("Workplace", "workplace"),
                new TableRowMethodResult<>("Secret ID", "getSecretId")
        );

        TableRow<Person>[] rows = tf.makeTableRows(bobby, jimmy, donald);
        TablePrinter.printRows(rows);
    }
    
}

```
Output:
```
Name        | Age | Workplace | Secret ID
------------------------------------------
Bobby Bob   | 28  | Google    | 77744    
Jimmy Joe   |     | Apple     | 12345    
Donald Duck | 63  | Disney    | 65536    
```

###### Made by Daniel Sage (2018)

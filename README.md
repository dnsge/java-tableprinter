# java-tableprinter
*Generate formatted tables with easy-to-read formatting*

 - Allows the use of Generics for ease of use
 - Simple
 - Custom headers
 - Supports empty cells

Example: 

```java
import org.dnsge.util.tableprinter.TableColumn;
import org.dnsge.util.tableprinter.TablePrinter;

public class TableUsage {

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

###### Made by Daniel Sage (2018)

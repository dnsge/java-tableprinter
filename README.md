# java-tableprinter
*Generate formatted tables with easy-to-read formatting*

 - Allows the use of Generics for ease of use
 - Simple
 - Custom headers


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
        });
        
        TableColumn<Integer> numberColumn = new TableColumn<>("#", new Integer[] {
                43,
                44,
                45,
        });

        TableColumn<String> partyColumn = new TableColumn<>("Party", new String[]{
                "Republican",
                "Democrat",
                "Republican",
        });

        TableColumn<String> birthLocation = new TableColumn<>("Birthplace", new String[]{
                "New Haven, CT",
                "Honolulu, HI",
                "New York City, NY",
        });

        TablePrinter.printColumns(numberColumn, nameColumn, partyColumn, birthLocation);
    }

}
```
Output: 

```
#  | Name           | Party      | Birthplace        
----------------------------------------------------
43 | George W. Bush | Republican | New Haven, CT     
44 | Barack Obama   | Democrat   | Honolulu, HI      
45 | Donald Trump   | Republican | New York City, NY 
```

###### Made by Daniel Sage (2018)

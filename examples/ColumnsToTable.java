import org.dnsge.util.tableprinter.column.TableColumn;
import org.dnsge.util.tableprinter.TablePrinter;

public class ColumnsToTable {

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
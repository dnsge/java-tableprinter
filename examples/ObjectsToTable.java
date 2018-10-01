import org.dnsge.util.tableprinter.TablePrinter;

import org.dnsge.util.tableprinter.row.RowConstructable;
import org.dnsge.util.tableprinter.row.RowConstructionSpecification;

import org.dnsge.util.tableprinter.row.field.TableRowFieldValue;
import org.dnsge.util.tableprinter.row.field.TableRowMethodResult;
import org.dnsge.util.tableprinter.row.field.TableRowRawValue;

public class ObjectsToTable {

    public static class Person implements RowConstructable {
        public final String name;
        public final Integer age;
        public final String workplace;
        private final int secretId;

        // Make sure that every RowConstructionSpecification is the same object!
        private static final RowConstructionSpecification personSpec = new RowConstructionSpecification(
                new TableRowFieldValue("Name", "name"),
                new TableRowFieldValue("Age", "age"),
                new TableRowFieldValue("Workplace", "workplace"),
                new TableRowMethodResult<>("Secret ID", "getSecretId"),
                new TableRowRawValue("Static Field", "Same across all!")
        );

        public RowConstructionSpecification getConstructionSpecification() {
            return personSpec;
        }

        public Person(String name, Integer age, String workplace, int secretId) {
            this.name = name;
            this.age = age;
            this.workplace = workplace;
            this.secretId = secretId;
        }

        public int getSecretId() {
            return secretId;
        }

    }

    public static void main(String[] args) {
        Person bobby = new Person("Bobby Bob", 28, "Google", 77744);
        Person jimmy = new Person("Little Jimmy", 6, null, 12345);
        Person donald = new Person("Donald Duck", 63, "Disney", 65536);

        TablePrinter.printObjectRows(bobby, jimmy, donald);
    }

}
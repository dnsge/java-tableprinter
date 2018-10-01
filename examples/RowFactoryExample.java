import org.dnsge.util.tableprinter.TablePrinter;
import org.dnsge.util.tableprinter.row.TableRow;
import org.dnsge.util.tableprinter.row.TableRowFactory;
import org.dnsge.util.tableprinter.row.field.TableRowFieldValue;
import org.dnsge.util.tableprinter.row.field.TableRowMethodResult;

public class RowFactoryExample {

    public static class Person {
        public final String name;
        public final Integer age;
        public final String workplace;

        public Person(String name, Integer age, String workplace) {
            this.name = name;
            this.age = age;
            this.workplace = workplace;
        }

    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Person pA = new Person("Person A", 28, "Google");
        Person pB = new Person("Person B", 16, null);
        Person pC = new Person("Person C", 63, "Disney");

        TableRowFactory<Person> tf = new TableRowFactory<>(
                new TableRowFieldValue("Name", "name"),
                new TableRowFieldValue("Age", "age"),
                new TableRowFieldValue("Workplace", "workplace")
        );

        TableRow<Person>[] rows = tf.makeTableRows(pA, pB, pC);
        TablePrinter.printRows(rows);
    }

}
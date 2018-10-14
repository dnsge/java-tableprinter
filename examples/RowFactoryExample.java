import org.dnsge.util.tableprinter.TablePrinter;
import org.dnsge.util.tableprinter.row.*;
import static org.dnsge.util.tableprinter.row.NameValue.nameValue;

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

        public int birthYear(int currentYear) {
            return currentYear - age;
        }

    }

    public static void main(String[] args) {
        RowGenerationSpecification<Person> rcs = new RowGenerationSpecification<>(
                (Person p) -> nameValue("Name", p.name),
                (Person p) -> nameValue("Age", String.valueOf(p.age)),
                (Person p) -> nameValue("Birth year", p.birthYear(2018)),
                (Person p) -> nameValue("Workplace", p.workplace)
        );

        Person pA = new Person("Person A", 28, "Google");
        Person pB = new Person("Person B", 16, null);
        Person pC = new Person("Person C", 63, "Disney");

        TableRowFactory<Person> tf = new TableRowFactory<>(rcs);
        TableRow[] rows = tf.makeTableRows(pA, pB, pC);

        TablePrinter.printRows(rows);
    }

}
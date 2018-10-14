import org.dnsge.util.tableprinter.TablePrinter;
import org.dnsge.util.tableprinter.row.NameValue;
import org.dnsge.util.tableprinter.row.RowMakeable;
import static org.dnsge.util.tableprinter.row.NameValue.nameValue;

public class ObjectsToTable {

    public static class Person implements RowMakeable {
        public final String name;
        public final Integer age;
        public final String workplace;
        private final String password;

        public Person(String name, Integer age, String workplace, String password) {
            this.name = name;
            this.age = age;
            this.workplace = workplace;
            this.password = password;
        }

        public int birthYear(int currentYear) {
            return currentYear - age;
        }

        @Override
        public NameValue[] makeRow() {
            return new NameValue[]{
                    nameValue("Age", age),
                    nameValue("Name", name),
                    nameValue("Birth year", birthYear(2018)),
                    nameValue("Workplace", workplace),
                    nameValue("Password", password)
            };
        }
    }

    public static void main(String[] args) {
        Person pA = new Person("Amelia", 28, "Google", "secretPassword");
        Person pB = new Person("David", 16, null, "abc12345");
        Person pC = new Person("Joey", 63, "Disney", "correcthorsebatterystaple");

        TablePrinter.printObjects(pA, pB, pC);
    }

}
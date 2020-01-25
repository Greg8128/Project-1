import java.util.*;

public class DynTest {
    public static void main(String args[]) {
        ArrayList<Person> m = new ArrayList<Person>();
        m.add(new Person("dim"));
        m.add(new Person("f"));
        m.add(new Person("m"));
        ArrayList<Person> clone = new ArrayList<Person>();
        for ( Person p : m) {
            clone.add(p);
        }

        clone.get(0).setFather(clone.get(1));
        clone.get(0).setMother(clone.get(2));
        clone.get(1).addChild(clone.get(0));
        clone.get(2).addChild(clone.get(0));
        m.get(0).sysOut();
        System.out.println(m.get(1).getChildren());
        System.out.println(m.get(2).getChildren());

    }
}

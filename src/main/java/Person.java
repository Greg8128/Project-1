import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.util.ArrayList;

public class Person {
    private String name;
    private Person father = null;
    private Person mother = null;
    private ArrayList<Person> children;

    public Person (String name){
        this.name = name;
        children = new ArrayList<Person>();
    }

    public String getName (){
        return name;
    }
    public String getFather(){ return father.getName(); }
    public void setFather(Person fat) {
        this.father = fat;
        father.addChild(this);
    }
    public String getMother() {
        return mother.getName();
    }
    public void setMother(Person mom) {
        this.mother = mom; mother.addChild(this);
    }
    public ArrayList<Person> getChildren (){
        return this.children;
    }
    public void addChild(Person kid) {
        this.children.add(kid);
    }
    public void removeChild(Person kid) {
        this.children.remove(kid);
    }

    public String toString() {
        return name;
    }

    public void sysOut() {
        System.out.println("Name  : " + name);
        System.out.println("Mother   : " + mother);
        System.out.println("Father   : " + father);
        System.out.println("Children : " + children + '\n');
    }
}

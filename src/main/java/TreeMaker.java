/* Team 1 : Sidney Bloch, Huantong Jenny Ji, Greogory Baimetov, Richard Ty
 * Task : parse a .dat file and create a family tree ( contrarily stored in an ArrayList )
 */

import java.io.*;
import java.util.*;

public class TreeMaker {

    public static final String SENTINEL = "END";
    private static ArrayList<Person> family;


    public static void main(String args[]) {
        try {
            /* The following block of code asks for a filePath to their family Tree .dat*/
            System.out.println("WARNING : THIS PROGRAM WILL RUN UNTIL IT IS TERMINATED" + '\n'
                    + "Remeber to Terminate it" + '\n');
            System.out.println("Enter File Path");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader rb = new BufferedReader(isr);
            String file_path = rb.readLine();

            ArrayList<String> file = scanFile(file_path);
            ArrayList<String> relations = splitList(file);
            treeMaker(relations);


            System.out.println("Family Size : " +family.size() + '\n');
            System.out.println("Searchable Family Members : " + '\n' + family);
            while(true) {
                /* the following lines of code ask the user to input the name of
                 * their desired family member searchPerson returns the person if
                 * they exist, and null if not found*/
                System.out.println('\n' + "Please enter the name of the family member : ");
                String name = rb.readLine();
                if (searchPerson(name) == null) {
                    System.out.println("Person not found, please try again"+'\n');
                } else {
                    searchPerson(name).sysOut();
                }
            }

        } catch (IOException ioe) {
            System.out.println("IO Exception Raised");
        }

    }


    /*
    * scanFile
    *       Parameters : String of the file Path
    *       Returns    : ArrayList of all of the lines in the file
    *                   each element is a separate line in the file
    *       Task : Scan the file and return a list whose elements are
    *          composed of individual lines in the file
    */

    public static ArrayList<String> scanFile(String file_Name) {
        ArrayList<String> file = new ArrayList<String>();
        try {
            Scanner input = new Scanner(new File(file_Name));
            while (input.hasNextLine()) {
                String n = input.nextLine();
                file.add(n);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return file;
    }

    /*
    * splitList
    *       Parameters : ArrayList of the file contents
    *       Return     : a list of the Child, Mother, Father relationships
    *       Task : this method takes the full file. creates a subList of the first section
    *          of the list and sends that subList off to be processed into people.
    *          the remaining list is then stripped of sentinel elements and returned
    */

    public static ArrayList<String> splitList(ArrayList<String> list) {
        createPeople(list.subList(0,list.indexOf(SENTINEL)));
        int inx = list.indexOf(SENTINEL)-1;
        for (int i = 0 ; i<= inx ; i++) {
            list.remove(0);
        }
        list.remove(SENTINEL);
        list.remove(SENTINEL);
        return list;
    }

    /*
    * createPeople
    *       Parameters : List of Strings
    *       Return     : void
    *       Task : This method parses the list of names.
    *          For each name in the list, A person is created
    *          and added to a global list which holds all of the family members
    */
    public static void createPeople(List<String> people) {
        family = new ArrayList<Person>();
        for ( String name : people ) {
            family.add(new Person(name));
        }
    }

    /* treeMaker
    *       Parameters : List of family relations in the form [child, mother, father, ...]
    *       Return     : Nothing
    *       Task : takes in a list of relations, parses the first 3 names in the list,
    *          then adds their family relations to the Person Objects in the global list
    */
    public static void treeMaker(ArrayList<String> lists) {
        for (int i = 0; i <= lists.size() - 2; i += 3) {
            Person c = getPerson(lists.get(i));
            Person m = getPerson(lists.get(i+1));
            Person f = getPerson(lists.get(i+2));
            addRelatives(c,m,f);
        }

    }

    /*
    * searchPerson
    *       Parameter : a String holding the seach name
    *       Return    : If found a Person Object
    *                   If not found 'null'
    *       Task : Search the list of family members. Return the person if found
    *          return null if otherwise
    */
    public static Person searchPerson (String name) {
        for (Person p : family) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    /*
    * getPerson
    *       Parameter : String of name
    *       Return    : Person p
    *       Task : Find a Person object If found, return Person
    *          if not found, return a new Person(name) instantiated with name
    */
    public static Person getPerson (String name ) {
        for (Person p : family) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return new Person(name);
    }

    /*
    * addRelatives
    *       Parameters : Person child, Person mother, Person Father
    *       Return     : void
    *       Task : add parent relationships to child.
    */
    public static void addRelatives(Person c, Person m, Person f) {
        c.setMother(m);
        c.setFather(f);
    }

}

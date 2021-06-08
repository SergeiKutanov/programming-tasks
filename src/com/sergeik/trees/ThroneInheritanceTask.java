package com.sergeik.trees;

import java.util.*;

/**
 * A kingdom consists of a king, his children, his grandchildren, and so on. Every once in a while, someone
 * in the family dies or a child is born.
 *
 * The kingdom has a well-defined order of inheritance that consists of the king as the first member.
 * Let's define the recursive function Successor(x, curOrder), which given a person x and the inheritance order
 * so far, returns who should be the next person after x in the order of inheritance.
 */
public class ThroneInheritanceTask {

    public static void main(String[] args) {
        ThroneInheritance t= new ThroneInheritance("king"); // order: king
        t.birth("king", "andy"); // order: king > andy
        t.birth("king", "bob"); // order: king > andy > bob
        t.birth("king", "catherine"); // order: king > andy > bob > catherine
        t.birth("andy", "matthew"); // order: king > andy > matthew > bob > catherine
        t.birth("bob", "alex"); // order: king > andy > matthew > bob > alex > catherine
        t.birth("bob", "asha"); // order: king > andy > matthew > bob > alex > asha > catherine
        assert Arrays.equals(
                new String[] {"king", "andy", "matthew", "bob", "alex", "asha", "catherine"},
                t.getInheritanceOrder().toArray()
        ); // return ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
        t.death("bob"); // order: king > andy > matthew > bob > alex > asha > catherine
        assert Arrays.equals(
                new String[] {"king", "andy", "matthew", "alex", "asha", "catherine"},
                t.getInheritanceOrder().toArray()
        ); // return ["king", "andy", "matthew", "alex", "asha", "catherine"]
    }

    static class ThroneInheritance {

        Person king;
        Map<String, Person> nav;

        public ThroneInheritance(String kingName) {
            king = new Person(kingName);
            nav = new HashMap<>();
            nav.put(kingName, king);
        }

        public void birth(String parentName, String childName) {
            Person person = nav.get(parentName);
            if (person != null) {
                Person p = new Person(childName);
                nav.put(childName, p);
                person.children.add(p);
            }

        }

        public void death(String name) {
            Person person = nav.get(name);
            if (person != null)
                person.isDead = true;
        }

        public List<String> getInheritanceOrder() {
            List<String> res = new LinkedList<>();
            king.getChildren(res);
            return res;
        }

        private class Person {
            boolean isDead = false;
            String name;
            List<Person> children;

            Person(String name) {
                this.name = name;
                children = new LinkedList<>();
            }

            Person find(String name) {
                if (this.name.equals(name))
                    return this;
                for (Person child: children) {
                    Person p = child.find(name);
                    if (p != null)
                        return p;
                }
                return null;
            }

            void getChildren(List<String> res) {
                if (!isDead)
                    res.add(name);
                for (Person child: children) {
                    child.getChildren(res);
                }
            }
        }
    }

}

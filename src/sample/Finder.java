package sample;

import sample.Molecule;

/**
 * Created by adeliya16 on 4/3/17.
 */
public class Finder {static public void main(String[] args) {
    try {
        Molecule m;
        m = new Molecule("H2O1");
        m.Print();
        double d = m.getWeight();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
}

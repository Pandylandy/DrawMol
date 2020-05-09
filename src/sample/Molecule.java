package sample;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by adeliya16 on 4/3/17.
 */
public class Molecule {
    double weight;
    ArrayList<Atom> m = new ArrayList<Atom>();
    ArrayList<ArrayList<Integer>> bonds = new ArrayList<>();

    public void new_bond(int a, int b) {
        ArrayList<Integer> bond = new ArrayList<>();
        bond.add(a);
        bond.add(b);
        this.bonds.add(bond);
    }

//    public static ArrayList<String> Get_symbols() throws Exception {
//        ArrayList<String> sym_list = new ArrayList<String>();
//        Scanner scan = new Scanner(new FileReader("src/sample/table"));
//        try {
//            while (true) {
//                String str = scan.nextLine();
//                String arr[] = str.split("&");
//                sym_list.add(arr[1]);
//
//            }
//        } catch (Exception ex) {
//        }
//        scan.close();
//        return sym_list;
//    }

    public String Print() {
        String out = "";
        for (int i = 0; i < m.size(); i++) {
            out = out + i + " " + m.get(i).Print() + "\n";
        }
        return out;
    }


    Molecule(String name) throws Exception {

        ArrayList el = new ArrayList();
        ArrayList<Integer> num = new ArrayList<Integer>();
        try {
            for (int i = 0; i < name.length(); ) {
                String elem = "", number = "";
                char els = name.charAt(i);
//                System.out.println("" + (int) els + " " + (int) '0' + " " + (int) '9');
                while (!(els >= '0' && els <= '9')) {
                    elem = elem + els;
                    i++;
                    els = name.charAt(i);
                }
                el.add(elem);
//                System.out.println(el);
//                System.out.println(els);

                while (i < name.length() && els >= '0' && els <= '9') {
                    number = number + els;
                    i++;
//                    System.out.println(number);
                    if (i < name.length()) els = name.charAt(i);
                }

                num.add(Integer.parseInt(number));
//                System.out.println("!!!" + els);
            }

            for (int k = 0; k < el.size(); k++) {
                String brutto = (String) el.get(k);
                int col = num.get(k);
                for (int c = 0; c < col; c++) {
                    m.add(new Atom(brutto));
                }
            }
        }
        catch (Exception ex) {
            throw new Exception("="+ ex.getMessage());
        }
    }



    public int Size() {
        int kol = m.size();
        return kol;
    }

    public String Atomname(int i) {
        return ((Atom) m.get(i)).getName();
    }

    public double Atomrad(int i) {
        return ((Atom) m.get(i)).getR();
    }

    public double getWeight()
    {
        return weight;
    }
}

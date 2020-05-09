package sample;

import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by adeliya16 on 4/3/17.
 */
public class Atom {
    int number;
    String symbol;
    int charge;
    double weight;
    int valency;
    String name;
    double rad;

    Atom(int num, String symbol, int ch, double w, int val) {
        number = num;
        charge = ch;
        weight = w;
        valency = val;
        this.symbol = symbol;
        this.rad = rad;
    }


    public String Print(){
        return "Atom: "+symbol+" Weight: "+weight;
    }


    public  String getName() {
        return name;
    }
    public  double getR()
    {
        return rad;
    }


    Atom(String symbol) throws Exception {
        Scanner scan = new Scanner(new FileReader("src/sample/table"));
        try {
            while (true) {
                String str = scan.nextLine();
                String arr[] = str.split("&");
                if (arr[1].equals(symbol)) {
                    this.number = Integer.parseInt(arr[0]);
                    this.symbol = symbol;
                    this.charge = Integer.parseInt(arr[2]);
                    this.weight = Integer.parseInt(arr[3]);
                    this.valency = Integer.parseInt(arr[4]);
                    break;
                }
            }
        } catch (Exception ex) {
            throw new Exception("...");
        }
    }
}

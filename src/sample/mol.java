package sample;
import java.util.ArrayList;

/**
 * Created by Alina on 14.05.2017.
 */
public class mol {

    protected ArrayList lel = new ArrayList();
    protected ArrayList<Integer> lint = new ArrayList();
    protected int col = 0;

    mol(String brutto) throws Exception {

        for (int i = 0; i < brutto.length();) {
            String elem="", num="";
            char el = brutto.charAt(i);
            while (!(el >= '0' && el <= '9')) {
                elem=elem+el; i++; el = brutto.charAt(i);
            }
            lel.add(elem);
            while(i<brutto.length() && (el >= '0' && el <= '9'))
            {
                num=num+el; i++;
                if(i<brutto.length()) el = brutto.charAt(i);
            }
            lint.add( Integer.parseInt(num));
        }

        for (int i =0; i<lint.size(); i++) {
            col += lint.get(i);
        }
    }

    public ArrayList print_element() {
        System.out.println(lel);
        return lel;
    }

    public ArrayList<Integer> print_col() {
        System.out.println(lint);
        return lint;
    }

    public int get_col() {
        System.out.println(col);
        return col;
    }


}
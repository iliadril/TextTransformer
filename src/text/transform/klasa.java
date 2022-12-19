package text.transform;

import java.util.ArrayList;
import java.util.Locale;

public class klasa implements text_transform{

    String zmienna0;


    klasa(String zmienna0) {
        this.zmienna0 = zmienna0;
    }


    public static void main(String[] args) {
        String x = "MirEk";
        klasa obj = new klasa(x);
        //System.out.print("upper " + obj.upper(x) + "\n");
        //System.out.print("lower " + obj.lower(x) + "\n");
        //System.out.print(obj.capitalize(x));
        System.out.print(obj.inverse(x));

    }

    public String upper(String x) {
        if (x.length() != 0) {
            return x.toUpperCase();
        }
        return String.valueOf(0);
    }

    public String lower(String x) {
        if (x.length() != 0) {
            return x.toLowerCase();
        }
        return String.valueOf(0);
    }

    public String capitalize(String x) {
        x.strip();
        if (x.length() == 0) return String.valueOf(0);
        x = ChangeLetter(x, 0);
        //x = pomoc(x, 4);
        for (int i = 0; i < x.length(); i++) {
            if(x.charAt(i) == ' '){
                if((int) x.charAt(i+1) <= 122 && (int) x.charAt(i+1) >= 97) {
                    x = ChangeLetter(x, i+1);
                }
            }
        }
        return x;
    }

    public String ChangeLetter(String x, Integer i){ // funkcja wykorzystywana w capitalize;
        String s1 = x.substring(i, i+1).toUpperCase();
        String s2 = x.substring(0, i);
        x = s2 + s1 + x.substring(i+1);
        return x;
    }

    public String inverse(String x){
        x.strip();
        if(x.length() == 0) return "0";
        int len = x.length();
        ArrayList<Boolean> index = new ArrayList<>();
        ArrayList<Character> characters = new ArrayList<>();
        for(int i = 0; i<len; i++){
            if((int) x.charAt(i) <= 122 && (int) x.charAt(i) >= 97) index.add(false); // mala
            else index.add(true);
            characters.add(x.charAt(i));
        }
        x = "";
        int k = 1;
        for(int i = 0; i<len; i++){
            if(index.get(i)){
                if((int)characters.get(characters.size() - k) >= 65 && (int)characters.get(characters.size() - k) <= 90){
                    x += (characters.get(characters.size() - k));
                }
                else{
                    x += (characters.get(characters.size() - k)).toString().toUpperCase();
                }
            }
            else{
                if((int)characters.get(characters.size() - k) >= 65 && (int)characters.get(characters.size() - k) <= 90){
                    x += (characters.get(characters.size() - k)).toString().toLowerCase();
                }
                else{
                    x += (characters.get(characters.size() - k));
                }
            }
            k++;
        }
        return x;
    }

    private String Delete_strings(String x) {
        String NewString = "";
        String[] parts = x.split(" ");
        ArrayList<String> list = new ArrayList();
        int j = parts.length;

        int i;
        for(i = 0; i < j; ++i) {
            list.add(parts[i]);
        }

        for(i = 0; i < list.size(); ++i) {
            this.tak(list);
        }

        NewString = this.help_with_delete(list);
        return NewString;
    }

    public String help_with_delete(ArrayList<String> list) {
        String NewString = "";

        for(int i = 0; i < list.size(); ++i) {
            NewString = NewString + (String)list.get(i) + " ";
        }

        return NewString;
    }

    public String tak(ArrayList<String> list) {
        for(int i = 0; i < list.size(); ++i) {
            if (i > 0 && Objects.equals(list.get(i), list.get(i - 1))) {
                list.remove(list.get(i));
            }
        }

        String x = this.help_with_delete(list);
        return x;
    }
}

package pl.put.poznan.texttransformer.logic.impl;

import org.springframework.stereotype.Component;
import pl.put.poznan.texttransformer.logic.TextTransformer;

import java.util.ArrayList;

@Component
public class TextTransformerImpl implements TextTransformer {

    @Override
    public String upper(String x) {
        if (x.length() != 0) {
            return x.toUpperCase();
        }
        return String.valueOf(0);
    }

    @Override
    public String lower(String x) {
        if (x.length() != 0) {
            return x.toLowerCase();
        }
        return String.valueOf(0);
    }

    public String changeLetter(String x, Integer i){

        String s1 = x.substring(i, i+1).toUpperCase();
        String s2 = x.substring(0, i);
        x = s2 + s1 + x.substring(i+1);
        return x;
    }

    @Override
    public String capitalize(String x) {
        x = x.strip();
        if (x.length() == 0) return String.valueOf(0);
        x = changeLetter(x, 0);
        for (int i = 0; i < x.length(); i++) {
            if(x.charAt(i) == ' '){
                if((int) x.charAt(i+1) <= 122 && (int) x.charAt(i+1) >= 97) {
                    x = changeLetter(x, i+1);
                }
            }
        }
        return x;
    }

    @Override
    public String inverse(String x) {
        x = x.strip();
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

    @Override
    public String deleteRepetitions(String x) {
        return null;
    }
}

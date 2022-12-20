package pl.put.poznan.texttransformer.logic.impl;

import org.springframework.stereotype.Component;
import pl.put.poznan.texttransformer.logic.TextTransformer;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class TextTransformerImpl implements TextTransformer {

    /**
     * upper - function to convert all letters to uppercase
     * @param text - given word that we want to change
     * @return - returns word that contains only Uppercase.
     */
    @Override
    public String upper(String text) {
        if (text.length() != 0) {
            return text.toUpperCase();
        }
        return String.valueOf(0);
    }

    /**
     * lower - function to convert all letters to lowercase
     * @param text - given word that we want to change
     * @return - returns word that contains only Lowercase.
     */
    @Override
    public String lower(String text) {
        if (text.length() != 0) {
            return text.toLowerCase();
        }
        return String.valueOf(0);
    }

    /**
     * capitalize - function responsible for changing the first letter of each word to uppercase
     * @param x - given ward that we want to change
     * @return - returns word after changes, (with every first letter of each word to uppercase)
     */
    @Override
    public String capitalize(String x) {
        x = x.strip();
        if (x.length() == 0) return String.valueOf(0);
        x = changeLetter(x, 0);
        for (int i = 0; i < x.length(); i++) {
            if(x.charAt(i) == ' '){
                if(x.charAt(i+1) <= 122 && x.charAt(i+1) >= 97) {
                    x = changeLetter(x, i+1);
                }
            }
        }
        return x;
    }

    /**
     * changeLetter - function that changes letter to uppercase.
     * @param text - String given from function capitalize
     * @param i - position of letter that we want to change
     * @return returns changed word
     */
    public String changeLetter(String text, Integer i){

        String s1 = text.substring(i, i+1).toUpperCase();
        String s2 = text.substring(0, i);
        text = s2 + s1 + text.substring(i+1);
        return text;
    }

    /**
     * inverse - function that is designed to change the order of characters to the opposite order,
     * while maintaining the case of the letters in the appropriate positions
     * @param x - given String that we want to change.
     * @return returns changed word in opposite order with case-sensitive in the positions
     */
    @Override
    public String inverse(String x) {
        x = x.strip();
        if(x.length() == 0) return "0";
        int len = x.length();
        ArrayList<Boolean> index = new ArrayList<>();
        ArrayList<Character> characters = new ArrayList<>();
        for(int i = 0; i<len; i++){
            if(x.charAt(i) <= 122 && x.charAt(i) >= 97) index.add(false); // mala
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

    /**
     * deleteRepetitions - function responsible for removing repetitions that are in close correlation next to each other
     * @param x - String in which we want to delte repetitions
     * @return function returns word without repetitions.
     */
    @Override
    public String deleteRepetitions(String x) {
        String newString = "";
        String[] parts = x.split(" ");
        ArrayList<String> list = new ArrayList();
        int j = parts.length;

        int i;
        for(i = 0; i < j; ++i) {
            list.add(parts[i]);
        }

        for(i = 0; i < list.size(); ++i) {
            this.deleteWord(list);
        }

        newString = this.SetNewString(list);
        return newString;
    }

    /**
     * SetNewString - function used in deleteRepetitions. This function is making New String of given splitted strings in list
     * @param list - list of Strings taken from function deleteRepetitions.
     * @return - returns New String of given splitted strings in list
     */
    public String SetNewString(ArrayList<String> list) {
        String newString = "";
        for (String s : list) {
            newString = newString + s + " ";
        }
        return newString;
    }

    /**
     * delete_word - function used in deleteRepetitions. Responisble for removing word that has repetition. Is called many times
     * to make sure that there's no more repetitons (2+)
     * @param list - list of Strings taken from function deleteRepetitions.
     * @return - returns String without repetition (if function found repetition)
     */
    public String deleteWord(ArrayList<String> list) {
        for(int i = 0; i < list.size(); ++i) {
            if (i > 0 && Objects.equals(list.get(i), list.get(i - 1))) {
                list.remove(list.get(i));
            }
        }

        return this.SetNewString(list);
    }
}

package pl.put.poznan.texttransformer.logic.impl;

import pl.put.poznan.texttransformer.logic.NumberTransformer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.ArrayList;

public class NumberTransformerImpl implements NumberTransformer {
    private final Hashtable<Integer, String> digitDict;
    private final ArrayList<Integer> dictKeys;

    /**
     * Number2TextTransformer - constructor method
     *
     * @param String filename - file path to dictionary containing text forms of digits
     * @throws IOException exception when trying to access a file that does not exist
     */
    public Number2TextTransformer(String filename) throws IOException{
        digitDict = new Hashtable<>();
        dictKeys = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        while(reader.ready())
        {
            line = reader.readLine();

            int spaceIdx = line.indexOf(" ");
            if (spaceIdx != -1) {
                Integer key = Integer.parseInt(line.substring(0, spaceIdx));
                String value = line.substring(spaceIdx + 1);
                digitDict.put(key, value);
                dictKeys.add(key);
            }
        }

        dictKeys.sort(Collections.reverseOrder());
        reader.close();
    }

    /**
     * transform - transforms digit into text. Example: For input '100' output would be 'one hundred'
     *
     * @param Integer digit - digit that should be changed into text
     * @return returns String text - digit expressed using text
     */
    @Override
    public String transform(Integer digit) {
        StringBuilder text = new StringBuilder();
        for (Integer key : dictKeys){
            if (digit >= key)
            {
                text.append(" ").append(digitDict.get(key));
                digit -= key;
            }
        }

        return text.toString();
    }
}

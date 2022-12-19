package pl.put.poznan.transformer.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.ArrayList;


public class Number2TextTransformer {

    private final Hashtable<Integer, String> digitDict;
    private final ArrayList<Integer> dictKeys;

    public Number2TextTransformer(String filename) throws IOException{
        digitDict = new Hashtable<Integer, String>();
        dictKeys = new ArrayList<Integer>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        while(reader.ready())
        {
            line = reader.readLine();

            int spaceIdx = line.indexOf(" ");
            if (spaceIdx != -1) {
                Integer key = Integer.parseInt(line.substring(0, spaceIdx));
                String value = line.substring(spaceIdx + 1, line.length());
                digitDict.put(key, value);
                dictKeys.add(key);
            }
        }

        dictKeys.sort(Collections.reverseOrder());
        reader.close();
    }

    public String transform(Integer digit) {
        String text = "";
        for (Integer key : dictKeys){
            if (digit >= key)
            {
                text = text + " " + digitDict.get(key);
                digit -= key;
            }
        }

        return text;
    }
}

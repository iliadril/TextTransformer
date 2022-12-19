package pl.put.poznan.transformer.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;


public class AbbreviationsTransformer {

    private Hashtable<String, String> long2short_dict;
    private Hashtable<String, String> short2long_dict;

    public AbbreviationsTransformer(String filename) throws IOException{
        long2short_dict = new Hashtable<String, String>();
        short2long_dict = new Hashtable<String, String>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        while(reader.ready())
        {
            line = reader.readLine();

            int spaceIdx = line.indexOf(" ");
            if (spaceIdx != -1) {
                String shortForm = line.substring(0, spaceIdx);
                String longForm = line.substring(spaceIdx + 1, line.length());
                short2long_dict.put(shortForm, longForm);
                long2short_dict.put(longForm, shortForm);
            }
        }

        reader.close();
    }

    public String transform2Full(String original) {
        String transformed = original;
        Set<String> dictKeys = short2long_dict.keySet();

        for (String key : dictKeys){
            if (transformed.contains(key))
            {
                transformed = transformed.replaceAll(key, short2long_dict.get(key));
            }
        }

        return transformed;
    }

    public String transform2Short(String original) {
        String transformed = original;
        Set<String> dictKeys = long2short_dict.keySet();

        for (String key : dictKeys){
            if (transformed.contains(key))
            {
                transformed = transformed.replaceAll(key, long2short_dict.get(key));
            }
        }

        return transformed;
    }
}

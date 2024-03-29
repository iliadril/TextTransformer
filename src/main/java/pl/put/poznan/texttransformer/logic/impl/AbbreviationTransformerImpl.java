package pl.put.poznan.texttransformer.logic.impl;

import pl.put.poznan.texttransformer.logic.AbbreviationTransformer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class AbbreviationTransformerImpl implements AbbreviationTransformer {
    private final HashMap<String, String> long2shortDict;
    private final HashMap<String, String> short2longDict;

    /**
     * AbbreviationsTransformer - constructor method
     *
     * @param filename - file path to dictionary containing acronyms and full forms of predefined words
     * @throws IOException exception when trying to access a file that does not exist
     */
    public AbbreviationTransformerImpl(String filename) throws IOException{
        long2shortDict = new HashMap<>();
        short2longDict = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        while(reader.ready())
        {
            line = reader.readLine();

            int spaceIdx = line.indexOf(" ");
            if (spaceIdx != -1) {
                String shortForm = line.substring(0, spaceIdx);
                String longForm = line.substring(spaceIdx + 1);
                short2longDict.put(shortForm, longForm);
                long2shortDict.put(longForm, shortForm);
            }
        }

        reader.close();
    }

    /**
     * transform2Full - transforms acronyms into full strings. Example: For input 'FBI' output would be 'Federal Bureau of Investigation'
     *
     * @param original - string that contains text that should be transformed
     * @return returns String transformed - string that contains text without acronyms
     */
    @Override
    public String transform2Full(String original) {
        String transformed = original;
        Set<String> dictKeys = short2longDict.keySet();

        for (String key : dictKeys){
            if (transformed.contains(key))
            {
                transformed = transformed.replaceAll(key, short2longDict.get(key));
            }
        }

        return transformed;
    }


    /**
     * transform2Short - transforms strings into acronyms. Example: For input 'Federal Bureau of Investigation' output would be 'FBI'
     *
     * @param original - string that contains text that should be transformed
     * @return returns String transformed - string that contains text with acronyms instead of full forms
     */
    @Override
    public String transform2Short(String original) {
        String transformed = original;
        Set<String> dictKeys = long2shortDict.keySet();

        for (String key : dictKeys){
            if (transformed.contains(key))
            {
                transformed = transformed.replaceAll(key, long2shortDict.get(key));
            }
        }

        return transformed;
    }
}

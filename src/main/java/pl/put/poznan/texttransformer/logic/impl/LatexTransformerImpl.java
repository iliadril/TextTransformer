package pl.put.poznan.texttransformer.logic.impl;

import pl.put.poznan.texttransformer.logic.LatexTransformer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LatexTransformerImpl implements LatexTransformer {
    private final ArrayList<String> latexSpecialCharsDict;

    /**
     * LatexFriendlyTransformer - constructor method
     *
     * @param filename - file path to dictionary containing LaTeX special characters
     * @throws IOException exception when trying to access a file that does not exist
     */
    public LatexTransformerImpl(String filename) throws IOException{
        latexSpecialCharsDict = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        while(reader.ready())
        {
            latexSpecialCharsDict.add(reader.readLine().replaceAll("\\s",""));
        }

        reader.close();
    }

    /**
     * transform - transforms text into LaTeX-friendly format. Example: For input '&' output would be '\&'
     *
     * @param original - string that contains text that should be transformed
     * @return returns String transformed - string that contains transformed, LaTeX-friendly text
     */
    @Override
    public String transform(String original) {
        String transformed = original;

        for (String key : latexSpecialCharsDict){
            if (transformed.contains(key))
                transformed = transformed.replaceAll( key, "\\\\" + key);
        }

        return transformed;
    }
}

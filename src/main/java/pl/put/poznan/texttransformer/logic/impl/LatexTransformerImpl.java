package pl.put.poznan.texttransformer.logic.impl;

import pl.put.poznan.texttransformer.logic.LatexTransformer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LatexTransformerImpl implements LatexTransformer {
    private final ArrayList<String> latexSpecialChars_dict;

    /**
     * LatexFriendlyTransformer - constructor method
     *
     * @param String filename - file path to dictionary containing LaTeX special characters
     * @throws IOException exception when trying to access a file that does not exist
     */
    public LatexFriendlyTransformer(String filename) throws IOException{
        latexSpecialChars_dict = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        while(reader.ready())
        {
            latexSpecialChars_dict.add(reader.readLine().replaceAll("\\s",""));
        }

        reader.close();
    }

    /**
     * transform - transforms text into LaTeX-friendly format. Example: For input '&' output would be '\&'
     *
     * @param String original - string that contains text that should be transformed
     * @return returns String transformed - string that contains transformed, LaTeX-friendly text
     */
    @Override
    public String transform(String original) {
        String transformed = original;

        for (String key : latexSpecialChars_dict){
            if (transformed.contains(key))
                transformed = transformed.replaceAll( key, "\\\\" + key);
        }

        return transformed;
    }
}

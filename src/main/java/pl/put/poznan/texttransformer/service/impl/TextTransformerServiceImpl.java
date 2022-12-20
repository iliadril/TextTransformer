package pl.put.poznan.texttransformer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.poznan.texttransformer.logic.AbbreviationTransformer;
import pl.put.poznan.texttransformer.logic.LatexTransformer;
import pl.put.poznan.texttransformer.logic.NumberTransformer;
import pl.put.poznan.texttransformer.logic.TextTransformer;
import pl.put.poznan.texttransformer.logic.impl.AbbreviationTransformerImpl;
import pl.put.poznan.texttransformer.logic.impl.LatexTransformerImpl;
import pl.put.poznan.texttransformer.logic.impl.NumberTransformerImpl;
import pl.put.poznan.texttransformer.service.TextTransformerService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class TextTransformerServiceImpl implements TextTransformerService {

    public static final Logger logger = LoggerFactory.getLogger(TextTransformerServiceImpl.class);

    @Autowired
    TextTransformer textTransformer;
    AbbreviationTransformer abbreviationTransformer = new AbbreviationTransformerImpl("src/main/resources/dict_Short2Full.txt");
    LatexTransformer latexTransformer = new LatexTransformerImpl("src/main/resources/latex_special_chars.txt");
    NumberTransformer numberTransformer = new NumberTransformerImpl("src/main/resources/dict_Int2Str.txt");



    List<String> availableModes = Arrays.asList(
            "upper", "lower", "capitalize", "inverse", "deleteRepetitions"
    );

    public TextTransformerServiceImpl() throws IOException {
        // this constructor is empty simply because I'm lazy and exhausted, sorry for that
    }

    @Override
    public String transform(String transformType, String text) {
        List<String> transformations = this.parseTransformations(transformType);
        logger.info("transforming a string using {}", String.join(", ", transformations));
        for (String t : transformations) {
            text = this.applyTransformation(t, text);
        }
        return null;
    }

    private String applyTransformation(String transformation, String text) {
        switch (transformation) {
            case "upper" -> {
                return textTransformer.upper(text);
            }
            case "lower" -> {
                return textTransformer.lower(text);
            }
            case "capitalize" -> {
                return textTransformer.capitalize(text);
            }
            case "inverse" -> {
                return textTransformer.inverse(text);
            }
            case "deleteRepetitions" -> {
                return textTransformer.deleteRepetitions(text);
            }
            default -> logger.warn("no method provided for {}", transformation);
        }
        return null;
    }

    private List<String> parseTransformations(String transformType) {
        List<String> transformations = List.of(transformType.strip().split(","));
        return transformations.stream()
                .map(String::toLowerCase)
                .filter(method -> availableModes.stream().anyMatch(method::equals))
                .toList();
    }
}

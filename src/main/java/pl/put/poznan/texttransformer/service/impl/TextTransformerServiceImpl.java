package pl.put.poznan.texttransformer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.poznan.texttransformer.logic.TextTransformer;
import pl.put.poznan.texttransformer.service.TextTransformerService;

import java.util.Arrays;
import java.util.List;

@Service
public class TextTransformerServiceImpl implements TextTransformerService {

    public static final Logger logger = LoggerFactory.getLogger(TextTransformerServiceImpl.class);

    @Autowired
    TextTransformer textTransformer;

    List<String> availableModes = Arrays.asList(
            "upper", "lower", "capitalize", "inverse", "deleteRepetitions"
    );

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

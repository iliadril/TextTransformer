package pl.put.poznan.texttransformer.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.put.poznan.texttransformer.logic.TextTransformer;
import pl.put.poznan.texttransformer.service.TextTransformerService;

@Service
@AllArgsConstructor
public class TextTransformerServiceImpl implements TextTransformerService {

    TextTransformer textTransformer;


    @Override
    public String transform(String type, String text) {
        return null;
    }
}

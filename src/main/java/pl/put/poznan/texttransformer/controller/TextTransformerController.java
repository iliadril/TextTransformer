package pl.put.poznan.texttransformer.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.texttransformer.service.TextTransformerService;

@RestController
@RequestMapping("/{text}")
@AllArgsConstructor
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    private TextTransformerService textTransformerService;

    @GetMapping(produces = "application/json")
    public String get(@PathVariable String text,
                      @RequestParam(value="transforms", defaultValue="upper,inverse") String transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(transforms);

        // perform the transformation, you should run your logic here, below is just a silly example
        return textTransformerService.transform(transforms, text);
    }

    @PostMapping(produces = "application/json")
    public String post(@PathVariable String text,
                       @RequestBody String transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(transforms);

        // perform the transformation, you should run your logic here, below is just a silly example
        return textTransformerService.transform(transforms, text);
    }

}

package pl.put.poznan.texttransformer.logic;

public interface AbbreviationTransformer {
    String transform2Full(String original);
    String transform2Short(String original);
}

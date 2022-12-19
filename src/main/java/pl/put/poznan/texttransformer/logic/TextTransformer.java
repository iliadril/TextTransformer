package pl.put.poznan.texttransformer.logic;

public interface TextTransformer {

    String upper(String x);
    String lower(String x);
    String capitalize(String x);
    String inverse(String x);
}
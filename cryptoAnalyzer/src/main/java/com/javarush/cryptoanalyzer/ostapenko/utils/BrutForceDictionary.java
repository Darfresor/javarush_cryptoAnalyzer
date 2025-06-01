package com.javarush.cryptoanalyzer.ostapenko.utils;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

public class BrutForceDictionary {
    private static HashSet<String> dictionary;
    private static final String DICTIONARY_PATH = "com/javarush/cryptoanalyzer/ostapenko/cryptoanalyzer/russian_top_words.json";
    private static final String ERROR_TEXT ="Ошибка при загрузке словаря:";
    static {
        dictionary = loadDictionary();
    }


    private BrutForceDictionary() {
    }

    public static HashSet<String> getDictionary() {
        return dictionary;
    }

    private static HashSet<String> loadDictionary() {
        try(InputStream inputStream = BrutForceDictionary.class.getClassLoader().getResourceAsStream(DICTIONARY_PATH)){
            ObjectMapper mapper = new ObjectMapper();
            List<String> words = mapper.readValue(inputStream, new TypeReference<List<String>>() {
            });
            dictionary = new HashSet<>(words);
        } catch (RuntimeException e) {
            System.err.println(ERROR_TEXT);
            e.printStackTrace();
        } catch (StreamReadException e) {
            System.err.println(ERROR_TEXT);
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            System.err.println(ERROR_TEXT);
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println(ERROR_TEXT);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        ;
    return dictionary;
    }
}

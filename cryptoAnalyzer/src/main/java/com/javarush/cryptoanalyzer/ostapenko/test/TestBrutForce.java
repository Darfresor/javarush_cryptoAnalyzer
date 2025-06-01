package com.javarush.cryptoanalyzer.ostapenko.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

public class TestBrutForce {
    public static void main(String[] args) {
        HashSet<String> DICTIONARY = null;
        try {
            // 1. Создаём ObjectMapper (основной класс Jackson)
            ObjectMapper mapper = new ObjectMapper();

            // 2. Загружаем JSON-файл в Java-объект
            InputStream inputStream = TestBrutForce.class.getClassLoader()
                    .getResourceAsStream("com/javarush/cryptoanalyzer/ostapenko/cryptoanalyzer/russian_top_words.json");

            List<String> words = mapper.readValue(inputStream, new TypeReference<List<String>>() {});
            DICTIONARY = new HashSet<>(words);
            System.out.println(DICTIONARY);


        } catch (Exception e) {
            System.err.println("Ошибка при загрузке словаря:");
            e.printStackTrace();
        };
        /*
        //-------------------------------------------
        Cipher cipher = new Cipher();
        String result;
        String bestResult = "";
        int bestNumberOfMatches = 0;
        int numberOfMatches = 0;
        int finalkey = 0;
        for (int i = 0; i < 32; i++) {
            numberOfMatches = 0;
            result = cipher.decrypt("Яфр фжуф к рп струфрл!", i);
            String[] wordsinRelust = result.split("\\s+");
            for (String s : wordsinRelust) {
                if (DICTIONARY.contains(s.toLowerCase())) {
                    System.out.printf("При работе с ключом = %d, слово \"%s\" найдено в словаре %n", i, s);
                    numberOfMatches++;
                }
            }
            if (numberOfMatches > bestNumberOfMatches) {
                bestNumberOfMatches = numberOfMatches;
                bestResult = result;
                finalkey = i;
            }
        }

        System.out.println("Наибольшое кол-во сопавдений = " + bestNumberOfMatches);
        System.out.println("При ключе  = " + finalkey);
        System.out.println(bestResult);

        */
    }

}

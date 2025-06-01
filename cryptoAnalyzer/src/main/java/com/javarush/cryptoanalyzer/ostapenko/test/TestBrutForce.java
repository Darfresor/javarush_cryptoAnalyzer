package com.javarush.cryptoanalyzer.ostapenko.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.cryptoanalyzer.ostapenko.utils.BrutForceDictionary;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

public class TestBrutForce {
    public static void main(String[] args) {
        HashSet<String> DICTIONARY =BrutForceDictionary.getDictionary();
        System.out.println(DICTIONARY);
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

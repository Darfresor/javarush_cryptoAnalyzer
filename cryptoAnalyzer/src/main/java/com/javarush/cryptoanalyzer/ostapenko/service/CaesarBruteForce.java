package com.javarush.cryptoanalyzer.ostapenko.service;

import com.javarush.cryptoanalyzer.ostapenko.constans.GuiViewConstans;
import com.javarush.cryptoanalyzer.ostapenko.constans.RussianAlphabet;
import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import com.javarush.cryptoanalyzer.ostapenko.exception.ApplicationException;
import com.javarush.cryptoanalyzer.ostapenko.utils.BrutForceDictionary;
import com.javarush.cryptoanalyzer.ostapenko.utils.FileManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode.ERROR;
import static com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode.OK;

public class CaesarBruteForce implements Function{
    private char[] alphabet;
    private HashSet<Character> alphabetSet;
    private HashMap<Character, Integer> alphabetMap;

    private void initAlphabet() {
        alphabet = RussianAlphabet.cyrillicValues();
        alphabetSet = RussianAlphabet.getAlphabetSet();
        alphabetMap = RussianAlphabet.getAlphabetMap();
    }
    private String decrypt(String text, int shift) {
        char[] inputChars = text.toCharArray();
        char[] decryptedChars = new char[inputChars.length];
        int originalPos = 0;
        int newPosition = 0;
        for (int i = 0; i < inputChars.length; i++) {
            if (alphabetSet.contains(inputChars[i])
                    || alphabetSet.contains(Character.toUpperCase(inputChars[i]))
            ) {
                originalPos = alphabetMap.get(Character.toUpperCase(inputChars[i]));
                newPosition = (originalPos - shift + alphabet.length) % alphabet.length;
                if (!Character.isUpperCase(inputChars[i])) {
                    decryptedChars[i] = Character.toLowerCase(alphabet[newPosition]);
                } else {
                    decryptedChars[i] = alphabet[newPosition];
                }
            } else {
                decryptedChars[i] = inputChars[i];
            }
        }
        return new String(decryptedChars);
    }

    @Override
    public Result execute(String[] parametrs) {
        int finalkey = 0;
        try {
            initAlphabet();
            HashSet<String> dictionary = BrutForceDictionary.getDictionary();
            System.out.println("parametrs = " + Arrays.toString(parametrs));
            String text = FileManager.readFile(parametrs[1]);
            String result = "";
            String bestResult = "";
            int bestNumberOfMatches = 0;
            int numberOfMatches = 0;
            for (int i = 0; i < alphabet.length; i++) {
                numberOfMatches = 0;
                result = decrypt(text, i);
                String[] wordsinRelust = result.split("\\s+");
                for (String s : wordsinRelust) {
                    if (dictionary.contains(s.toLowerCase())) {
                        numberOfMatches++;
                    }
                }
                if (numberOfMatches > bestNumberOfMatches) {
                    bestNumberOfMatches = numberOfMatches;
                    bestResult = result;
                    finalkey = i;
                }
            }
            FileManager.writeFile(bestResult, parametrs[2]);
            System.out.println("Наибольшое кол-во сопавдений = " + bestNumberOfMatches);
            System.out.println("При ключе  = " + finalkey);
            // System.out.println(bestResult);
            return new Result(OK, new String[]{GuiViewConstans.UPDATE_AREA_FILE_OUT}, "Наибольшее кол-во сопавдений найдено при ключе = "+ finalkey );
        } catch(Exception e){
            e.printStackTrace();
            return new Result(ERROR, new ApplicationException("ошибка при операции декодирования", e));
        }
    }
}

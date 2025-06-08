package com.javarush.cryptoanalyzer.ostapenko.service;

import com.javarush.cryptoanalyzer.ostapenko.constans.RussianAlphabet;
import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import com.javarush.cryptoanalyzer.ostapenko.exception.ApplicationException;
import com.javarush.cryptoanalyzer.ostapenko.utils.FileManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode.ERROR;
import static com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode.OK;

public class DecodeVigenere implements Function{
    private char[] alphabet;
    private HashSet<Character> alphabetSet;
    private HashMap<Character, Integer> alphabetMap;


    private void initAlphabet() {
        alphabet = RussianAlphabet.cyrillicValues();
        alphabetSet = RussianAlphabet.getAlphabetSet();
        alphabetMap = RussianAlphabet.getAlphabetMap();
    }

    @Override
    public Result execute(String[] parametrs) {
        try {
            initAlphabet();
            System.out.println("parametrs = " + Arrays.toString(parametrs));
            String keyWord = (parametrs[7]);
            String text = FileManager.readFile(parametrs[1]);
            String result = encrypt(text, keyWord);
            System.out.println(keyWord);
            System.out.println(text);
            System.out.println(result);
            FileManager.writeFile(result, parametrs[2]);
        } catch(Exception e){
            e.printStackTrace();
            return new Result(ERROR, new ApplicationException("ошибка при операции декодирования", e));

        }
        return new Result(OK);
    }

    private String encrypt(String text, String keyWord) {
        char[] inputChars = text.toCharArray();
        char[] decryptedChars = new char[inputChars.length];
        char[] keyChars = keyWord.toCharArray();
        Integer[] keyCharsID = new Integer[keyChars.length];
        for (int i = 0; i < keyCharsID.length; i++) {
            keyCharsID[i] = alphabetMap.get(keyChars[i]);
        }
        System.out.println(Arrays.toString(keyCharsID));
        int originalPos = 0;
        int newPosition = 0;
        int shift = 0;
        int k= 0;
        for (int i = 0; i < inputChars.length; i++) {
            shift =  keyCharsID[k];
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
                if(k==keyCharsID.length-1){
                    k = 0;
                }else{
                    k++;
                };
            } else {
                decryptedChars[i] = inputChars[i];
            };

        }
        return new String(decryptedChars);
    }

}

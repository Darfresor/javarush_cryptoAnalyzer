package com.javarush.cryptoanalyzer.ostapenko.service;


import com.javarush.cryptoanalyzer.ostapenko.constans.GuiViewConstans;
import com.javarush.cryptoanalyzer.ostapenko.constans.RussianAlphabet;
import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import com.javarush.cryptoanalyzer.ostapenko.exception.ApplicationException;
import com.javarush.cryptoanalyzer.ostapenko.utils.FileManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static com.javarush.cryptoanalyzer.ostapenko.constans.EncodeVigenereConstants.PATERN_VIGENERE_ENCODE_RESULT_ERROR;
import static com.javarush.cryptoanalyzer.ostapenko.constans.FunctionConstants.PATERN_PARAMETRS;
import static com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode.ERROR;
import static com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode.OK;

public class EncodeVigenere implements Function{
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
            System.out.printf(PATERN_PARAMETRS,Arrays.toString(parametrs));
            String keyWord = (parametrs[7]);
            String text = FileManager.readFile(parametrs[1]);
            String result = encrypt(text, keyWord);
            //System.out.println(keyWord);
            //System.out.println(text);
            //System.out.println(result);
            FileManager.writeFile(result, parametrs[2]);
            return new Result(OK, new String[]{GuiViewConstans.UPDATE_AREA_FILE_OUT});
        } catch(Exception e){
            e.printStackTrace();
            return new Result(ERROR, new ApplicationException(PATERN_VIGENERE_ENCODE_RESULT_ERROR, e));

        }
    }

    private String encrypt(String text, String keyWord) {
        char[] inputChars = text.toCharArray();
        char[] encryptedChars = new char[inputChars.length];
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
                newPosition = (originalPos + shift) % alphabet.length;
                if (!Character.isUpperCase(inputChars[i])) {
                    encryptedChars[i] = Character.toLowerCase(alphabet[newPosition]);
                } else {
                    encryptedChars[i] = alphabet[newPosition];
                }
                if(k==keyCharsID.length-1){
                    k = 0;
                }else{
                    k++;
                };
            } else {
                encryptedChars[i] = inputChars[i];
            };

        }
        return new String(encryptedChars);
    }

}





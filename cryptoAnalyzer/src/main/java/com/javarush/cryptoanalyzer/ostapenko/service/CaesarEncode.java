package com.javarush.cryptoanalyzer.ostapenko.service;

import com.javarush.cryptoanalyzer.ostapenko.constans.GuiViewConstans;
import com.javarush.cryptoanalyzer.ostapenko.constans.RussianAlphabet;
import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import com.javarush.cryptoanalyzer.ostapenko.exception.ApplicationException;
import com.javarush.cryptoanalyzer.ostapenko.utils.FileManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode.ERROR;
import static com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode.OK;

public class CaesarEncode implements Function{
    private char[] alphabet;
    private HashSet<Character> alphabetSet;
    private HashMap<Character, Integer> alphabetMap;

    private void initAlphabet() {
        alphabet = RussianAlphabet.cyrillicValues();
        alphabetSet = RussianAlphabet.getAlphabetSet();
        alphabetMap = RussianAlphabet.getAlphabetMap();
    }

    private String encrypt(String text, int shift) {
        char[] inputChars = text.toCharArray();
        char[] encryptedChars = new char[inputChars.length];
        int originalPos = 0;
        int newPosition = 0;
        for (int i = 0; i < inputChars.length; i++) {
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
            } else {
                encryptedChars[i] = inputChars[i];
            }
        }
        return new String(encryptedChars);
    }

    @Override
    public Result execute(String[] parametrs) {
        try {
            initAlphabet();
            System.out.println("parametrs = " + Arrays.toString(parametrs));
            int shift = Integer.parseInt(parametrs[3]);
            String text = FileManager.readFile(parametrs[1]);
            String result = encrypt(text, shift);
            FileManager.writeFile(result, parametrs[2]);
            // System.out.println(text);
            // System.out.println(result);
            return new Result(OK, new String[]{GuiViewConstans.UPDATE_AREA_FILE_OUT});
        } catch(Exception e){
            e.printStackTrace();
            return new Result(ERROR, new ApplicationException("ошибка при операции кодирования", e));
        }

    }
}

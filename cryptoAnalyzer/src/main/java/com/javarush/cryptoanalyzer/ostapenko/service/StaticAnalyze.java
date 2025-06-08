package com.javarush.cryptoanalyzer.ostapenko.service;

import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import com.javarush.cryptoanalyzer.ostapenko.utils.FileManager;

import java.util.*;

public class StaticAnalyze implements Function {

    @Override
    public Result execute(String[] parametrs) {
        System.out.println("parametrs = " + Arrays.toString(parametrs));
        String encodeText = FileManager.readFile(parametrs[1]);
        String representativeText = FileManager.readFile(parametrs[4]);
        HashMap<Character, Integer> countCharInSourceText = new HashMap<>();
        HashMap<Character, Integer> countCharInEncodeText = new HashMap<>();
        countCharInSourceText = calcCharStatistic(representativeText);
        countCharInEncodeText = calcCharStatistic(encodeText);
        HashMap<Character, Integer> countCharInEncodeTextFiltred = new HashMap<>();
        HashMap<Character, Integer> countCharSourceTextFiltred = new HashMap<>();
        countCharInEncodeTextFiltred = clearMapDictionary(countCharInEncodeText, countCharInSourceText);
        countCharSourceTextFiltred = clearMapDictionary(countCharInSourceText, countCharInEncodeText);
        Map<Character, Double> representativeSortMap = convertoSortLinkedHasMap(countCharSourceTextFiltred);
        Map<Character, Double> encodeSortMap = convertoSortLinkedHasMap(countCharInEncodeTextFiltred);
        HashMap<Character, Character> finalMap = new HashMap<>();
        finalMap = buildCharMatchesMap(encodeSortMap, representativeSortMap);
        String result = decodeText(finalMap,encodeText);
        FileManager.writeFile(result, parametrs[2]);
        System.out.println(result);

        return null;
    }

    private HashMap<Character, Integer> calcCharStatistic(String text) {
        char[] inputChars = text.toLowerCase().toCharArray();
        HashMap<Character, Integer> countCharInText = new HashMap<>();

        for (int i = 0; i < inputChars.length; i++) {
            if (countCharInText.containsKey(inputChars[i])) {
                countCharInText.put(inputChars[i], (countCharInText.get(inputChars[i]) + 1));
            } else {
                countCharInText.put(inputChars[i], 1);
            }
            ;
        }
        return countCharInText;
    }

    private HashMap<Character, Integer> clearMapDictionary(HashMap<Character, Integer> mapSource, HashMap<Character, Integer> mapFiltr) {
        HashMap<Character, Integer> filtredMap = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : mapSource.entrySet()) {
            if (mapFiltr.containsKey(entry.getKey())) {
                filtredMap.put(entry.getKey(), entry.getValue());
            }
        }
        return filtredMap;
    }

    private int countAllCharactersInMap(HashMap<Character, Integer> map){
        int countAllChar = 0;
        Integer[] arrayValues = map.values().toArray(new Integer[0]);
        for (int i = 0; i < arrayValues.length; i++) {
            countAllChar += arrayValues[i];
        }
        return countAllChar;
    }

    private Map<Character, Double> convertoSortLinkedHasMap(HashMap<Character, Integer> map){
        int countAllCharactersInMap = countAllCharactersInMap(map);
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<Character, Double> sortResult = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            sortResult.put(entry.getKey(), ((double) entry.getValue()) / countAllCharactersInMap);
        }
        ;
        return sortResult;
    }
    private HashMap<Character, Character> buildCharMatchesMap(Map<Character, Double> encodeMap, Map<Character, Double> representativeMap){
        HashMap<Character, Character> finalMap = new HashMap<>();
        Iterator<Map.Entry<Character, Double>> it = representativeMap.entrySet().iterator();
        Iterator<Map.Entry<Character, Double>> it2 = encodeMap.entrySet().iterator();
        while(it.hasNext()&&it.hasNext()){
            finalMap.put(it2.next().getKey(), it.next().getKey());
        };
        return finalMap;
    }
    private String decodeText(HashMap<Character, Character> charMatchesMap, String encodeText){
        char[] encodeInputChars = encodeText.toLowerCase().toCharArray();
        char[] finalDecode = new char[encodeInputChars.length];
        for (int i = 0; i < encodeInputChars.length; i++) {
            if(charMatchesMap.containsKey(encodeInputChars[i])){
                finalDecode[i] = charMatchesMap.get(encodeInputChars[i]);
            }else{
                finalDecode[i] = encodeInputChars[i];
            }
        };
        return String.valueOf(finalDecode);
    };

}

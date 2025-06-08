package com.javarush.cryptoanalyzer.ostapenko.test;

import com.javarush.cryptoanalyzer.ostapenko.constans.RussianAlphabet;
import com.javarush.cryptoanalyzer.ostapenko.utils.FileManager;

import java.util.*;

public class TestStaticAnalyze {
    public static void main(String[] args) {
        String sourceText = FileManager.readFile("D:\\Файл для статистического анализа.txt");
        //System.out.println(sourceText);

        char[] sourceInputChars = sourceText.toLowerCase().toCharArray();
        HashMap<Character, Integer> countCharInSourceText = new HashMap<>();

        for (int i = 0; i < sourceInputChars.length; i++) {
            if (countCharInSourceText.containsKey(sourceInputChars[i])) {
                countCharInSourceText.put(sourceInputChars[i], (countCharInSourceText.get(sourceInputChars[i]) + 1));
            } else {
                countCharInSourceText.put(sourceInputChars[i], 1);
            }
            ;


        }
        Set<Map.Entry<Character, Integer>> entries = countCharInSourceText.entrySet();
        for (Map.Entry<Character, Integer> entry : entries) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            //System.out.println(key + "->" + ((double)value)/sourceInputChars.length*100);
        }

        //------------------------------
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        String decodeText = FileManager.readFile("D:\\Зашифрованный файл.txt");
        //System.out.println(sourceText);

        char[] encodeInputChars = decodeText.toLowerCase().toCharArray();
        HashMap<Character, Integer> countCharInEncodeText = new HashMap<>();

        for (int i = 0; i < encodeInputChars.length; i++) {
            if (countCharInEncodeText.containsKey(encodeInputChars[i])) {
                countCharInEncodeText.put(encodeInputChars[i], countCharInEncodeText.get(encodeInputChars[i]) + 1);
            } else {
                countCharInEncodeText.put(encodeInputChars[i], 1);
            }
            ;


        }
        Set<Map.Entry<Character, Integer>> entries2 = countCharInEncodeText.entrySet();
        for (Map.Entry<Character, Integer> entry : entries2) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            // System.out.println(key + "->" + value);
        }
        ;
        //---------------------------------
        //Урезаем исходные данные до информации в репрезентативном файле
        HashMap<Character, Integer> countCharInEncodeTextFiltred = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : entries2) {
            if (countCharInSourceText.containsKey(entry.getKey())) {
                countCharInEncodeTextFiltred.put(entry.getKey(), entry.getValue());
            }
        }


        HashMap<Character, Integer> countCharSourceTextFiltred = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : entries) {
            if (countCharInEncodeText.containsKey(entry.getKey())) {
                countCharSourceTextFiltred.put(entry.getKey(), entry.getValue());
            }
        }


        //подсчитываем суммарное кол-во элементов после урезания исходного словаря
        Integer[] EncodeTextFiltredValues = countCharInEncodeTextFiltred.values().toArray(new Integer[0]);
        int countOfEncodeTextFiltred = 0;
        for (int i = 0; i < EncodeTextFiltredValues.length; i++) {
            countOfEncodeTextFiltred += EncodeTextFiltredValues[i];
        }

        Integer[] SourceTextFiltredValues = countCharSourceTextFiltred.values().toArray(new Integer[0]);
        int countOfSourceTextFiltred = 0;
        for (int i = 0; i < SourceTextFiltredValues.length; i++) {
            countOfSourceTextFiltred += SourceTextFiltredValues[i];
        }


        List<Map.Entry<Character, Integer>> list = new ArrayList<>(countCharSourceTextFiltred.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<Character, Double> result = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            result.put(entry.getKey(), ((double) entry.getValue()) / countOfSourceTextFiltred);
        }
        ;

        List<Map.Entry<Character, Integer>> list2 = new ArrayList<>(countCharInEncodeTextFiltred.entrySet());
        list2.sort(Map.Entry.comparingByValue());
        Map<Character, Double> result2 = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list2) {
            result2.put(entry.getKey(), ((double) entry.getValue()) / countOfEncodeTextFiltred);
        }
        ;


        System.out.println(result.size());
        System.out.println(result2.size());
        System.out.println(result);
        System.out.println(result2);



        //--------------------------------------------
        //создаем карту соотношений репрезентативного файла и зашифрованного
        HashMap<Character, Character> finalMap = new HashMap<>();
        Iterator<Map.Entry<Character, Double>> it = result.entrySet().iterator();
        Iterator<Map.Entry<Character, Double>> it2 = result2.entrySet().iterator();
        while(it.hasNext()&&it.hasNext()){
            finalMap.put(it2.next().getKey(), it.next().getKey());
        };

        for(Map.Entry<Character, Character> map: finalMap.entrySet()){
            System.out.println(map.getKey() +"---"+ map.getValue());
        };
        //---------------------------------------------
        //Производим замену в зашифрованном тексте
        char[] finalDecode = new char[encodeInputChars.length];

        for (int i = 0; i < encodeInputChars.length; i++) {
            if(finalMap.containsKey(encodeInputChars[i])){
                finalDecode[i] = finalMap.get(encodeInputChars[i]);
            }else{
                finalDecode[i] = encodeInputChars[i];
            }
        };
        String str = String.valueOf(finalDecode);
        System.out.println(str);
        //-----------------------------------------
        //Замена данных
        char source ='у';
        char target = 'д';
        char[] array = str.toLowerCase().toCharArray();
        for (int i = 0; i < array.length; i++) {
            if(array[i]==target){
                array[i]='@';
            };
        }
        for (int i = 0; i < array.length; i++) {
            if(array[i]==source){
                array[i]=target;
            };
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '@') {
                array[i] = source;
            }
            ;
        }
        System.out.println("---------------------------------------------");
        str = String.valueOf(array);
        System.out.println(str);
    }
}

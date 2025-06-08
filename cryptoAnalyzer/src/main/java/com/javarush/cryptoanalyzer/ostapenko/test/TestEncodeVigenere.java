package com.javarush.cryptoanalyzer.ostapenko.test;


import com.javarush.cryptoanalyzer.ostapenko.service.EncodeVigenere;

public class TestEncodeVigenere {
    public static void main(String[] args) {
        String[] parametrs = {"VIGENER_ENCODE",
                "D:\\\\Исходный файл-виженер.txt"
                , "D:\\\\Зашифрованный файл - виженер.txt"
                , "2"
                ,""
                ,""
                ,""
                ,"АББ"
        };
        EncodeVigenere encodeVigenere = new EncodeVigenere();
        encodeVigenere.execute(parametrs);
    }
}

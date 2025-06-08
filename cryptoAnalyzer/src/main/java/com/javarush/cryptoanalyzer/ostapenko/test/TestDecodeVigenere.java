package com.javarush.cryptoanalyzer.ostapenko.test;


import com.javarush.cryptoanalyzer.ostapenko.service.DecodeVigenere;

public class TestDecodeVigenere {
    public static void main(String[] args) {
        String[] parametrs = {"VIGENER_DECODE",
                "D:\\\\Зашифрованный файл - виженер.txt"
                , "D:\\\\Расшифрованный файл - виженер.txt"
                , "2"
                ,""
                ,""
                ,""
                ,"АББ"
        };
        DecodeVigenere decodeVigenere = new DecodeVigenere();
        decodeVigenere.execute(parametrs);
    }
}

package com.javarush.cryptoanalyzer.ostapenko.test;

import com.javarush.cryptoanalyzer.ostapenko.service.StaticAnalyze;

public class test2 {
    public static void main(String[] args) {
        String[] parametrs = {"STATIC_ANALYZE",
                "D:\\\\Зашифрованный файл.txt"
                , "D:\\\\Расшифрованный файл статистический анализ.txt"
                , "2"
                ,"D:\\\\Файл для статистического анализа.txt"
        };
        StaticAnalyze staticAnalyze= new StaticAnalyze();
        staticAnalyze.execute(parametrs);
    }
}

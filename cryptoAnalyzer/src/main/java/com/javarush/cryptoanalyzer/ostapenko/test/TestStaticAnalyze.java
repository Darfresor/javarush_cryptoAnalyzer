package com.javarush.cryptoanalyzer.ostapenko.test;


import com.javarush.cryptoanalyzer.ostapenko.service.ChangesChar;
import com.javarush.cryptoanalyzer.ostapenko.service.StaticAnalyze;


public class TestStaticAnalyze {
    public static void main(String[] args) {
        String[] parametrs = {"STATIC_ANALYZE",
                "D:\\\\Зашифрованный файл.txt"
                , "D:\\\\Расшифрованный файл статистический анализ.txt"
                , "2"
                ,"D:\\\\Файл для статистического анализа.txt"
        };
        StaticAnalyze staticAnalyze= new StaticAnalyze();
        staticAnalyze.execute(parametrs);

        String[] parametrs2 = {"STATIC_ANALYZE",
                "D:\\\\Зашифрованный файл.txt"
                , "D:\\\\Расшифрованный файл статистический анализ.txt"
                , "2"
                ,"D:\\\\Файл для статистического анализа.txt"
                ,"у"
                ,"д"
        };
        ChangesChar changesChar = new ChangesChar();
        changesChar.execute(parametrs2);

        String[] parametrs3 = {"STATIC_ANALYZE",
                "D:\\\\Зашифрованный файл.txt"
                , "D:\\\\Расшифрованный файл статистический анализ.txt"
                , "2"
                ,"D:\\\\Файл для статистического анализа.txt"
                ,"."
                ,"ы"
        };
        changesChar = new ChangesChar();
        changesChar.execute(parametrs3);
    }
}

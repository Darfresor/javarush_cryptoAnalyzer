package com.javarush.cryptoanalyzer.ostapenko.test;

import com.javarush.cryptoanalyzer.ostapenko.service.CaesarDecode;

public class TestCaesarDecode {
    public static void main(String[] args) {
        String[] parametrs = {"DECODE",
                "D:\\\\1234.txt"
                , "D:\\\\123.txt"
                , "2"
        };
        CaesarDecode encode = new CaesarDecode();
        encode.execute(parametrs);
    }
}

package com.javarush.cryptoanalyzer.ostapenko.test;

import com.javarush.cryptoanalyzer.ostapenko.service.CaesarEncode;

public class TestCaesarEncode {
    public static void main(String[] args) {
        String[] parametrs = {"ENCODE",
                "D:\\\\123.txt"
                , "D:\\\\1234.txt"
                , "2"
        };
        CaesarEncode encode = new CaesarEncode();
        encode.execute(parametrs);
    }
}

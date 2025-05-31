package com.javarush.cryptoanalyzer.ostapenko.test;

import com.javarush.cryptoanalyzer.ostapenko.service.Decode;

public class TestDecode {
    public static void main(String[] args) {
        String[] parametrs = {"DECODE",
                "D:\\\\1234.txt"
                , "D:\\\\123.txt"
                , "2"
        };
        Decode encode = new Decode();
        encode.execute(parametrs);
    }
}

package com.javarush.cryptoanalyzer.ostapenko.test;

import com.javarush.cryptoanalyzer.ostapenko.service.Encode;
import com.javarush.cryptoanalyzer.ostapenko.utils.FileManager;

public class TestEncode {
    public static void main(String[] args) {
        String[] parametrs = {"ENCODE",
                "D:\\\\123.txt"
                , "D:\\\\1234.txt"
                , "2"
        };
        Encode encode = new Encode();
        encode.execute(parametrs);
    }
}

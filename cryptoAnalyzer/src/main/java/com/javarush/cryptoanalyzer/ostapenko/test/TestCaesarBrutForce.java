package com.javarush.cryptoanalyzer.ostapenko.test;


import com.javarush.cryptoanalyzer.ostapenko.service.CaesarBruteForce;

public class TestCaesarBrutForce {
    public static void main(String[] args) {
        String[] parametrs = {"DECODE",
                "D:\\\\1234.txt"
                , "D:\\\\123.txt"
                , "2"
        };
        CaesarBruteForce brutForce = new CaesarBruteForce();
        brutForce.execute(parametrs);
    }

}

package com.javarush.cryptoanalyzer.ostapenko.test;


import com.javarush.cryptoanalyzer.ostapenko.service.BruteForce;

public class TestBrutForce {
    public static void main(String[] args) {
        String[] parametrs = {"DECODE",
                "D:\\\\1234.txt"
                , "D:\\\\123.txt"
                , "2"
        };
        BruteForce brutForce = new BruteForce();
        brutForce.execute(parametrs);
    }

}

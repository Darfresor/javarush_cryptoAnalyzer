package com.javarush.cryptoanalyzer.ostapenko.test;


import com.javarush.cryptoanalyzer.ostapenko.service.BrutForce;

public class TestBrutForce {
    public static void main(String[] args) {
        String[] parametrs = {"DECODE",
                "D:\\\\1234.txt"
                , "D:\\\\123.txt"
                , "2"
        };
        BrutForce brutForce = new BrutForce();
        brutForce.execute(parametrs);
    }

}

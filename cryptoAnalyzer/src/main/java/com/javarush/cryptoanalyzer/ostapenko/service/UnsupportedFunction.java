package com.javarush.cryptoanalyzer.ostapenko.service;

import com.javarush.cryptoanalyzer.ostapenko.entity.Result;

public class UnsupportedFunction implements Function{
    @Override
    public Result execute(String[] parametrs) {
        return null;
    }
}

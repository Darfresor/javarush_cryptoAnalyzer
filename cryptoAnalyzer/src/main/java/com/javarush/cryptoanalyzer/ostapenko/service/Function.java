package com.javarush.cryptoanalyzer.ostapenko.service;

import com.javarush.cryptoanalyzer.ostapenko.entity.Result;

public interface Function {
    Result execute(String[] parametrs);
}

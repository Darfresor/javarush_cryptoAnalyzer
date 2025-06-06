package com.javarush.cryptoanalyzer.ostapenko.repository;

import com.javarush.cryptoanalyzer.ostapenko.service.*;

public enum FunctionCode {
    ENCODE(new Encode()), DECODE(new Decode()), BRUTEFORCE(new BruteForce()), UNSUPPORTED_FUNCTION(new UnsupportedFunction());
    private Function function;

    FunctionCode(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }
}

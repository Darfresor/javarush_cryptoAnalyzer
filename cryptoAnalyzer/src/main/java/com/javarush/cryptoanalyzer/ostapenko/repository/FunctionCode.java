package com.javarush.cryptoanalyzer.ostapenko.repository;

import com.javarush.cryptoanalyzer.ostapenko.service.Decode;
import com.javarush.cryptoanalyzer.ostapenko.service.Encode;
import com.javarush.cryptoanalyzer.ostapenko.service.Function;
import com.javarush.cryptoanalyzer.ostapenko.service.UnsupportedFunction;

public enum FunctionCode {
ENCODE(new Encode()), DECODE(new Decode()), UNSUPPORTED_FUNCTION(new UnsupportedFunction());
private Function function;

    FunctionCode(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }
}

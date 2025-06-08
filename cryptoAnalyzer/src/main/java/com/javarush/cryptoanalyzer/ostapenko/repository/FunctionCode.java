package com.javarush.cryptoanalyzer.ostapenko.repository;

import com.javarush.cryptoanalyzer.ostapenko.service.*;

public enum FunctionCode {
    ENCODE(new CaesarEncode()), DECODE(new CaesarDecode()), BRUTEFORCE(new CaesarBruteForce()), STATIC_ANALYZE(new StaticAnalyze()),
    CHANGES_CHAR(new ChangesChar()), VIGENER_ENCODE(new EncodeVigenere()), VIGENER_DECODE(new DecodeVigenere()),
    UNSUPPORTED_FUNCTION(new UnsupportedFunction());
    private Function function;

    FunctionCode(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }
}

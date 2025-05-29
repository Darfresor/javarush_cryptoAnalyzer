package com.javarush.cryptoanalyzer.ostapenko.view;

import com.javarush.cryptoanalyzer.ostapenko.entity.Result;

public interface View {
    /**
     * <p> [0] элемент -  что нужно сделать.
     * <p> [1] элемент -  путь откуда получать данные.
     * <p> [2] элемент -  путь куда сохранять данные.
     * <p> [3] элемент -  длина ключа.
     */
    String[] getParametrs();

    void printResult(Result result);
}

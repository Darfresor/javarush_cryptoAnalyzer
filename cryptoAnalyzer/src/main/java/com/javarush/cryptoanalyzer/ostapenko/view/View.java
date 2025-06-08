package com.javarush.cryptoanalyzer.ostapenko.view;

import com.javarush.cryptoanalyzer.ostapenko.entity.Result;

public interface View {
    /**
     * <p> [0] элемент -  что нужно сделать.
     * <p> [1] элемент -  путь откуда получать данные.
     * <p> [2] элемент -  путь куда сохранять данные.
     * <p> [3] элемент -  длина ключа.
     * <p> [4] элемент -  путь к файлу с репрезентационными данными для статистического анализа
     * <p> [5] элемент -  символ который мы будем заменять в тексте при статистическом анализе
     * <p> [6] элемент -  символ на который мы будем заменять исходный в тексте при статистическом анализе
     */
    String[] getParametrs();

    void printResult(Result result);

}

package com.javarush.cryptoanalyzer.ostapenko.constans;

import java.util.HashMap;
import java.util.HashSet;

public enum RussianAlphabet {
    A('А', 0),
    BE('Б', 1),
    VE('В', 2),
    GE('Г', 3),
    DE('Д', 4),
    YE('Е', 5),
    YO('Ё', 6),
    ZHE('Ж', 7),
    ZE('З', 8),
    I('И', 9),
    SHORT_I('Й', 10),
    KA('К', 11),
    EL('Л', 12),
    EM('М', 13),
    EN('Н', 14),
    O('О', 15),
    PE('П', 16),
    ER('Р', 17),
    ES('С', 18),
    TE('Т', 19),
    U('У', 20),
    EF('Ф', 21),
    HA('Х', 22),
    TSE('Ц', 23),
    CHE('Ч', 24),
    SHA('Ш', 25),
    SHCHA('Щ', 26),
    HARD_SIGN('Ъ', 27),
    YERU('Ы', 28),
    SOFT_SIGN('Ь', 29),
    EH('Э', 30),
    YU('Ю', 31),
    YA('Я', 32),
    DOT('.', 33),
    COMMA(',', 34),
    LEFT_GUILLEMET('«', 35),
    RIGHT_GUILLEMET('»', 36),
    DOUBLE_QUOTE('"', 37),
    SINGLE_QUOTE('\'', 38),
    COLON(':', 39),
    EXCLAMATION('!', 40),
    QUESTION('?', 41),
    SPACE(' ', 42);
    private final char cyrillicChar;
    private final int position;

    RussianAlphabet(char cyrillicChar, int position) {
        this.cyrillicChar = cyrillicChar;
        this.position = position;
    }

    public Character getCyrillicChar() {
        return cyrillicChar;
    }

    public Integer getPosition() {
        return position;
    }

    public static char[] cyrillicValues() {
        char[] cyrillicArray = new char[RussianAlphabet.values().length];
        int num = 0;
        for (var letter : RussianAlphabet.values()) {
            cyrillicArray[num] = letter.getCyrillicChar();
            num++;
        }
        return cyrillicArray;
    }
    public static HashSet<Character> getAlphabetSet() {
        HashSet<Character> alphabetSet = new HashSet<>();
        for (var letter : RussianAlphabet.values()) {
            alphabetSet.add(letter.getCyrillicChar());
        }
        return alphabetSet;
    }
    public static HashMap<Character, Integer> getAlphabetMap() {
        HashMap<Character,Integer> alphabetMap = new HashMap<>();
        for (var letter : RussianAlphabet.values()) {
            alphabetMap.put(letter.getCyrillicChar(),letter.getPosition());
        }
        return alphabetMap;
    }
}

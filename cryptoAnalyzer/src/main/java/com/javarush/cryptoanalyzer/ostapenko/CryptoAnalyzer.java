package com.javarush.cryptoanalyzer.ostapenko;

import com.javarush.cryptoanalyzer.ostapenko.app.Application;

public class CryptoAnalyzer {
    public static void main(String[] args) {
        Application.launch(Application.class, args);
    }
}
//TODO важно!! в брутфорсе сделать вывод ключа. Возможно в отдельном окне рядом с чебкосом брутфорса?
//TODO после статистического анализа вынести все в константы отдельные
//TODO сделать в интерфейса соответствующие блокировки/разблокировки компонентов при выборе опций
//TODO расширить список исключений
//TODO и уже после всего выше по остаточному принципу поковырять шифр вижинера
package com.javarush.cryptoanalyzer.ostapenko.view;

import com.javarush.cryptoanalyzer.ostapenko.entity.Result;

import static com.javarush.cryptoanalyzer.ostapenko.constans.ApplicationComplitionConstans.EXCEPTION;
import static com.javarush.cryptoanalyzer.ostapenko.constans.ApplicationComplitionConstans.SUCCESS;

public class GuiView implements View {
    @Override
    public String[] getParametrs() {
        //TODO здесь нужно доделать возвращение параметров
        return new String[0];
    }

    @Override
    public void printResult(Result result) {
        switch (result.getResultCode()) {
            case OK -> System.out.println(SUCCESS);
            case ERROR -> System.out.println(EXCEPTION + result.getApplicationException().getMessage());
        }
    }

}

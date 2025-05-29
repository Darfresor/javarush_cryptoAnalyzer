package com.javarush.cryptoanalyzer.ostapenko.app;

import com.javarush.cryptoanalyzer.ostapenko.controller.MainController;
import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import com.javarush.cryptoanalyzer.ostapenko.repository.FunctionCode;
import com.javarush.cryptoanalyzer.ostapenko.service.Function;

import static com.javarush.cryptoanalyzer.ostapenko.constans.FunctionCodeConstans.*;


public class Application {
    private final MainController mainController;

    public Application(MainController mainController) {
        this.mainController = mainController;
    }

    public Result run() {
        String[] parametrs = mainController.getView().getParametrs();
        String mode = parametrs[0];
        Function function = getFunction(mode);
        return function.execute(parametrs);
    }

    private Function getFunction(String mode) {
        return switch (mode) {
            case "1" -> FunctionCode.valueOf(ENCODE).getFunction();
            case "2" -> FunctionCode.valueOf(DECODE).getFunction();
            default -> FunctionCode.valueOf(UNSUPPORTED_FUNCTION).getFunction();
        };
    }

    public void printResult(Result result) {
        mainController.getView().printResult(result);
    }
}

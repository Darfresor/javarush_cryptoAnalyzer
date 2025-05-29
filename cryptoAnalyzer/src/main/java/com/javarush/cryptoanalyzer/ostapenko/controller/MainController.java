package com.javarush.cryptoanalyzer.ostapenko.controller;

import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import com.javarush.cryptoanalyzer.ostapenko.exception.ApplicationException;
import com.javarush.cryptoanalyzer.ostapenko.repository.FunctionCode;
import com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode;
import com.javarush.cryptoanalyzer.ostapenko.service.Function;
import com.javarush.cryptoanalyzer.ostapenko.view.View;

import static com.javarush.cryptoanalyzer.ostapenko.constans.FunctionCodeConstans.*;

public class MainController {
    private View view;

    public MainController(View view) {
        this.view = view;
    }

    public void run() {
        try {
            String[] parametrs = view.getParametrs();
            String mode = parametrs[0];
            Function function = getFunction(mode);
            Result result = function.execute(parametrs);
            view.printResult(result);
        } catch (RuntimeException e) {
            view.printResult(new Result(ResultCode.ERROR,
                    new ApplicationException("Ошибка в работе приложения", e)));
        }
    }

    private Function getFunction(String mode) {
        return switch (mode) {
            case "1" -> FunctionCode.valueOf(ENCODE).getFunction();
            case "2" -> FunctionCode.valueOf(DECODE).getFunction();
            default -> FunctionCode.valueOf(UNSUPPORTED_FUNCTION).getFunction();
        };
    }

    public View getView() {
        return view;
    }
}

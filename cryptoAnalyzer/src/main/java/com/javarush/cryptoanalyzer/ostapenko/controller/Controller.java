package com.javarush.cryptoanalyzer.ostapenko.controller;

import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import com.javarush.cryptoanalyzer.ostapenko.exception.ApplicationException;
import com.javarush.cryptoanalyzer.ostapenko.repository.FunctionCode;
import com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode;
import com.javarush.cryptoanalyzer.ostapenko.service.Function;
import com.javarush.cryptoanalyzer.ostapenko.view.View;

import static com.javarush.cryptoanalyzer.ostapenko.constans.ControllerConstants.PATTER_ERROR;
import static com.javarush.cryptoanalyzer.ostapenko.constans.FunctionCodeConstans.*;

public abstract class Controller {
    protected View view;

    public Controller(View view) {
        this.view = view;
        initialize();

    }

    /**
     * Дополнительная инициализация если в этом есть необходимость.
     * Например для кнопок в GUI
     */
    public abstract void initialize();

    public void run() {
        try {
            String[] parametrs = view.getParametrs();
            String mode = parametrs[0];
            Function function = getFunction(mode);
            Result result = function.execute(parametrs);
            view.printResult(result);
            //TODO передача команд изменения интерфейса после работы функции?
        } catch (RuntimeException e) {
            view.printResult(new Result(ResultCode.ERROR,
                    new ApplicationException(PATTER_ERROR, e)));
            e.printStackTrace();
        }
    }

    ;

    private Function getFunction(String mode) {
        return switch (mode) {
            case ENCODE -> FunctionCode.valueOf(ENCODE).getFunction();
            case DECODE -> FunctionCode.valueOf(DECODE).getFunction();
            case BRUTEFORCE -> FunctionCode.valueOf(BRUTEFORCE).getFunction();
            case STATIC_ANALYZE -> FunctionCode.valueOf(STATIC_ANALYZE).getFunction();
            case CHANGES_CHAR -> FunctionCode.valueOf(CHANGES_CHAR).getFunction();
            case VIGENER_ENCODE -> FunctionCode.valueOf(VIGENER_ENCODE).getFunction();
            case VIGENER_DECODE -> FunctionCode.valueOf(VIGENER_DECODE).getFunction();
            default -> FunctionCode.valueOf(UNSUPPORTED_FUNCTION).getFunction();
        };
    }
}

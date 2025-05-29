package com.javarush.cryptoanalyzer.ostapenko.service;

import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import com.javarush.cryptoanalyzer.ostapenko.exception.ApplicationException;

import static com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode.ERROR;
import static com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode.OK;

public class Encode implements Function{
    @Override
    public Result execute(String[] parametrs) {
        try {
            //TODO описать логику кодирования
        } catch(Exception e){
            return new Result(ERROR, new ApplicationException("ошибка при операции декодирования", e));
        }
        return new Result(OK);
    }
}

package com.javarush.cryptoanalyzer.ostapenko.entity;

import com.javarush.cryptoanalyzer.ostapenko.exception.ApplicationException;
import com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode;

public class Result {
    private ResultCode resultCode;
    private ApplicationException applicationException;
    private String message;
    private String[] commands;

    public Result(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public Result(ResultCode resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }
    public Result(ResultCode resultCode, String[] commands) {
        this.resultCode = resultCode;
        this.commands = commands;
    }

    public Result(ResultCode resultCode,String[] commands, String message) {
        this.resultCode = resultCode;
        this.message = message;
        this.commands = commands;
    }




    public Result(ResultCode resultCode, ApplicationException applicationException) {
        this.resultCode = resultCode;
        this.applicationException = applicationException;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public ApplicationException getApplicationException() {
        return applicationException;
    }

    public String getMessage() {
        return message;
    }


    public String[] getCommands() {
     return commands;
    }
}

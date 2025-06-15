package com.javarush.cryptoanalyzer.ostapenko.service;

import com.javarush.cryptoanalyzer.ostapenko.constans.GuiViewConstans;
import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import com.javarush.cryptoanalyzer.ostapenko.exception.ApplicationException;
import com.javarush.cryptoanalyzer.ostapenko.utils.FileManager;

import java.util.Arrays;

import static com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode.ERROR;
import static com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode.OK;

public class ChangesChar implements Function{
    @Override
    public Result execute(String[] parametrs) {
        try {
            System.out.println("parametrs = " + Arrays.toString(parametrs));
            String decodeText = FileManager.readFile(parametrs[2]);
            String result = changeChar(decodeText,parametrs[5].toCharArray()[0], parametrs[6].toCharArray()[0]);
            FileManager.writeFile(result, parametrs[2]);
            //System.out.println(result);
            return new Result(OK, new String[]{GuiViewConstans.UPDATE_AREA_FILE_OUT});
        } catch(Exception e){
            e.printStackTrace();
            return new Result(ERROR, new ApplicationException("ошибка при операции замены символов в тексте", e));

        }
    }

    private String changeChar(String inputTxt, char source, char target){
        char[] array = inputTxt.toLowerCase().toCharArray();
        for (int i = 0; i < array.length; i++) {
            if(array[i]==target){
                array[i]='@';
            };
        }
        for (int i = 0; i < array.length; i++) {
            if(array[i]==source){
                array[i]=target;
            };
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '@') {
                array[i] = source;
            }
            ;
        }
        return String.valueOf(array);
    }
}

package com.javarush.cryptoanalyzer.ostapenko.controller;

import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import com.javarush.cryptoanalyzer.ostapenko.exception.ApplicationException;
import com.javarush.cryptoanalyzer.ostapenko.repository.FunctionCode;
import com.javarush.cryptoanalyzer.ostapenko.repository.ResultCode;
import com.javarush.cryptoanalyzer.ostapenko.service.Function;
import com.javarush.cryptoanalyzer.ostapenko.view.GuiView;
import com.javarush.cryptoanalyzer.ostapenko.view.View;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import static com.javarush.cryptoanalyzer.ostapenko.constans.FunctionCodeConstans.*;

public class MainController {
    private View view;

    public MainController(View view) {
        this.view = view;
        setupEventHandlers();
    }


    private void setupEventHandlers() {
        if(view instanceof GuiView guiView){
            guiView.setSelectFileButtonInHandler(() -> handleSelectFileIn(guiView));
        }

    }


    private void handleSelectFileIn(GuiView guiView) {
        System.out.println("Нажата кнопка выбор пути входящего файла");
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                //boolean isValidExtension = Validator.isValidExtension(file.getAbsolutePath());
                boolean isValidExtension = true;
                if (!isValidExtension) {
                    guiView.getFilePathFieldIn().setText("");
                   // showAlertWindow("Неподдерживаемый файл", "Пожалуйста, выберите файл с расширением .txt");
                } else {
                    guiView.getFilePathFieldIn().setText(file.getAbsolutePath());
                    guiView.log("Выбран файл с входными данными: " + file);
                }

            }
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
}

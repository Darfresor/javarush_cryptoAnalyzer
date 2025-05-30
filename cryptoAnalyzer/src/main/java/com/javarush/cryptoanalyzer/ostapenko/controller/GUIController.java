package com.javarush.cryptoanalyzer.ostapenko.controller;

import com.javarush.cryptoanalyzer.ostapenko.view.GuiView;
import com.javarush.cryptoanalyzer.ostapenko.view.View;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class GUIController extends Controller {

    public GUIController(View view) {
        super(view);
    }

    @Override
    public void initialize() {
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        if (view instanceof GuiView guiView) {
            guiView.setSelectFileButtonInHandler(() -> handleSelectFileIn(guiView));
            guiView.setSelectFileButtonOutHandler(() -> handleSelectFileOut(guiView));
            // При нажатии на "Закодировать" -> run() с режимом "encode"
            //  guiView.setEncodeHandler(() -> runWithMode("encode"));
            //TODO при нажатии кнопки описываем ее действие, если это кнопка кодировать и прочее то для них вызываем метод run последующий метод run.
        }
    }

    private void handleSelectFileIn(GuiView guiView) {
        System.out.println("Начат выбор файла входных данных");
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        guiView.setFilePathFieldIn(file.getAbsolutePath());
        guiView.log("Выбран файл с входными данными: " + file);
        System.out.println("Выбор файла входных данных завершен");
        if(guiView.isStartButtonAvailability()){
            guiView.setEnabledStartButton(true);
        }else{
            guiView.setEnabledStartButton(false);
        }
    }
    private void handleSelectFileOut(GuiView guiView) {
        System.out.println("Начат выбор файла выходных данных");
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        guiView.setFilePathFieldOut(file.getAbsolutePath());
        guiView.log("Выбран файл с выходными данными: " + file);
        System.out.println("Выбор файла выходных данных завершен");
        if(guiView.isStartButtonAvailability()){
            guiView.setEnabledStartButton(true);
        }else{
            guiView.setEnabledStartButton(false);
        }
    }
}

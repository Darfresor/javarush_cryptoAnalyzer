package com.javarush.cryptoanalyzer.ostapenko.controller;

import com.javarush.cryptoanalyzer.ostapenko.exception.ViewTypeException;
import com.javarush.cryptoanalyzer.ostapenko.utils.FileManager;
import com.javarush.cryptoanalyzer.ostapenko.view.GuiView;
import com.javarush.cryptoanalyzer.ostapenko.view.View;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import static com.javarush.cryptoanalyzer.ostapenko.constans.GUIControllerConstans.*;

public class GUIController extends Controller {
    GuiView guiView;

    public GUIController(View view) {
        super(view);
    }

    @Override
    public void initialize() {
        if(view instanceof GuiView){
            this.guiView = (GuiView) view;
        }else{
            throw new ViewTypeException("В интерфейс View передан класс отличный от ожидаемого GuiView.");
        };
        setupEventHandlers();
    }

    private void setupEventHandlers() {
            guiView.setSelectFileButtonInHandler(() -> handlerSelectFileIn(guiView));
            guiView.setSelectFileButtonOutHandler(() -> handlerSelectFileOut(guiView));
            guiView.setSelectFileButtonSourceHandler(() -> handlerSelectFileSource(guiView));
            guiView.setStartButton(() -> handlerStartButton(guiView));
            guiView.setChangeChar(() -> handlerChangeChar(guiView));
    }

    private void handlerSelectFileIn(GuiView guiView) {
        System.out.println(START_IN_FILE_CHOOSE);
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        guiView.setFilePathFieldIn(file.getAbsolutePath());
        guiView.log(MSG_FOR_LOG_FILE_IN_DETAIL + file);
        System.out.println(END_IN_FILE_CHOOSE);
        if(guiView.isStartButtonAvailability()){
            guiView.setEnabledStartButton(true);
        }else{
            guiView.setEnabledStartButton(false);
        }
        guiView.setTextFileIn(FileManager.readFile(guiView.getFilePathFieldIn()));
        guiView.log(MSG_FOR_LOG_CHOOSE_IN + file);
    }
    private void handlerSelectFileOut(GuiView guiView) {
        System.out.println(START_OUT_FILE_CHOOSE);
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        guiView.setFilePathFieldOut(file.getAbsolutePath());
        guiView.log(MSG_FOR_LOG_FILE_OUT_DETAIL + file);
        System.out.println(END_OUT_FILE_CHOOSE);
        if(guiView.isStartButtonAvailability()){
            guiView.setEnabledStartButton(true);
        }else{
            guiView.setEnabledStartButton(false);
        };
        guiView.setTextFileOut(FileManager.readFile(guiView.getFilePathFieldOut()));
        guiView.log(MSG_FOR_LOG_CHOOSE_OUT + file);
    }

    private void handlerSelectFileSource(GuiView guiView) {
        System.out.println(START_STATIC_FILE_CHOOSE);
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        guiView.setFilePathFieldSource(file.getAbsolutePath());
        guiView.log(MSG_FOR_LOG_FILE_STATIC_DETAIL + file);
        System.out.println(END_STATIC_FILE_CHOOSE);
        guiView.setTextFileSource(FileManager.readFile(guiView.getFilePathFieldSource()));
        guiView.log(MSG_FOR_LOG_CHOOSE_STATIC + file);
    }

    private void handlerStartButton(GuiView guiView) {
        guiView.log(MSG_FOR_LOG_START_FUNCTION);
        run();
        guiView.setTextFileOut(FileManager.readFile(guiView.getFilePathFieldOut()));
        guiView.log(MSG_FOR_LOG_END_FUNCTION);
    }

    private void handlerChangeChar(GuiView guiView) {
        guiView.log(MSG_FOR_LOG_START_FUNCTION);
        run();
        guiView.setTextFileOut(FileManager.readFile(guiView.getFilePathFieldOut()));
        guiView.log(MSG_FOR_LOG_END_FUNCTION);
    }



}

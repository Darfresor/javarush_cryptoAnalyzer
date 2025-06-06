package com.javarush.cryptoanalyzer.ostapenko.controller;

import com.javarush.cryptoanalyzer.ostapenko.view.GuiView;
import com.javarush.cryptoanalyzer.ostapenko.view.View;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
//TODO  в консоли тоже добавить отображение по умолчанию?
//TODO ДОбавить ли всплывающий сообщения и свертку лога?
//TODO добавить значек смены путей между собой
//TODO сделать валидаци, не допуская одинаковые пути
//TODO вынести текст в константы
//TODO выводить в дебаг информацию о ключе + менять счетчик в интерфейсе?
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
            guiView.setSelectFileButtonInHandler(() -> handlerSelectFileIn(guiView));
            guiView.setSelectFileButtonOutHandler(() -> handlerSelectFileOut(guiView));
            guiView.setStartButton(() -> handlerStartButton());
        }
    }

    private void handlerSelectFileIn(GuiView guiView) {
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
    private void handlerSelectFileOut(GuiView guiView) {
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

    private void handlerStartButton() {
        run();
    }



}

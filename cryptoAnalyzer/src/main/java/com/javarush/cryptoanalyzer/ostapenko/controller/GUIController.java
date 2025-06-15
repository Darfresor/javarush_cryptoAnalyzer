package com.javarush.cryptoanalyzer.ostapenko.controller;

import com.javarush.cryptoanalyzer.ostapenko.exception.ViewTypeException;
import com.javarush.cryptoanalyzer.ostapenko.utils.FileManager;
import com.javarush.cryptoanalyzer.ostapenko.view.GuiView;
import com.javarush.cryptoanalyzer.ostapenko.view.View;

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
            guiView.setAllButtonHandler(() -> handlerAllButton(guiView));
    }

    private void handlerAllButton(GuiView guiView) {
        guiView.log(MSG_FOR_LOG_START_FUNCTION);
        run();
        guiView.setTextFileOut(FileManager.readFile(guiView.getFilePathFieldOut()));
        guiView.log(MSG_FOR_LOG_END_FUNCTION);
    }




}

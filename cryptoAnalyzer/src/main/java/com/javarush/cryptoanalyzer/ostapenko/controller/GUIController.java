package com.javarush.cryptoanalyzer.ostapenko.controller;

import com.javarush.cryptoanalyzer.ostapenko.exception.ViewTypeException;
import com.javarush.cryptoanalyzer.ostapenko.view.GuiView;
import com.javarush.cryptoanalyzer.ostapenko.view.View;


public class GUIController extends Controller {
    GuiView guiView;

    public GUIController(View view) {
        super(view);
    }

    @Override
    public void initialize() {
        if(view instanceof GuiView transformView){
            this.guiView = transformView;
        }else{
            throw new ViewTypeException("В интерфейс View передан класс отличный от ожидаемого GuiView.");
        }
        setupEventHandlers();
    }

    private void setupEventHandlers() {
            guiView.setAllButtonHandler(() -> handlerAllButton(guiView));
    }

    private void handlerAllButton(GuiView guiView) {
        run();
    }




}

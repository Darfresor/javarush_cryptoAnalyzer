package com.javarush.cryptoanalyzer.ostapenko.controller;

import com.javarush.cryptoanalyzer.ostapenko.exception.ViewTypeException;
import com.javarush.cryptoanalyzer.ostapenko.view.GuiView;
import com.javarush.cryptoanalyzer.ostapenko.view.View;

import static com.javarush.cryptoanalyzer.ostapenko.constans.GUIControllerConstants.PATTERN_ERROR_VIEW_TYPE;


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
            throw new ViewTypeException(PATTERN_ERROR_VIEW_TYPE);
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

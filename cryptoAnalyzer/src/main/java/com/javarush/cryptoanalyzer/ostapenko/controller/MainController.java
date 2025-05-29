package com.javarush.cryptoanalyzer.ostapenko.controller;

import com.javarush.cryptoanalyzer.ostapenko.view.View;

public class MainController {
    private View view;

    public MainController(View view) {
        this.view = view;

    }

    public View getView() {
        return view;
    }
}

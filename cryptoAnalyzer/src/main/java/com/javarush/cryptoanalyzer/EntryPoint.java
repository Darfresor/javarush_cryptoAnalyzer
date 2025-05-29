package com.javarush.cryptoanalyzer;

import com.javarush.cryptoanalyzer.ostapenko.app.Application;
import com.javarush.cryptoanalyzer.ostapenko.controller.MainController;
import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import com.javarush.cryptoanalyzer.ostapenko.view.View;
import com.javarush.cryptoanalyzer.ostapenko.view.GuiView;

public class EntryPoint {
    public static void main(String[] args) {
        View view = new GuiView();
        MainController mainController = new MainController(view);
        Application application = new Application(mainController);
        Result result = application.run();
        application.printResult(result);
    }
}

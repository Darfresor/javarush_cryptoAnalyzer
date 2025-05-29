package com.javarush.cryptoanalyzer.ostapenko.app;


import com.javarush.cryptoanalyzer.ostapenko.controller.MainController;
import com.javarush.cryptoanalyzer.ostapenko.view.GuiView;
import com.javarush.cryptoanalyzer.ostapenko.view.View;
import javafx.stage.Stage;


public class Application  extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {

        View view = new GuiView(stage);
        MainController controller = new MainController(view);
        controller.run();


    }
}

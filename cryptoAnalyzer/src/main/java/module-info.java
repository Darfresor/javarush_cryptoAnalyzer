module com.javarush.cryptoanalyzer.ostapenko.cryptoanalyzer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.javarush.cryptoanalyzer.ostapenko.cryptoanalyzer to javafx.fxml;
    exports com.javarush.cryptoanalyzer.ostapenko.cryptoanalyzer;
}
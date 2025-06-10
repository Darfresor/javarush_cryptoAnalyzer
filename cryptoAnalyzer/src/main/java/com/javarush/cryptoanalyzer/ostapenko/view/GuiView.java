package com.javarush.cryptoanalyzer.ostapenko.view;

import com.javarush.cryptoanalyzer.ostapenko.constans.RussianAlphabet;
import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import static com.javarush.cryptoanalyzer.ostapenko.constans.ApplicationComplitionConstans.*;
import static com.javarush.cryptoanalyzer.ostapenko.constans.GuiViewConstans.*;


public class GuiView implements View {
    private Map<String, Runnable> handlers = new HashMap<>();

    private final Stage stage;
    private TextArea logArea;
    private Button selectFileButtonIn;
    private Button selectFileButtonOut;
    private Button selectFileButtonSource;
    private Button showLogButton;
    private TextField filePathFieldIn;
    private TextField filePathFieldOut;
    private TextField filePathFieldSource;
    private RadioButton encryptCheckBox;
    private RadioButton decryptCheckBox;
    private RadioButton bruteforceCheckBox;
    private RadioButton staticAnalyzeCheckBox;
    private RadioButton staticAnalyzeChangeChar;
    private RadioButton vigenereDecryptCheckBox;
    private RadioButton vigenereEncryptCheckBox;
    private Spinner<Integer> key;
    private Button startButton;
    private TextArea textFileIn;
    private TextArea textFileOut;
    private TextArea textFileSource;
    private Label labelForSource;
    private ToggleGroup checkBoxGroup;
    private Label labelForSourceChar;
    private ComboBox comboBoxSourceChar;
    private Label labelForChangeChar;
    private ComboBox comboBoxChangeChar;
    private Button changeChar;
    private Label keywordTip;
    private TextField keyWordContent;
    private Label keyTip;


    public GuiView(Stage stage) {
        this.stage = stage;
        initUI();
        setHandlers();
        stage.show();
    }

    private void initUI() {
        stage.setTitle(STAGE_TITLE);
        stage.setScene(createScene());
    }


    private void setHandlers() {
        checkBoxGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            switch (((RadioButton) newVal).getText()) {
                case CAESAR_ENCODE, CAESAR_DECODE, CAESAR_BRUTE_FORCE -> {
                    disableStaticKeyAndKeyWordObject(true);
                    disableStaticAnalyzeObject(true);
                    disableStaticAnalyzeChangeCharObject(true);
                }
                case STATIC_ANALYZE -> {
                    disableStaticKeyAndKeyWordObject(true);
                    disableStaticAnalyzeObject(false);
                    disableStaticAnalyzeChangeCharObject(true);
                }
                case CHANGE_CHAR -> {
                    disableStaticKeyAndKeyWordObject(true);
                    disableStaticAnalyzeObject(true);
                    disableStaticAnalyzeChangeCharObject(false);
                }
                case VIGENERE_ENCODE, VIGENERE_DECODE -> {
                    disableStaticKeyAndKeyWordObject(false);
                    disableStaticAnalyzeObject(true);
                    disableStaticAnalyzeChangeCharObject(true);
                }
            }
        });
    }


    private void disableStaticAnalyzeObject(boolean value) {
        filePathFieldSource.setDisable(value);
        textFileSource.setDisable(value);
        selectFileButtonSource.setDisable(value);
        labelForSource.setDisable(value);
    }

    private void disableStaticAnalyzeChangeCharObject(boolean value) {
        labelForSourceChar.setDisable(value);
        comboBoxSourceChar.setDisable(value);
        labelForChangeChar.setDisable(value);
        comboBoxChangeChar.setDisable(value);
        changeChar.setDisable(value);
    }
    private void disableStaticKeyAndKeyWordObject(boolean value) {
        keywordTip.setDisable(value);
        keyWordContent.setDisable(value);
        keyTip.setDisable(!value);
        key.setDisable(!value);
    }


    public Scene createScene() {
        HBox selectionPanelAll = getPathFilePanel();
        VBox controlPanelAll = getControlPanelAll();
        VBox logPaneAll = getLogPane();

        BorderPane root = new BorderPane();
        root.setTop(selectionPanelAll);
        BorderPane.setMargin(selectionPanelAll, new Insets(0, 20, 0, 0));
        root.setCenter(controlPanelAll);
        root.setBottom(logPaneAll);

        return new Scene(root, 1800, 1000);
    }

    private VBox getControlPanelAll() {
        keyTip = new Label(TIP_INT_KEY);
        HBox.setMargin(keyTip, new Insets(0, 0, 0, 20));
        int minKeyLenght = INT_KEY_MIX_VALUE;
        int maxKeyLength = RussianAlphabet.cyrillicValues().length - 1;
        key = new Spinner<>(minKeyLenght, maxKeyLength, 1);
        keywordTip = new Label(TIP_STR_KEY);
        keywordTip.setDisable(true);
        HBox.setMargin(keyTip, new Insets(0, 0, 0, 20));
        keyWordContent = new TextField();
        keyWordContent.setDisable(true);
        HBox keyInfo = new HBox(10, keyTip, key, keywordTip, keyWordContent);

        encryptCheckBox = new RadioButton(CAESAR_ENCODE);
        encryptCheckBox.setSelected(true);
        decryptCheckBox = new RadioButton(CAESAR_DECODE);
        decryptCheckBox.setSelected(false);
        bruteforceCheckBox = new RadioButton(CAESAR_BRUTE_FORCE);
        bruteforceCheckBox.setSelected(false);
        staticAnalyzeCheckBox = new RadioButton(STATIC_ANALYZE);
        staticAnalyzeCheckBox.setSelected(false);
        staticAnalyzeChangeChar = new RadioButton(CHANGE_CHAR);
        staticAnalyzeChangeChar.setSelected(false);
        vigenereEncryptCheckBox = new RadioButton(VIGENERE_ENCODE);
        vigenereEncryptCheckBox.setSelected(false);
        vigenereDecryptCheckBox = new RadioButton(VIGENERE_DECODE);
        vigenereDecryptCheckBox.setSelected(false);


        checkBoxGroup = new ToggleGroup();
        encryptCheckBox.setToggleGroup(checkBoxGroup);
        decryptCheckBox.setToggleGroup(checkBoxGroup);
        bruteforceCheckBox.setToggleGroup(checkBoxGroup);
        staticAnalyzeCheckBox.setToggleGroup(checkBoxGroup);
        staticAnalyzeChangeChar.setToggleGroup(checkBoxGroup);
        vigenereEncryptCheckBox.setToggleGroup(checkBoxGroup);
        vigenereDecryptCheckBox.setToggleGroup(checkBoxGroup);

        VBox controlPanelCheck = new VBox(10, encryptCheckBox, decryptCheckBox, bruteforceCheckBox, staticAnalyzeCheckBox,
                staticAnalyzeChangeChar, vigenereEncryptCheckBox, vigenereDecryptCheckBox);
        VBox.setMargin(encryptCheckBox, new Insets(0, 0, 0, 20));
        VBox.setMargin(decryptCheckBox, new Insets(0, 0, 0, 20));
        VBox.setMargin(bruteforceCheckBox, new Insets(0, 0, 0, 20));
        VBox.setMargin(staticAnalyzeCheckBox, new Insets(0, 0, 0, 20));
        VBox.setMargin(staticAnalyzeChangeChar, new Insets(0, 0, 0, 20));
        VBox.setMargin(vigenereEncryptCheckBox, new Insets(0, 0, 0, 20));
        VBox.setMargin(vigenereDecryptCheckBox, new Insets(0, 0, 0, 20));

        startButton = new Button("Запустить обработку");
        startButton.setDisable(true);
        HBox.setMargin(startButton, new Insets(0, 0, 0, 20));
        HBox controlPanelStart = new HBox(10, startButton);

        VBox controlPanelAll = new VBox(10, keyInfo, controlPanelCheck, controlPanelStart);
        BorderPane.setMargin(controlPanelAll, new Insets(10, 0, 0, 0)); // (top, right, bottom, left)
        return controlPanelAll;
    }

    private HBox getPathFilePanel() {
        textFileIn = new TextArea(DEFAULT_IN_AREA_TEXT);
        textFileIn.setEditable(false);
        textFileIn.setWrapText(true);
        ScrollPane logScrollPanelIn = new ScrollPane(textFileIn);
        logScrollPanelIn.setFitToWidth(true);
        logScrollPanelIn.setFitToHeight(true);
        Label labelForInput = new Label(LABEL_AREA_TEXT_IN);
        filePathFieldIn = new TextField();
        filePathFieldIn.setEditable(false);
        HBox.setHgrow(filePathFieldIn, Priority.ALWAYS);
        selectFileButtonIn = new Button(CHOOSE_FILE);

        textFileSource = new TextArea(DEFAULT_STATIC_AREA_TEXT);
        textFileSource.setEditable(false);
        textFileSource.setWrapText(true);
        textFileSource.setDisable(true);
        ScrollPane logScrollPanelSource = new ScrollPane(textFileSource);
        logScrollPanelSource.setFitToWidth(true);
        logScrollPanelSource.setFitToHeight(true);
        labelForSource = new Label(LABEL_AREA_TEXT_STATIC);
        labelForSource.setDisable(true);
        filePathFieldSource = new TextField();
        filePathFieldSource.setEditable(false);
        filePathFieldSource.setDisable(true);
        HBox.setHgrow(filePathFieldSource, Priority.ALWAYS);
        selectFileButtonSource = new Button(CHOOSE_FILE);
        selectFileButtonSource.setDisable(true);

        labelForSourceChar = new Label(LABEL_SOURCE_CHAR);
        labelForSourceChar.setDisable(true);
        comboBoxSourceChar = new ComboBox();
        comboBoxSourceChar.setDisable(true);
        setComboBoxSourceChar();
        labelForChangeChar = new Label(LABEL_TARGET_CHAR);
        labelForChangeChar.setDisable(true);
        comboBoxChangeChar = new ComboBox();
        comboBoxChangeChar.setDisable(true);
        setComboBoxChangeChar();
        changeChar = new Button(BUTTON_CHAGE_CHAR);
        changeChar.setDisable(true);


        textFileOut = new TextArea(DEFAULT_OUT_AREA_TEXT);
        textFileOut.setEditable(false);
        textFileOut.setWrapText(true);
        ScrollPane logScrollPanelOut = new ScrollPane(textFileOut);
        logScrollPanelOut.setFitToWidth(true);
        logScrollPanelOut.setFitToHeight(true);
        Label labelForOutput = new Label(LABEL_AREA_TEXT_OUT);
        filePathFieldOut = new TextField();
        filePathFieldOut.setEditable(false);
        HBox.setHgrow(filePathFieldOut, Priority.ALWAYS);
        selectFileButtonOut = new Button(CHOOSE_FILE);


        HBox selectionPanelIn = new HBox(10, labelForInput, filePathFieldIn, selectFileButtonIn);
        HBox selectionPanelOut = new HBox(10, labelForOutput, filePathFieldOut, selectFileButtonOut);
        HBox selectionPanelSource = new HBox(10, labelForSource, filePathFieldSource, selectFileButtonSource, comboBoxSourceChar);
        HBox selectionPanelChangeChar = new HBox(10, labelForSourceChar, comboBoxSourceChar, labelForChangeChar, comboBoxChangeChar);
        VBox selectionChangeFinal = new VBox(10, selectionPanelChangeChar, changeChar);
        HBox textSourceAreaAndSelectionChangePanel = new HBox(10, logScrollPanelSource, selectionChangeFinal);


        VBox panelWorkWithInAndSourceFile = new VBox(10, logScrollPanelIn, selectionPanelIn, textSourceAreaAndSelectionChangePanel, selectionPanelSource);
        HBox.setMargin(panelWorkWithInAndSourceFile, new Insets(0, 0, 0, 20));
        VBox panelWorkWithOutFile = new VBox(10, logScrollPanelOut, selectionPanelOut);

        HBox selectionPanelAll = new HBox(10, panelWorkWithInAndSourceFile, panelWorkWithOutFile);
        HBox.setHgrow(panelWorkWithInAndSourceFile, Priority.ALWAYS);
        HBox.setHgrow(panelWorkWithOutFile, Priority.ALWAYS);
        return selectionPanelAll;
    }

    private VBox getLogPane() {
        showLogButton = new Button(BUTTON_DISABLE_LOG);


        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setWrapText(true);
        logArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        ScrollPane logScrollPane = new ScrollPane(logArea);
        logScrollPane.setFitToWidth(true);
        logScrollPane.setFitToHeight(true);

        VBox logPanelAll = new VBox(10, showLogButton, logScrollPane);
        VBox.setMargin(showLogButton, new Insets(0, 0, 0, 20));
        VBox.setMargin(logScrollPane, new Insets(0, 20, 5, 20));

        return logPanelAll;
    }


    @Override
    public String[] getParametrs() {
        String[] parametrs = new String[8];
        if (isSelectedEncryptCheckBox()) {
            parametrs[0] = "ENCODE";
        }
        ;
        ;
        if (isSelectedDecryptCheckBox()) {
            parametrs[0] = "DECODE";
        }
        ;
        if (isSelectedBruteforceCheckBox()) {
            parametrs[0] = "BRUTEFORCE";
        }
        ;
        if (isSelectedStaticAnalyzeCheckBox()) {
            parametrs[0] = "STATIC_ANALYZE";
        };
        if(isSelectedStaticAnalyzeChangeChar()){
            parametrs[0] = "CHANGES_CHAR";
        }
        if(isSelectedVigenereEncryptCheckBox()){
            parametrs[0] = "VIGENER_ENCODE";
        }
        if(isSelectedVigenereDecryptCheckBox()){
            parametrs[0] = "VIGENER_DECODE";
        }

        parametrs[1] = filePathFieldIn.getText();
        parametrs[2] = filePathFieldOut.getText();
        parametrs[3] = String.valueOf(key.getValue());
        parametrs[4] = filePathFieldSource.getText();
        parametrs[5] = String.valueOf(comboBoxSourceChar.getValue());
        parametrs[6] = String.valueOf(comboBoxChangeChar.getValue());
        parametrs[7] = keyWordContent.getText();
        return parametrs;
    }


    @Override
    public void printResult(Result result) {
        switch (result.getResultCode()) {
            case OK -> {
                log(SUCCESS);
                if(result.getMessage()!=null && !result.getMessage().isEmpty()){
                    log(result.getMessage());
                    showInfoMessage(result.getMessage());
                }

            }
            case ERROR -> {
                System.out.println(EXCEPTION + result.getApplicationException().getMessage());
                showAlertMessage(result.getApplicationException().getMessage());
                for(StackTraceElement element : result.getApplicationException().getStackTrace()){
                    log(String.valueOf(element));
                }

            }
        }
    }

    public void setSelectFileButtonInHandler(Runnable handler) {
        selectFileButtonIn.setOnAction(e -> handler.run());
    }

    public void log(String message) {
        logArea.appendText(message + "\n");
    }
    private void showInfoMessage(String message){
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle(TITLE_INFORMATION);
        infoAlert.setContentText(message);
        infoAlert.showAndWait();
    }
    private void showAlertMessage(String message){
        Alert infoAlert = new Alert(Alert.AlertType.ERROR);
        infoAlert.setTitle(TITLE_ERROR);
        infoAlert.setContentText(message);
        infoAlert.showAndWait();
    }

    public void setSelectFileButtonOutHandler(Runnable handler) {
        selectFileButtonOut.setOnAction(e -> handler.run());
    }

    public void setFilePathFieldIn(String text) {
        filePathFieldIn.setText(text);
    }

    public void setFilePathFieldOut(String text) {
        filePathFieldOut.setText(text);
    }

    public void setFilePathFieldSource(String text) {
        filePathFieldSource.setText(text);
    }

    public String getFilePathFieldIn() {
        return filePathFieldIn.getText();
    }

    public String getFilePathFieldOut() {
        return filePathFieldOut.getText();
    }

    public String getFilePathFieldSource() {
        return filePathFieldSource.getText();
    }

    public boolean isStartButtonAvailability() {
        if (filePathFieldOut.getText() != null
                && filePathFieldIn.getText() != null
                && !filePathFieldIn.getText().isEmpty()
                && !filePathFieldOut.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void setEnabledStartButton(boolean b) {
        startButton.setDisable(!b);
    }


    public boolean isSelectedEncryptCheckBox() {
        return encryptCheckBox.isSelected();
    }


    public boolean isSelectedDecryptCheckBox() {
        return decryptCheckBox.isSelected();
    }

    public boolean isSelectedBruteforceCheckBox() {
        return bruteforceCheckBox.isSelected();
    }

    public boolean isSelectedStaticAnalyzeCheckBox() {
        return staticAnalyzeCheckBox.isSelected();
    }
    public boolean isSelectedStaticAnalyzeChangeChar() {
        return staticAnalyzeChangeChar.isSelected();
    }
    public boolean isSelectedVigenereEncryptCheckBox() {
        return vigenereEncryptCheckBox.isSelected();
    }
    public boolean isSelectedVigenereDecryptCheckBox() {
        return vigenereDecryptCheckBox.isSelected();
    }




    public void setStartButton(Runnable handler) {
        startButton.setOnAction(e -> handler.run());
    }
    public void setChangeChar(Runnable handler) {
        changeChar.setOnAction(e -> handler.run());
    }

    public void setTextFileIn(String textIn) {
        this.textFileIn.setText(textIn);
    }

    public void setTextFileOut(String textOut) {
        this.textFileOut.setText(textOut);
    }

    public void setTextFileSource(String textSource) {
        this.textFileSource.setText(textSource);
    }

    public void setSelectFileButtonSourceHandler(Runnable handler) {
        selectFileButtonSource.setOnAction(e -> handler.run());
    }

    public void setComboBoxChangeChar() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (char c : RussianAlphabet.cyrillicValues()) {
            items.add(String.valueOf(Character.toLowerCase(c)));
        }
        comboBoxChangeChar.setItems(items);
    }

    public void setComboBoxSourceChar() {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (char c : RussianAlphabet.cyrillicValues()) {
            items.add(String.valueOf(Character.toLowerCase(c)));
        }
        comboBoxSourceChar.setItems(items);
    }


}

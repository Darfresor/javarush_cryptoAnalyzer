package com.javarush.cryptoanalyzer.ostapenko.view;

import com.javarush.cryptoanalyzer.ostapenko.entity.Result;
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

import static com.javarush.cryptoanalyzer.ostapenko.constans.ApplicationComplitionConstans.EXCEPTION;
import static com.javarush.cryptoanalyzer.ostapenko.constans.ApplicationComplitionConstans.SUCCESS;

//TODO вынести текст в константы
//TODO разделить на методы init component, actionlistener component, и в конце уже вызов сцены с ее параметрами.
//Сделать отдельный класс унаследованный от stage и туда перенести логику?
public class GuiView implements View {
    private Map<String, Runnable> handlers = new HashMap<>();

    private final Stage stage;
    private TextArea logArea;
    private Button selectFileButtonIn;
    private Button selectFileButtonOut;
    private Button selectFileButtonSource;
    private Button showLogButton;
    private Button changeChar;
    private TextField filePathFieldIn;
    private TextField filePathFieldOut;
    private TextField filePathFieldSource;
    private RadioButton encryptCheckBox;
    private RadioButton decryptCheckBox;
    private RadioButton bruteforceCheckBox;
    private RadioButton staticAnalyzeCheckBox;
    private RadioButton vigenereDecryptCheckBox;
    private RadioButton vigenereEncryptCheckBox;
    private Spinner<Integer> key;
    private Button startButton;
    private TextArea textFileIn;
    private TextArea textFileOut;
    private TextArea textFileSource;


    public GuiView(Stage stage) {
        this.stage = stage;
        initUI();
    }

    private void initUI() {
        stage.setTitle("Криптоанализатор");
        stage.setScene(createScene());
        stage.show();
    }

    public Scene createScene() {
        //TODO вынести в константы названия кнопок
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
        Label keyTip = new Label("Укажите длину ключа:");
        HBox.setMargin(keyTip, new Insets(0, 0, 0, 20));
        int minKeyLenght = 1;
        //int maxKeyLength = DefaultRussianAlphabet.values().length - 1;
        //TODO временная замена на константу, вернуть когда все будет перенесено в новый проект
        int maxKeyLength = 32;
        key = new Spinner<>(minKeyLenght, maxKeyLength, 1);
        HBox keyInfo = new HBox(10, keyTip, key);

        encryptCheckBox = new RadioButton("Шифровать с ключом(Шифр Цезаря)");
        encryptCheckBox.setSelected(true);
        decryptCheckBox = new RadioButton("Расшифровать с ключом(Шифр Цезаря)");
        decryptCheckBox.setSelected(false);
        bruteforceCheckBox = new RadioButton("Расшифровать без ключа(BrutForce для шифра Цезаря)");
        bruteforceCheckBox.setSelected(false);
        staticAnalyzeCheckBox = new RadioButton("Расшифровать статистическим анализом");
        staticAnalyzeCheckBox.setSelected(false);
        vigenereEncryptCheckBox = new RadioButton("Шифровать словом-ключом(шифр Виженера)");
        vigenereEncryptCheckBox.setSelected(false);
        vigenereEncryptCheckBox.setDisable(true);
        vigenereDecryptCheckBox = new RadioButton("Расшифровать словом-ключом(шифр Виженера)");
        vigenereDecryptCheckBox.setSelected(false);
        vigenereDecryptCheckBox.setDisable(true);


        ToggleGroup checkBoxGroup = new ToggleGroup();
        encryptCheckBox.setToggleGroup(checkBoxGroup);
        decryptCheckBox.setToggleGroup(checkBoxGroup);
        bruteforceCheckBox.setToggleGroup(checkBoxGroup);
        staticAnalyzeCheckBox.setToggleGroup(checkBoxGroup);
        vigenereEncryptCheckBox.setToggleGroup(checkBoxGroup);
        vigenereDecryptCheckBox.setToggleGroup(checkBoxGroup);

        VBox controlPanelCheck = new VBox(10, encryptCheckBox, decryptCheckBox, bruteforceCheckBox, staticAnalyzeCheckBox, vigenereEncryptCheckBox, vigenereDecryptCheckBox);
        VBox.setMargin(encryptCheckBox, new Insets(0, 0, 0, 20));
        VBox.setMargin(decryptCheckBox, new Insets(0, 0, 0, 20));
        VBox.setMargin(bruteforceCheckBox, new Insets(0, 0, 0, 20));
        VBox.setMargin(staticAnalyzeCheckBox, new Insets(0, 0, 0, 20));
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
        textFileIn = new TextArea("Здесь будут данные входного файла");
        textFileIn.setEditable(false);
        textFileIn.setWrapText(true);
        ScrollPane logScrollPanelIn = new ScrollPane(textFileIn);
        logScrollPanelIn.setFitToWidth(true);
        logScrollPanelIn.setFitToHeight(true);
        Label labelForInput = new Label("Путь к файлу с входными данными:");
        filePathFieldIn = new TextField();
        filePathFieldIn.setEditable(false);
        HBox.setHgrow(filePathFieldIn, Priority.ALWAYS);
        selectFileButtonIn = new Button("Выбрать файл");

        textFileSource = new TextArea("Здесь будут данные файла для статистического анализа");
        textFileSource.setEditable(false);
        textFileSource.setWrapText(true);
        textFileSource.setDisable(true);
        ScrollPane logScrollPanelSource = new ScrollPane(textFileSource);
        logScrollPanelSource.setFitToWidth(true);
        logScrollPanelSource.setFitToHeight(true);
        Label labelForSource = new Label("Путь к файлу для статистического анализа:");
        labelForSource.setDisable(true);
        filePathFieldSource = new TextField();
        filePathFieldSource.setEditable(false);
        filePathFieldSource.setDisable(true);
        HBox.setHgrow(filePathFieldSource, Priority.ALWAYS);
        selectFileButtonSource = new Button("Выбрать файл");
        selectFileButtonSource.setDisable(true);

        Label labelForSourceChar = new Label("Исходная буква:");
        labelForSourceChar.setDisable(true);
        ComboBox comboBoxSourceChar = new ComboBox();
        comboBoxSourceChar.setDisable(true);
        Label labelForChangeChar = new Label("Заменяемая буква:");
        labelForChangeChar.setDisable(true);
        ComboBox comboBoxChangeChar = new ComboBox();
        comboBoxChangeChar.setDisable(true);
        Button changeChar = new Button("Заменить символы");
        changeChar.setDisable(true);


        textFileOut = new TextArea("Здесь будут данные выходного файла");
        textFileOut.setEditable(false);
        textFileOut.setWrapText(true);
        ScrollPane logScrollPanelOut = new ScrollPane(textFileOut);
        logScrollPanelOut.setFitToWidth(true);
        logScrollPanelOut.setFitToHeight(true);
        Label labelForOutput = new Label("Путь к файлу с выходными данными:");
        filePathFieldOut = new TextField();
        filePathFieldOut.setEditable(false);
        HBox.setHgrow(filePathFieldOut, Priority.ALWAYS);
        selectFileButtonOut = new Button("Выбрать файл");


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
        showLogButton = new Button("Скрыть лог");


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
        String[] parametrs = new String[4];
        if (isSelectedEncryptCheckBox()) {
            parametrs[0] = "ENCODE";
        };
        ;
        if (isSelectedDecryptCheckBox()) {
            parametrs[0] = "DECODE";
        };
        if (isSelectedBruteforceCheckBox()) {
            parametrs[0] = "BRUTEFORCE";
        };
        parametrs[1] = filePathFieldIn.getText();
        parametrs[2] = filePathFieldOut.getText();
        parametrs[3] = String.valueOf(key.getValue());
        return parametrs;
    }

    @Override
    public void printResult(Result result) {
        switch (result.getResultCode()) {
            case OK -> System.out.println(SUCCESS);
            case ERROR -> System.out.println(EXCEPTION + result.getApplicationException().getMessage());
        }
    }

    public void setSelectFileButtonInHandler(Runnable handler) {
        selectFileButtonIn.setOnAction(e -> handler.run());
    }

    public void log(String message) {
        logArea.appendText(message + "\n");
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


    public void setStartButton(Runnable handler) {
        startButton.setOnAction(e -> handler.run());
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




}

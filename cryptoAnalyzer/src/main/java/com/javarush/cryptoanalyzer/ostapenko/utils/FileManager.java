package com.javarush.cryptoanalyzer.ostapenko.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public final class FileManager {
    private FileManager(){};    /**
     * @throws IOException when filePath wrong;
     */
    public static String readFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + filePath, e);
        }
    }

    ;

    /**
     * @throws IOException when filePath wrong;
     */
    public static void writeFile(String content, String filePath) {
        try {
        Path path = Paths.get(filePath);
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        Files.write(path, bytes);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи файла: " + filePath, e);
        }
    }

    ;
}

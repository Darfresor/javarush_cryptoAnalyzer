package com.javarush.cryptoanalyzer.ostapenko.utils;

import com.javarush.cryptoanalyzer.ostapenko.exception.NonReadingFileException;
import com.javarush.cryptoanalyzer.ostapenko.exception.NonWritingException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.javarush.cryptoanalyzer.ostapenko.constans.FileManagerConstants.ERROR_READ;
import static com.javarush.cryptoanalyzer.ostapenko.constans.FileManagerConstants.ERROR_WRITE;


public final class FileManager {
    private FileManager(){};
    /**
     * @throws IOException when filePath wrong;
     */
    public static String readFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new NonReadingFileException(String.format(ERROR_READ,filePath), e);
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
            throw new NonWritingException(String.format(ERROR_WRITE,filePath), e);
        }
    }

    ;
}

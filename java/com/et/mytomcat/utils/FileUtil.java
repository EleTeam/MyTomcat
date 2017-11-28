package com.et.mytomcat.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    public static String getFileContent(String path) throws FileNotFoundException, IOException {
        StringBuffer stringBuffer = new StringBuffer();

        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }

        return stringBuffer.toString();
    }

}

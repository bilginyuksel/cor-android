package com.huawei.hms.cordova.example.basef.handler;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TSCFileUtils {

    public static void createFile(String fileName, String fileContent) {
        try {
            String root = Environment.getExternalStorageDirectory().toString();
            File scriptsFolder = new File(root + "/scripts");
            if (!scriptsFolder.exists()) {
                scriptsFolder.mkdirs();
            }

            File file = new File(scriptsFolder, fileName);
            file.createNewFile();

            FileWriter myWriter = new FileWriter(file);
            myWriter.write(fileContent);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

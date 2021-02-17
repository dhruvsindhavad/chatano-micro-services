package com.chatano.webbasedgui.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class FileHandler {

    final static String contactUsFilePath = System.getProperty("catalina.base") + "//contactus.txt";

    public boolean writeIntoContactUs(String data) {
        System.out.println("Inside writeIntoContactUs with Data = " + data);
        FileWriter fw = null;
        BufferedWriter bw = null;
        File fileContactUs = new File(contactUsFilePath);
        try {
            fileContactUs.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileContactUs.exists()) {
            try {
                fw = new FileWriter(fileContactUs.getAbsoluteFile(), true);
                bw = new BufferedWriter(fw);
                bw.write("\n" + data);
                bw.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (bw != null)
                        bw.close();

                    if (fw != null)
                        fw.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}

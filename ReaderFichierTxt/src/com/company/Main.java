package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String file = "C:/Users/thoma/OneDrive/Bureau/Workplace/IntelliJ/ReaderFichierTxt/test.srt";

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        while (true) {
            try {
                if (!((line = br.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(line);
        }
        br.close();
        // write your code here
    }
}

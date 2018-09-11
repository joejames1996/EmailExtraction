package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main
{
    static String filePath = "C:/Work/Training/EmailExtraction";
    static String fileName = "sample.txt";
    static String sub = "@softwire.com";

    public static void main(String[] args) throws IOException
    {
        String file = loadSampleFile();
        int count = countInString(file);
        System.out.println(sub + ": " + count + " times.");
    }

    static String loadSampleFile() throws IOException
    {
        Path path = Paths.get(filePath, fileName);
        byte[] sample = null;
        sample = Files.readAllBytes(path);
        return new String(sample);
    }

    static int countInString(String file)
    {
        int count = 0;
        for(int i = 0; i < file.length() - sub.length(); i++)
        {
            String checkString = file.substring(i, i + sub.length());
            if(checkString.equals(sub)) count++;
        }
        return count;
    }
}

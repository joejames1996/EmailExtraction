package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    static String filePath = "C:/Work/Training/EmailExtraction";
    static String fileName = "sample.txt";
    static String sub = "@softwire.com";
    static String softwireRegex = "\\W([A-Za-z\\.'_%\\+-]+@softwire\\.com)\\W";
    static String regex = "\\W([A-Za-z\\.'_%\\+-]+(@[A-Za-z\\.'_%\\+-]+\\.[A-Za-z.]+))\\W";

    static Map<String, Integer> emailMap = new HashMap();

    public static void main(String[] args) throws IOException
    {
        String file = loadSampleFile();
        //int count = countInString(file);
        //int count = countValidSoftwireEmails(file);
        //System.out.println(sub + ": " + count + " times.");
        addEmailsToMap(file);
        printEmailMap();
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

    static int countValidSoftwireEmails(String file)
    {
        int count = 0;
        Pattern pattern = Pattern.compile(softwireRegex);
        Matcher matcher = pattern.matcher(file);
        while(!matcher.hitEnd())
        {
            if(matcher.find()) count++;
        }
        return count;
    }

    static void addEmailsToMap(String file)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(file);
        while(!matcher.hitEnd())
        {
            if(matcher.find())
            {
                String domain = matcher.group(2);
                if(emailMap.containsKey(domain))
                {
                    int value = emailMap.get(domain);
                    emailMap.remove(domain);
                    value++;
                    emailMap.put(domain, value);
                }
                else
                {
                    emailMap.put(domain, 1);
                }
            }
        }
    }

    static void printEmailMap()
    {
        for(String s : emailMap.keySet())
        {
            System.out.println(s + " appears " + emailMap.get(s) + " times.");
        }
    }
}

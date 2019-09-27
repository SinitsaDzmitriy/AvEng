package edu.sam.aveng.base.util;

import org.apache.tika.langdetect.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageDetector;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Runner {

    public static void main(String[] args) throws IOException {

        Set<String> supportedLangs = new HashSet<>();
        supportedLangs.add("en");
        supportedLangs.add("de");
        supportedLangs.add("ru");

        LanguageDetector langDetector = new OptimaizeLangDetector();
        langDetector.loadModels(supportedLangs);

        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i < 10; i++) {

            String input = scanner.nextLine();
            System.out.println(langDetector.detect(input).getLanguage());

        }

    }

}

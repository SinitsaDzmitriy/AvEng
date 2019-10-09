package edu.sam.aveng.base.util;

import edu.sam.aveng.base.model.entity.Sample;
import edu.sam.aveng.base.model.enumeration.Lang;

import org.apache.tika.langdetect.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageDetector;
import org.hibernate.search.analyzer.Discriminator;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SampleLangDiscriminator implements Discriminator {

    public String getAnalyzerDefinitionName(Object value, Object entity, String field) {
        String analyzerName = null;

        if(value != null && entity instanceof Sample) {
            try {
                String content = (String) value;
                Set<String> supportedLangs = new HashSet<>();
                Lang[] langs = Lang.values();

                for(Lang lang: langs) {
                    supportedLangs.add(lang.getCode());
                }

                LanguageDetector langDetector = new OptimaizeLangDetector();
                langDetector.loadModels(supportedLangs);
                analyzerName = langDetector.detect(content).getLanguage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return analyzerName;
    }
}

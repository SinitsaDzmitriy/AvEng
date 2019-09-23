package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.model.entity.Sample;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;


@RestController

@RequestMapping(value = "/utility")
public class UtilityController {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Actual mappings:

    @GetMapping("/populate-db-with-english-samples")
    public String supply() {

        BufferedReader reader = null;
        int counter = 0;

        try {

            reader = new BufferedReader(new FileReader("c:\\EnglishSentences.txt"));

            String tmp = null;

            Session session = sessionFactory.openSession();
            Transaction transaction;

            while ((tmp = reader.readLine()) != null) {

                if (tmp.length() <= 255
                        && tmp.length() >= 50
                        && Pattern.matches("[\\p{Alnum} ',.:!?-]{50,255}", tmp)) {

                    try {

                        transaction = session.beginTransaction();
                        session.persist(new Sample(tmp));
                        transaction.commit();

                        // If Sample has been persisted without exceptions
                        counter++;

                    } catch (Exception e) {
                        System.out.println("Exception is occurred.");
                    }

                }

            }

            session.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return counter + " Samples were persisted.";

    }

    @PostMapping("/index-samples")
    public void index() {

        try {
            FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.openSession());
            fullTextSession.createIndexer().startAndWait();
            fullTextSession.close();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

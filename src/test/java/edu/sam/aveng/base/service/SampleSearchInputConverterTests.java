package edu.sam.aveng.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.sam.aveng.base.converter.search.SampleSearchInputConverter;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/*
    ToDo: Random character number generation
*/

public class SampleSearchInputConverterTests {

    private final SampleSearchInputConverter subject = new SampleSearchInputConverter();

    private final List<String> EXPECTED_CRITERIAS_FOR_WORD;
    private final List<String> EXPECTED_CRITERIAS_FOR_PHRASE;

    public SampleSearchInputConverterTests() {

        EXPECTED_CRITERIAS_FOR_WORD = new ArrayList<>(2);
        EXPECTED_CRITERIAS_FOR_WORD.add("'query%'");
        EXPECTED_CRITERIAS_FOR_WORD.add("'% query%'");

        EXPECTED_CRITERIAS_FOR_PHRASE = new ArrayList<>(2);
        EXPECTED_CRITERIAS_FOR_PHRASE.add("'very% long% search% query%'");
        EXPECTED_CRITERIAS_FOR_PHRASE.add("'% very% long% search% query%'");
    }

    @Test
    @Order(1)
    public void standardLowercaseWord() {
        final String INPUT = "query";
        assertEquals(EXPECTED_CRITERIAS_FOR_WORD, subject.convertToLikeCriterias(INPUT));
    }

    @Test
    @Order(2)
    public void standardLowercasePhrase() {
        final String INPUT = "very long search query";
        assertEquals(EXPECTED_CRITERIAS_FOR_PHRASE, subject.convertToLikeCriterias(INPUT));
    }

    @Test
    @Order(3)
    public void inputWithMixedCase() {
        final String INPUT = "VerY lonG SeArCh quERy";
        assertEquals(EXPECTED_CRITERIAS_FOR_PHRASE, subject.convertToLikeCriterias(INPUT));
    }

    @Test
    @Order(4)
    public void inputWithMixedBlanks() {
        final String INPUT = "  very    long     search     query      ";
        assertEquals(EXPECTED_CRITERIAS_FOR_PHRASE, subject.convertToLikeCriterias(INPUT));
    }

    @Test
    @Order(5)
    public void inputWithMixedSqlSpecialCharacters() {
        final String INPUT = "%very_ long_ %search %%query__";
        assertEquals(EXPECTED_CRITERIAS_FOR_PHRASE, subject.convertToLikeCriterias(INPUT));
    }

}

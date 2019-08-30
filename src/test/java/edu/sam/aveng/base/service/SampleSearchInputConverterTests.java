package edu.sam.aveng.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.sam.aveng.base.converter.search.SampleSearchInputConverter;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/*
    ToDo: Random character number generation
*/

public class SampleSearchInputConverterTests {

    private final SampleSearchInputConverter subject = new SampleSearchInputConverter();

    private final String EXPECTED_VALUE_FOR_WORD = "'query%' OR '% query%'";
    private final String EXPECTED_VALUE_FOR_PHRASE = "'very% long% search% query%' OR '% very% long% search% query%'";

    @Test
    @Order(1)
    public void standardLowercaseWord() {
        final String INPUT = "query";
        assertEquals(EXPECTED_VALUE_FOR_WORD, subject.convertToCriteria(INPUT));
    }

    @Test
    @Order(2)
    public void standardLowercasePhrase() {
        final String INPUT = "very long search query";
        assertEquals(EXPECTED_VALUE_FOR_PHRASE, subject.convertToCriteria(INPUT));
    }

    @Test
    @Order(3)
    public void inputWithMixedCase() {
        final String INPUT = "VerY lonG SeArCh quERy";
        assertEquals(EXPECTED_VALUE_FOR_PHRASE, subject.convertToCriteria(INPUT));
    }

    @Test
    @Order(4)
    public void inputWithMixedBlanks() {
        final String INPUT = "  very    long     search     query      ";
        assertEquals(EXPECTED_VALUE_FOR_PHRASE, subject.convertToCriteria(INPUT));
    }

    @Test
    @Order(5)
    public void inputWithMixedSqlSpecialCharacters() {
        final String INPUT = "%very_ long_ %search %%query__";
        assertEquals(EXPECTED_VALUE_FOR_PHRASE, subject.convertToCriteria(INPUT));
    }

}

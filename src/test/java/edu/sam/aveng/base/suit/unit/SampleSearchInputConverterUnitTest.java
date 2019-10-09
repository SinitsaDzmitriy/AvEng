package edu.sam.aveng.base.suit.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.sam.aveng.base.converter.search.SampleSearchInputConverter;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class SampleSearchInputConverterUnitTest {
    private SampleSearchInputConverter subject = new SampleSearchInputConverter();

    private List<String> expectedCriteriasForWord;
    private List<String> expectedCriteriasForPhrase;

    public SampleSearchInputConverterUnitTest() {

        expectedCriteriasForWord = new ArrayList<>(2);
        expectedCriteriasForWord.add("'query%'");
        expectedCriteriasForWord.add("'% query%'");

        expectedCriteriasForPhrase = new ArrayList<>(2);
        expectedCriteriasForPhrase.add("'very% long% like% search% query%'");
        expectedCriteriasForPhrase.add("'% very% long% like% search% query%'");
    }

    @Test
    @Order(1)
    public void standardLowercaseWord() {
        final String INPUT = "query";
        assertEquals(expectedCriteriasForWord, subject.convertToLikeCriterias(INPUT));
    }

    @Test
    @Order(2)
    public void standardLowercasePhrase() {
        final String INPUT = "very long like search query";
        assertEquals(expectedCriteriasForPhrase, subject.convertToLikeCriterias(INPUT));
    }

    @Test
    @Order(3)
    public void inputWithMixedCase() {
        final String INPUT = "VerY lonG LIKE SeArCh quERy";
        assertEquals(expectedCriteriasForPhrase, subject.convertToLikeCriterias(INPUT));
    }

    @Test
    @Order(4)
    public void inputWithMixedBlanks() {
        final String INPUT = "  very    long     like         search     query      ";
        assertEquals(expectedCriteriasForPhrase, subject.convertToLikeCriterias(INPUT));
    }

    @Test
    @Order(5)
    public void inputWithMixedSqlSpecialCharacters() {
        final String INPUT = "%very_ long_ %like search %%query__";
        assertEquals(expectedCriteriasForPhrase, subject.convertToLikeCriterias(INPUT));
    }
}

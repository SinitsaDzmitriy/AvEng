package edu.sam.aveng.base.converter.search;

/*
    ToDo: Find a proper way to concat String's
    ToDo: Cover other important test cases in future
*/

public class SampleSearchInputConverter implements ISearchInputConverter {

    @Override
    public String convertToCriteria(String searchInput) {

        String[] searchInputParts = searchInput.trim().toLowerCase()
                .replaceAll("[%_]+", "").split("\\p{Blank}+");

        StringBuilder criteriaToMatchStringBeginningBuilder = new StringBuilder();
        StringBuilder criteriaToMatchOtherPositionsBuilder = new StringBuilder();
        StringBuilder criteriaCommonPart = new StringBuilder();

        criteriaToMatchStringBeginningBuilder
                .append("'")
                .append(searchInputParts[0])
                .append("%");

        criteriaToMatchOtherPositionsBuilder
                .append("'% ")
                .append(searchInputParts[0])
                .append("%");

        for (int i = 1; i < searchInputParts.length; i++) {
            criteriaCommonPart
                    .append(" ")
                    .append(searchInputParts[i])
                    .append("%");
        }

        criteriaCommonPart.append("'");

        return new StringBuilder()
                .append(criteriaToMatchStringBeginningBuilder)
                .append(criteriaCommonPart)
                .append(" OR ")
                .append(criteriaToMatchOtherPositionsBuilder)
                .append(criteriaCommonPart)
                .toString();
    }
}

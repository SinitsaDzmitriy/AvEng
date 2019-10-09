package edu.sam.aveng.base.converter.search;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchInputConverter implements ISearchInputConverter {

    @Override
    public List<String> convertToLikeCriterias(String searchInput) {

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

        List<String> likeCriterias = new ArrayList<>(2);

        likeCriterias.add(criteriaToMatchStringBeginningBuilder.append(criteriaCommonPart).toString());
        likeCriterias.add(criteriaToMatchOtherPositionsBuilder.append(criteriaCommonPart).toString());

        return likeCriterias;
    }
}

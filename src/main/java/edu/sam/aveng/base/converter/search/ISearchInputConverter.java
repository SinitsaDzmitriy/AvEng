package edu.sam.aveng.base.converter.search;

import java.util.List;

public interface ISearchInputConverter {
    List<String> convertToLikeCriterias(String searchInput);
}

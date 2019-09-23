package edu.sam.aveng.base.dao.sample;

import edu.sam.aveng.base.contract.dao.IGenericDao;
import edu.sam.aveng.base.model.entity.Sample;


import java.util.List;

public interface ISampleDao extends IGenericDao<Sample> {

    Sample findByContent(String content);

    List<Sample> likeSearch(List<String> likeCriterias);

    List<Sample> fullTextSearch(String searchQuery);

}

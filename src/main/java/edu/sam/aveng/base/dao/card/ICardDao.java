package edu.sam.aveng.base.dao.card;

import edu.sam.aveng.base.contract.v2.dao.IGenericDao;
import edu.sam.aveng.base.model.entity.Card;
import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.transfer.CardTableItem;

import java.util.List;
import java.util.Map;

public interface ICardDao extends IGenericDao<Card> {

    List<CardTableItem> findAllAsTableItems();

    List<CardTableItem> findAllAsTableItems(int maxNumberOfResults, int firstResultPosition);

    long countAll();

    List<Map> search(Lang cardLang, Lang coupledCardsLang, String formattedSearchInput);

}

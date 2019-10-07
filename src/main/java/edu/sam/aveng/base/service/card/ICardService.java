package edu.sam.aveng.base.service.card;

import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.dto.CardDto;
import edu.sam.aveng.base.model.transfer.CardTableItem;

import java.util.List;
import java.util.Map;

public interface ICardService {

    void create(final CardDto cardDto);

    CardDto findOne(final long id);

    List<CardTableItem> findAllAsTableItems();

    List<CardTableItem> findAllAsTableItems(int maxNumberOfResults, int firstResultPosition);

    void update(Long id, CardDto cardDto);

    void delete(long id);

    long countAll();

    List<Map> search(Lang usedLang, Lang desiredLang, String userInput);
}

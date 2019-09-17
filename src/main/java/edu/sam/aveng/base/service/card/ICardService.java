package edu.sam.aveng.base.service.card;

import edu.sam.aveng.base.model.domain.enumeration.Lang;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.CardTableItem;

import java.util.List;
import java.util.Map;

public interface ICardService {

    void create(final CardDto cardDto);

    CardDto findOne(final long id);

    List<CardTableItem> findAllAsTableItems();

    void update(Long id, CardDto cardDto);

    void delete(long id);

    List<Map> search(Lang usedLang, Lang desiredLang, String userInput);
}

package edu.sam.aveng.base.service.card.simple;

import edu.sam.aveng.base.model.domain.enumeration.Lang;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.ShortCardDto;

import java.util.List;
import java.util.Map;

public interface ISimpleCardService {
    void create(CardDto cardDto);

    List<ShortCardDto> findAll();

    CardDto findOne(long id);

    void update(long  cardId, CardDto cardDto);

    void delete(long cardId);

    List<Map> search(Lang usedLang, Lang desiredLang, String userInput);

}

package edu.sam.aveng.base.service;

import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.ShortCardDto;

import java.util.List;

public interface IOldCardService {
    void create(CardDto cardDto);

    List<ShortCardDto> findAll();

    CardDto findOne(long id);

    void update(long  cardId, CardDto cardDto);

    void delete(long cardId);
}

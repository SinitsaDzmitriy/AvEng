package edu.sam.aveng.service;

import edu.sam.aveng.dto.CardDto;
import edu.sam.aveng.dto.ShortCardDto;

import java.util.List;

public interface ICardService {
    void create(CardDto cardDto);
    List<ShortCardDto> findAll();
    CardDto findOne(long id);
    ShortCardDto findShortOne(long id);
    void update(long  cardId, CardDto cardDto);
    void delete(long cardId);
}

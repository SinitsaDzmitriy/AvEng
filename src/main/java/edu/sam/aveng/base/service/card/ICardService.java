package edu.sam.aveng.base.service.card;

import edu.sam.aveng.base.contract.converter.IShortConverter;
import edu.sam.aveng.base.contract.service.ICrudService;
import edu.sam.aveng.base.model.domain.Card;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.ShortCardDto;

public interface ICardService
        extends ICrudService<Card, CardDto, ShortCardDto,
        IShortConverter<Card, CardDto, ShortCardDto>> {
}

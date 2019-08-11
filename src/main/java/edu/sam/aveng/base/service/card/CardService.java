package edu.sam.aveng.base.service.card;

import edu.sam.aveng.base.contract.converter.IShortConverter;
import edu.sam.aveng.base.contract.service.SmartGenericCrudService;
import edu.sam.aveng.base.model.domain.Card;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.ShortCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@Service
@EnableTransactionManagement
@Transactional
public class CardService
    extends SmartGenericCrudService<Card, CardDto, ShortCardDto>
    implements ICardService {

    @Override
    @Autowired
    @Qualifier("cardConverter")
    public void converter(IShortConverter<Card, CardDto, ShortCardDto> converter) {
        setConverter(converter);
    }
}

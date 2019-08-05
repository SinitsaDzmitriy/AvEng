package edu.sam.aveng.service;

import edu.sam.aveng.dao.IGenericDao;
import edu.sam.aveng.domain.Card;
import edu.sam.aveng.domain.Sample;
import edu.sam.aveng.dto.CardDto;
import edu.sam.aveng.dto.ShortCardDto;
import edu.sam.aveng.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
@Transactional
public class CardServiceImpl implements ICardService {

    private IGenericDao<Card> cardDao;

    @Autowired
    public void setDao(IGenericDao<Card> daoToSet) {
        cardDao = daoToSet;
        cardDao.setClazz(Card.class);
    }

    @Override
    public void create(CardDto cardDto) {
        Card newCard = new Card();

        List<Sample> sampleList = cardDto.getSamples().stream()
                .map(Converter::convertToEntity)
                .collect(Collectors.toList());

        newCard.setContent(cardDto.getContent());
        newCard.setType(cardDto.getType());
        newCard.setPron(Converter.convertToEntity(cardDto.getPron()));
        newCard.setDefinition(cardDto.getDefinition());
        newCard.addSamples(sampleList);

        cardDao.create(newCard);
    }

    @Override
    public List<ShortCardDto> findAll() {
        return cardDao.findAll().stream()
                .map(Converter::convertToShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public CardDto findOne(long id) {
        return Converter.convertToDto(cardDao.findOne(id));
    }

    @Override
    public void update(long cardId, CardDto cardDto) {
        cardDto.setId(cardId);
        cardDao.update(Converter.convertToEntity(cardDto));
    }

    @Override
    public void delete(long cardId) {
        Card card = cardDao.findOne(cardId);
        cardDao.delete(card);
    }
}

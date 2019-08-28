package edu.sam.aveng.base.service.card.simple;

import edu.sam.aveng.base.contract.converter.ICollectionConverter;
import edu.sam.aveng.base.contract.dao.IGenericDao;
import edu.sam.aveng.base.converter.SampleConverter;
import edu.sam.aveng.base.model.domain.Card;
import edu.sam.aveng.base.model.domain.Pronunciation;
import edu.sam.aveng.base.model.domain.Sample;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.PronunciationDto;
import edu.sam.aveng.base.model.transfer.dto.SampleDto;
import edu.sam.aveng.base.model.transfer.dto.ShortCardDto;
import edu.sam.aveng.base.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@EnableTransactionManagement
@Transactional
public class SimpleCardServiceImpl implements ISimpleCardService {

    private IGenericDao<Card> cardDao;
    private IGenericDao<Sample> sampleDao;

    private ICollectionConverter<Sample, SampleDto> sampleConverter;

    @Autowired
    @Qualifier("genericHiberDao")
    public void setDaos(IGenericDao<Card> cardDao, IGenericDao<Sample> sampleDao) {
        cardDao.setClazz(Card.class);
        this.cardDao = cardDao;

        sampleDao.setClazz(Sample.class);
        this.sampleDao = sampleDao;
    }

    @Autowired
    public void setSampleConverter(ICollectionConverter<Sample, SampleDto> sampleConverter) {
        this.sampleConverter = sampleConverter;
    }

    @Override
    public void create(CardDto cardDto) {

        Card newCard = new Card();

        List<Sample> sampleList = cardDto.getSamples().stream()
                .filter(sampleDto -> !sampleDto.getContent().isEmpty())
                .flatMap(sampleDto -> {
                    Sample sample = sampleDao.findByProperty("content", sampleDto.getContent());
                    if(sample == null) {
                        sample = sampleConverter.convertToEntity(sampleDto);
                    }
                    return Stream.of(sample);
                })
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

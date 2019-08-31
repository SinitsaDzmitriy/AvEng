package edu.sam.aveng.base.service.card.simple;

import edu.sam.aveng.base.contract.converter.ICollectionConverter;
import edu.sam.aveng.base.contract.converter.IShortConverter;
import edu.sam.aveng.base.contract.dao.IGenericDao;
import edu.sam.aveng.base.model.domain.Card;
import edu.sam.aveng.base.model.domain.Sample;
import edu.sam.aveng.base.model.domain.enumeration.Lang;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.SampleDto;
import edu.sam.aveng.base.model.transfer.dto.ShortCardDto;
import edu.sam.aveng.base.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// ToDo: Bear checking of uq Sample constraint in SampleDao class (single-responsibility)

@Service
@EnableTransactionManagement
@Transactional
public class SimpleCardServiceImpl implements ISimpleCardService {

    private IGenericDao<Card> cardDao;
    private IGenericDao<Sample> sampleDao;

    private ICollectionConverter<Sample, SampleDto> sampleConverter;
    private IShortConverter<Card, CardDto, ShortCardDto> cardConverter;


    @Autowired
    @Qualifier("genericHiberDao")
    public void setDaos(IGenericDao<Card> cardDao, IGenericDao<Sample> sampleDao) {
        cardDao.setClazz(Card.class);
        this.cardDao = cardDao;

        sampleDao.setClazz(Sample.class);
        this.sampleDao = sampleDao;
    }

    @Autowired
    public void setConverters(IShortConverter<Card, CardDto, ShortCardDto> cardConverter,
                              ICollectionConverter<Sample, SampleDto> sampleConverter) {

        this.cardConverter = cardConverter;
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

        // ToDo: Bear the conversion in another place!

        newCard.setLang(cardDto.getLang());
        newCard.setContent(cardDto.getContent());
        newCard.setType(cardDto.getType());
        newCard.setPron(Converter.convertToEntity(cardDto.getPron()));
        newCard.setDefinition(cardDto.getDefinition());
        newCard.addSamples(sampleList);

        cardDao.create(newCard);
    }

    @Override
    public List<ShortCardDto> findAll() {
        return  cardConverter.convertToShortDto(cardDao.findAll())
                .collect(Collectors.toList());
    }

    @Override
    public CardDto findOne(long id) {
        return cardConverter.convertToDto(cardDao.findOne(id));
    }

    @Override
    public void update(long cardId, CardDto updatedCardDto) {

        Card updatedCard = cardConverter.convertToEntity(updatedCardDto);

        Set<Sample> preparedSamples = updatedCard.getSamples().stream()
                .filter(sample -> !sample.getContent().isEmpty())
                .map(sample -> {
                    Sample sampleFromDatabase = sampleDao.findByProperty("content", sample.getContent());
                    if(sampleFromDatabase != null) {
                        sample = sampleFromDatabase;
                    }
                    return sample;
                })
                .collect(Collectors.toSet());



        updatedCard.setId(cardId);
        updatedCard.setSamples(preparedSamples);

        cardDao.update(updatedCard);
    }

    @Override
    public void delete(long cardId) {
        Card card = cardDao.findOne(cardId);
        cardDao.delete(card);
    }

    @Override
    public List<Map> search(Lang usedLang, Lang desiredLang, String userInput) {
        return cardDao.search(usedLang, desiredLang, userInput);
    }

}

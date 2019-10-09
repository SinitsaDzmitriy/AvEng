package edu.sam.aveng.base.service.card;

import edu.sam.aveng.base.dao.sample.ISampleDao;
import edu.sam.aveng.base.model.dto.PronunciationDto;
import edu.sam.aveng.base.model.entity.Card;
import edu.sam.aveng.base.model.entity.CardMapping;
import edu.sam.aveng.base.model.entity.Pronunciation;
import edu.sam.aveng.base.model.entity.Sample;
import edu.sam.aveng.base.model.entity.UserCard;
import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.dto.CardDto;
import edu.sam.aveng.base.model.transfer.CardTableItem;
import edu.sam.aveng.base.converter.CardConverter;
import edu.sam.aveng.base.model.dto.SampleDto;
import edu.sam.aveng.base.dao.card.ICardDao;

import edu.sam.aveng.base.contract.v1.dao.IGenericDao;
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

@Service
@Transactional
@EnableTransactionManagement
public class CardService implements ICardService {
    private ICardDao cardDao;
    private ISampleDao sampleDao;

    private IGenericDao<Pronunciation> pronDao;
    private IGenericDao<UserCard> userCardDao;
    private IGenericDao<CardMapping> cardMappingDao;

    private CardConverter cardConverter;

    @Autowired
    public void setCardDao(ICardDao cardDao) {
        this.cardDao = cardDao;
    }

    @Autowired
    public void setSampleDao(ISampleDao sampleDao) {
        this.sampleDao = sampleDao;
    }

    @Autowired
    @Qualifier("genericHiberDao")
    public void setPronDao(IGenericDao<Pronunciation> pronDao) {
        pronDao.setClazz(Pronunciation.class);
        this.pronDao = pronDao;
    }

    @Autowired
    @Qualifier("genericHiberDao")
    public void setUserCardDao(IGenericDao<UserCard> userCardDao) {
        userCardDao.setClazz(UserCard.class);
        this.userCardDao = userCardDao;
    }

    @Autowired
    @Qualifier("genericHiberDao")
    public void setCardMappingDao(IGenericDao<CardMapping> cardMappingDao) {
        cardMappingDao.setClazz(CardMapping.class);
        this.cardMappingDao = cardMappingDao;
    }

    @Autowired
    public void setCardConverter(CardConverter cardConverter) {
        this.cardConverter = cardConverter;
    }

    @Override
    public void create(CardDto cardDto) {
        Card card = formCardWithAssociations(cardDto);
        cardDao.persist(card);
    }

    @Override
    public CardDto findOne(final long id) {
        CardDto cardDto;
        Card card = cardDao.findEagerly(id);

        if(card == null) {
            cardDto = null;
        } else {
            cardDto = cardConverter.convertToDto(card);
        }

        return cardDto;
    }

    @Override
    public List<CardTableItem> findAllAsTableItems(int maxNumberOfResults, int firstResultPosition) {
        return cardDao.findAllAsTableItems(maxNumberOfResults, firstResultPosition);
    }

    @Override
    public void update(Long id, CardDto cardDto) {
        Card card = formCardWithAssociations(cardDto);
        card.setId(id);
        cardDao.update(card);
    }

    @Override
    public void delete(long id) {
        // Delete associated UserCards (fk constraint)
        userCardDao.deleteByProperty("card.id", id);

        // Delete associated CardMappings (fk constraint)
        cardMappingDao.deleteByProperty("sourceCard.id", id);
        cardMappingDao.deleteByProperty("destCard.id", id);

        cardDao.delete(id);
    }

    @Override
    public List<Map> search(Lang usedLang, Lang desiredLang, String userInput) {
        return cardDao.search(usedLang, desiredLang, userInput);
    }

    @Override
    public long countAll() {
        return cardDao.countAll();
    }

    private Card formCardWithAssociations(CardDto cardDto) {
        Pronunciation preparedPron = obtainPron(cardDto.getPron());
        Set<Sample> preparedSamples = prepareSamples(cardDto.getSamples());

        Card card = cardConverter.convertToEntity(cardDto);
        card.setPron(preparedPron);
        card.setSamples(preparedSamples);

        return card;
    }

    private Set<Sample> prepareSamples(List<SampleDto> sampleDtos) {
         Set<Sample> preparedSamples = null;

         if(sampleDtos != null && !sampleDtos.isEmpty()) {
             Stream<Sample> sampleStream = sampleDtos.stream()
                     .filter(sampleDto -> sampleDto != null && !sampleDto.getContent().isEmpty())
                     .map(sampleDto -> new Sample(sampleDto.getContent()));
             preparedSamples = sampleDao.obtain(sampleStream).collect(Collectors.toSet());
         }

        return preparedSamples;
    }

    private Pronunciation obtainPron(PronunciationDto pronDto) {
        Pronunciation pron = null;

        if(pronDto != null) {
            pron = pronDao.findByProperty("transcription", pronDto.getTranscription());
            if(pron == null) {
                pron = new Pronunciation(pronDto.getTranscription());
                pronDao.create(pron);
            }
        }

        return pron;
    }
}

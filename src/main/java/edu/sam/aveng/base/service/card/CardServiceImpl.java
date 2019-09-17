package edu.sam.aveng.base.service.card;

import edu.sam.aveng.legacy.contract.dao.IGenericDao;
import edu.sam.aveng.base.model.domain.Card;
import edu.sam.aveng.base.model.domain.Sample;
import edu.sam.aveng.base.model.domain.enumeration.Lang;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.CardTableItem;
import edu.sam.aveng.base.converter.CardConverter;
import edu.sam.aveng.base.model.transfer.dto.SampleDto;
import edu.sam.aveng.base.dao.card.ICardDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
@Transactional
public class CardServiceImpl implements ICardService {

    private CardConverter cardConverter;

    private ICardDao cardDao;
    private IGenericDao<Sample> sampleDao;

    @Autowired
    public void setCardDao(ICardDao cardDao) {
        this.cardDao = cardDao;
    }

    @Autowired
    @Qualifier("genericHiberDao")
    public void setSampleDao(IGenericDao<Sample> sampleDao) {
        sampleDao.setClazz(Sample.class);
        this.sampleDao = sampleDao;
    }

    @Autowired
    public void setCardConverter(CardConverter cardConverter) {
        this.cardConverter = cardConverter;
    }

    @Override
    public void create(CardDto cardDto) {

        Card card = cardConverter.convertToEntity(cardDto);
        Set<Sample> preparedSamples = prepareSamples(cardDto.getSamples());
        card.setSamples(preparedSamples);
        cardDao.create(card);
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
    public List<CardTableItem> findAllAsTableItems() {
        return  cardDao.findAllAsTableItems();
    }

    @Override
    public void update(Long id, CardDto cardDto) {

        Set<Sample> preparedSamples = prepareSamples(cardDto.getSamples());

        Card card = cardConverter.convertToEntity(cardDto);

        card.setId(id);
        card.setSamples(preparedSamples);

        cardDao.update(card);
    }

    @Override
    public void delete(long id) {
        cardDao.delete(id);
    }

    @Override
    public List<Map> search(Lang usedLang, Lang desiredLang, String userInput) {
        return cardDao.search(usedLang, desiredLang, userInput);
    }

    private Set<Sample> prepareSamples(List<SampleDto> sampleDtos) {
        return sampleDtos.stream()
                .filter(sampleDto -> !sampleDto.getContent().isEmpty())
                .map(sampleDto -> {
                    Sample sample = sampleDao.findByProperty("content", sampleDto.getContent());
                    if(sample == null) {
                        sample = new Sample();
                        sample.setContent(sampleDto.getContent());
                    }
                    return sample;
                })
                .collect(Collectors.toSet());
    }

}

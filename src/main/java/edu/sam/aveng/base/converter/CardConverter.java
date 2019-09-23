package edu.sam.aveng.base.converter;

import edu.sam.aveng.legacy.contract.converter.IBaseConverter;
import edu.sam.aveng.legacy.contract.converter.ICollectionConverter;
import edu.sam.aveng.base.model.entity.Card;
import edu.sam.aveng.base.model.entity.Pronunciation;
import edu.sam.aveng.base.model.entity.Sample;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.PronunciationDto;
import edu.sam.aveng.base.model.transfer.dto.CardTableItem;
import edu.sam.aveng.base.model.transfer.dto.SampleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CardConverter{

    private IBaseConverter<Pronunciation, PronunciationDto> pronConverter;
    private ICollectionConverter<Sample, SampleDto> sampleConverter;

    @Autowired
    @Qualifier("pronConverter")
    public void setPronConverter(IBaseConverter<Pronunciation,
            PronunciationDto> pronConverter) {
        this.pronConverter = pronConverter;
    }

    @Autowired
    @Qualifier("oldSampleConverter")
    public void setSampleConverter(ICollectionConverter<Sample,
            SampleDto> sampleConverter) {
        this.sampleConverter = sampleConverter;
    }

    public CardDto convertToDto(Card card) {

        CardDto cardDto = new CardDto();

        List<SampleDto> sampleDtos = sampleConverter
                .convertToDto(card.getSamples())
                .collect(Collectors.toList());

        cardDto.setId(card.getId());
        cardDto.setLang(card.getLang());
        cardDto.setContent(card.getContent());
        cardDto.setType(card.getType());
        cardDto.setPron(pronConverter.convertToDto(card.getPron()));
        cardDto.setDefinition(card.getDefinition());
        cardDto.setSamples(sampleDtos);

        return cardDto;
    }

    public Card convertToEntity(CardDto cardDto) {

        Card card = new Card();

        card.setLang(cardDto.getLang());
        card.setContent(cardDto.getContent());
        card.setType(cardDto.getType());
        card.setPron(pronConverter.convertToEntity(cardDto.getPron()));
        card.setDefinition(cardDto.getDefinition());

        return card;
    }

    public Stream<CardTableItem> convertToShortDto(Collection<Card> cards) {
        return cards.stream()
                .map(card -> {
                    CardTableItem cardTableItem = new CardTableItem();

                    cardTableItem.setId(card.getId());
                    cardTableItem.setLang(card.getLang());
                    cardTableItem.setContent(card.getContent());
                    cardTableItem.setType(card.getType());
                    cardTableItem.setDefinition(card.getDefinition());

                    return cardTableItem;
                });
    }
}

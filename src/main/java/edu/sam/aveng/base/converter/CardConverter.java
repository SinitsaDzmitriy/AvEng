package edu.sam.aveng.base.converter;

import edu.sam.aveng.base.model.dto.PronunciationDto;
import edu.sam.aveng.base.model.dto.SampleDto;
import edu.sam.aveng.base.model.entity.Card;
import edu.sam.aveng.base.model.dto.CardDto;
import edu.sam.aveng.base.model.transfer.CardTableItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CardConverter{
    private SampleConverter sampleConverter;

    @Autowired
    public void setSampleConverter(SampleConverter sampleConverter) {
        this.sampleConverter = sampleConverter;
    }

    public CardDto convertToDto(Card card) {
        CardDto cardDto = null;

        if(card != null){
            List<SampleDto> sampleDtos = sampleConverter
                    .convertToDto(card.getSamples())
                    .collect(Collectors.toList());
            PronunciationDto pronDto = new PronunciationDto();
            pronDto.setTranscription(card.getPron().getTranscription());

            cardDto = new CardDto();
            cardDto.setId(card.getId());
            cardDto.setLang(card.getLang());
            cardDto.setContent(card.getContent());
            cardDto.setType(card.getType());
            cardDto.setPron(pronDto);
            cardDto.setDefinition(card.getDefinition());
            cardDto.setSamples(sampleDtos);
        }

        return cardDto;
    }

    public Card convertToEntity(CardDto cardDto) {
        Card card = new Card();

        if(cardDto != null) {
            card.setLang(cardDto.getLang());
            card.setContent(cardDto.getContent());
            card.setType(cardDto.getType());
            card.setDefinition(cardDto.getDefinition());
        }

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

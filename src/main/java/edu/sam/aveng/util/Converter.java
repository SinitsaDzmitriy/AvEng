package edu.sam.aveng.util;

import edu.sam.aveng.domain.Card;
import edu.sam.aveng.domain.Pronunciation;
import edu.sam.aveng.domain.Sample;
import edu.sam.aveng.domain.User;
import edu.sam.aveng.dto.CardDto;
import edu.sam.aveng.dto.PronunciationDto;
import edu.sam.aveng.dto.SampleDto;
import edu.sam.aveng.dto.ShortCardDto;
import edu.sam.aveng.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static PronunciationDto convertToDto(Pronunciation pron) {
        PronunciationDto pronDto = new PronunciationDto();
        pronDto.setTranscription(pron.getTranscription());
        return pronDto;
    }

    public static Pronunciation convertToEntity(PronunciationDto pronDto) {
        Pronunciation pron = new Pronunciation();
        pron.setTranscription(pronDto.getTranscription());
        return pron;
    }

    public static Sample convertToEntity(SampleDto sampleDto) {
        Sample sample = new Sample();
        sample.setContent(sampleDto.getContent());
        return sample;
    }

    public static SampleDto convertToDto(Sample sample) {
        SampleDto sampleDto = new SampleDto();
        sampleDto.setContent(sample.getContent());
        return sampleDto;
    }

    public static CardDto convertToDto(Card card) {

        List<SampleDto> sampleDtoList = card.getSamples().stream()
                .map(Converter::convertToDto)
                .collect(Collectors.toList());

        CardDto dto = new CardDto();

        dto.setId(card.getId());
        dto.setContent(card.getContent());
        dto.setType(card.getType());
        dto.setPron(Converter.convertToDto(card.getPron()));
        dto.setDefinition(card.getDefinition());
        dto.setSamples(sampleDtoList);

        return dto;
    }

    public static User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getEmail());
        return user;
    }

    public static Card convertToEntity(CardDto cardDto) {
        Card card = new Card();

        List<Sample> sampleList = cardDto.getSamples().stream()
                .map(Converter::convertToEntity)
                .collect(Collectors.toList());

        card.setId(cardDto.getId());
        card.setContent(cardDto.getContent());
        card.setType(cardDto.getType());
        card.setPron(Converter.convertToEntity(cardDto.getPron()));
        card.setDefinition(cardDto.getDefinition());
        card.addSamples(sampleList);

        return card;
    }

    public static ShortCardDto convertToShortDto(Card card) {
        ShortCardDto dto = new ShortCardDto();
        dto.setId(card.getId());
        dto.setContent(card.getContent());
        dto.setType(card.getType());
        dto.setDefinition(card.getDefinition());

        return dto;
    }
}

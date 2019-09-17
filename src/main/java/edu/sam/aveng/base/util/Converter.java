package edu.sam.aveng.base.util;

import edu.sam.aveng.base.model.domain.Card;
import edu.sam.aveng.base.model.domain.Pronunciation;
import edu.sam.aveng.base.model.domain.Sample;
import edu.sam.aveng.base.model.domain.User;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.PronunciationDto;
import edu.sam.aveng.base.model.transfer.dto.SampleDto;
import edu.sam.aveng.base.model.transfer.dto.ShortCardDto;
import edu.sam.aveng.base.model.transfer.UserCredentials;
import edu.sam.aveng.base.model.transfer.user.AbstractUserCredentials;
import edu.sam.aveng.base.model.transfer.user.UserRegCredentials;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        sampleDto.setId(sample.getId());
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

    public static User convertToEntity(UserCredentials userCredentials) {
        User user = new User();
        user.setEmail(userCredentials.getEmail());
        user.setPassword(new BCryptPasswordEncoder()
                .encode(userCredentials.getPassword()));
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


    public static User convertToEntity(AbstractUserCredentials credentials) {
        User user = new User();

        user.setEmail(credentials.getEmail());
        user.setPassword(credentials.getPassword());

        return user;
    }

    public static edu.sam.aveng.base.model.transfer.user.UserCredentials simplify(UserRegCredentials regCredentials) {
        edu.sam.aveng.base.model.transfer.user.UserCredentials credentials = new edu.sam.aveng.base.model.transfer.user.UserCredentials();

        credentials.setEmail(regCredentials.getEmail());
        credentials.setPassword(regCredentials.getPassword());

        return credentials;
    }
}

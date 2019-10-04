package edu.sam.aveng.base.converter;

import edu.sam.aveng.base.contract.v1.converter.IBaseConverter;
import edu.sam.aveng.base.model.entity.Pronunciation;
import edu.sam.aveng.base.model.transfer.dto.PronunciationDto;
import org.springframework.stereotype.Component;

@Component
public class PronConverter
        implements IBaseConverter<Pronunciation, PronunciationDto> {

    @Override
    public PronunciationDto convertToDto(Pronunciation pron) {
        PronunciationDto pronDto = new PronunciationDto();
        pronDto.setTranscription(pron.getTranscription());
        return pronDto;
    }

    @Override
    public Pronunciation convertToEntity(PronunciationDto pronDto) {
        Pronunciation pron = new Pronunciation();
        pron.setTranscription(pronDto.getTranscription());
        return pron;
    }
}

package edu.sam.aveng.dto;

public class PronunciationDto {
    private String transcription;

    // ToDo: One more field for audio-file

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getTranscription() {
        return transcription;
    }
}

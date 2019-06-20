package edu.sam.spittr.dto;

import edu.sam.spittr.domain.Spittle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalTime;

public class SpittleDTO {
    // ToDo: discuss if this field id needed
    private long id;

    @NotBlank(message="{validation.message.not_blank}")
    @Size(max=280, message="{validation.message.size}")
    private String message;

    // ToDo: read about this annotation
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)

    @PastOrPresent(message="{validation.time.past_or_present}")
    private LocalTime time;

    @Max(value = 180, message="{validation.latitude.max}")
    @Min(value = -180, message="{validation.latitude.min}")
    private double latitude;

    @Max(value = 90, message="{validation.longitude.max}")
    @Min(value = -90, message="{validation.longitude.min}")
    private double longitude;

    private SpittleDTO() { }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Spittle toSpittle() {
        Spittle spittle = new Spittle();
        spittle.setId(id);
        spittle.setMessage(message);
        spittle.setTime(time);
        spittle.setLatitude(latitude);
        spittle.setLongitude(longitude);
        return spittle;
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "id", "time");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id", "time");
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("{id=")
                .append(id)
                .append(" message=\"")
                .append(message)
                .append("\" time=\"")
                .append(time)
                .append("\" latitude=")
                .append(latitude)
                .append(" longitude=")
                .append(longitude)
                .append("}")
                .toString();
    }

    public static class Builder {
        // default value
        private long id = 0;
        private String message;
        private LocalTime time;
        private double latitude;
        private double longitude;

        public Builder() {
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setTime(LocalTime time) {
            this.time = time;
            return this;
        }

        public Builder setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public SpittleDTO build() {
            SpittleDTO dto = new SpittleDTO();
            dto.id = id;
            dto.message = message;
            dto.time = time;
            dto.latitude = latitude;
            dto.longitude = longitude;
            return  dto;
        }

        public SpittleDTO build(Spittle spittle) {
            SpittleDTO dto = new SpittleDTO();
            dto.id = spittle.getId();
            dto.message = spittle.getMessage();
            dto.time = spittle.getTime();
            dto.latitude = spittle.getLatitude();
            dto.longitude = spittle.getLongitude();
            return  dto;
        }
    }
}
package edu.sam.spittr.dto;

import edu.sam.spittr.domain.Spittle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalTime;

public class SpittleDTO {
    private long id;
    private String message;
    private LocalTime time;
    private Double latitude;
    private Double longitude;

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
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

    public static class Builder {
        // default value
        private long id = 0;
        private String message;
        private LocalTime time;
        private Double latitude;
        private Double longitude;

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
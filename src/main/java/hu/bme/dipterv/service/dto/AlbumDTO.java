package hu.bme.dipterv.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Album entity.
 */
public class AlbumDTO implements Serializable {

    private Long id;

    private String name;

    private LocalDate year;

    private Long bandId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public Long getBandId() {
        return bandId;
    }

    public void setBandId(Long bandId) {
        this.bandId = bandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlbumDTO albumDTO = (AlbumDTO) o;
        if (albumDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), albumDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlbumDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", year='" + getYear() + "'" +
            ", band=" + getBandId() +
            "}";
    }
}

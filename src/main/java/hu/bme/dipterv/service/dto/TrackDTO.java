package hu.bme.dipterv.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Track entity.
 */
public class TrackDTO implements Serializable {

    private Long id;

    private String title;

    private LocalDate year;

    private Long bandId;

    private Set<MusicianDTO> performers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Set<MusicianDTO> getPerformers() {
        return performers;
    }

    public void setPerformers(Set<MusicianDTO> musicians) {
        this.performers = musicians;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrackDTO trackDTO = (TrackDTO) o;
        if (trackDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trackDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrackDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", year='" + getYear() + "'" +
            ", band=" + getBandId() +
            "}";
    }
}

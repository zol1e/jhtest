package hu.bme.dipterv.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Musician entity.
 */
public class MusicianDTO implements Serializable {

    private Long id;

    private String name;

    private LocalDate birthday;

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MusicianDTO musicianDTO = (MusicianDTO) o;
        if (musicianDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), musicianDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MusicianDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", birthday='" + getBirthday() + "'" +
            "}";
    }
}

package hu.bme.dipterv.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Band entity.
 */
public class BandDTO implements Serializable {

    private Long id;

    private String name;

    private LocalDate founded;

    private Set<MusicianDTO> members = new HashSet<>();

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

    public LocalDate getFounded() {
        return founded;
    }

    public void setFounded(LocalDate founded) {
        this.founded = founded;
    }

    public Set<MusicianDTO> getMembers() {
        return members;
    }

    public void setMembers(Set<MusicianDTO> musicians) {
        this.members = musicians;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BandDTO bandDTO = (BandDTO) o;
        if (bandDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bandDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BandDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", founded='" + getFounded() + "'" +
            "}";
    }
}

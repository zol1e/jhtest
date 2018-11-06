package hu.bme.dipterv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Musician.
 */
@Entity
@Table(name = "musician")
public class Musician implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private LocalDate birthday;

    @ManyToMany(mappedBy = "performers")
    @JsonIgnore
    private Set<Track> tracks = new HashSet<>();

    @ManyToMany(mappedBy = "members")
    @JsonIgnore
    private Set<Band> bands = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Musician name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Musician birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public Musician tracks(Set<Track> tracks) {
        this.tracks = tracks;
        return this;
    }

    public Musician addTracks(Track track) {
        this.tracks.add(track);
        track.getPerformers().add(this);
        return this;
    }

    public Musician removeTracks(Track track) {
        this.tracks.remove(track);
        track.getPerformers().remove(this);
        return this;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    public Set<Band> getBands() {
        return bands;
    }

    public Musician bands(Set<Band> bands) {
        this.bands = bands;
        return this;
    }

    public Musician addBands(Band band) {
        this.bands.add(band);
        band.getMembers().add(this);
        return this;
    }

    public Musician removeBands(Band band) {
        this.bands.remove(band);
        band.getMembers().remove(this);
        return this;
    }

    public void setBands(Set<Band> bands) {
        this.bands = bands;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Musician musician = (Musician) o;
        if (musician.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), musician.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Musician{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", birthday='" + getBirthday() + "'" +
            "}";
    }
}

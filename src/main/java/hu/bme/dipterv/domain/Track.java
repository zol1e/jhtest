package hu.bme.dipterv.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Track.
 */
@Entity
@Table(name = "track")
public class Track implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "jhi_year")
    private LocalDate year;

    @ManyToOne
    @JsonIgnoreProperties("tracks")
    private Band band;

    @ManyToMany
    @JoinTable(name = "track_performers",
               joinColumns = @JoinColumn(name = "tracks_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "performers_id", referencedColumnName = "id"))
    private Set<Musician> performers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Track title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getYear() {
        return year;
    }

    public Track year(LocalDate year) {
        this.year = year;
        return this;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public Band getBand() {
        return band;
    }

    public Track band(Band band) {
        this.band = band;
        return this;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Set<Musician> getPerformers() {
        return performers;
    }

    public Track performers(Set<Musician> musicians) {
        this.performers = musicians;
        return this;
    }

    public Track addPerformers(Musician musician) {
        this.performers.add(musician);
        musician.getTracks().add(this);
        return this;
    }

    public Track removePerformers(Musician musician) {
        this.performers.remove(musician);
        musician.getTracks().remove(this);
        return this;
    }

    public void setPerformers(Set<Musician> musicians) {
        this.performers = musicians;
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
        Track track = (Track) o;
        if (track.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), track.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Track{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", year='" + getYear() + "'" +
            "}";
    }
}

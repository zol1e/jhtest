package hu.bme.dipterv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Band.
 */
@Entity
@Table(name = "band")
public class Band implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "founded")
    private LocalDate founded;

    @ManyToMany
    @JoinTable(name = "band_members",
               joinColumns = @JoinColumn(name = "bands_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "members_id", referencedColumnName = "id"))
    private Set<Musician> members = new HashSet<>();

    @OneToMany(mappedBy = "band")
    private Set<Album> albums = new HashSet<>();

    @OneToMany(mappedBy = "band")
    private Set<Track> tracks = new HashSet<>();

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

    public Band name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFounded() {
        return founded;
    }

    public Band founded(LocalDate founded) {
        this.founded = founded;
        return this;
    }

    public void setFounded(LocalDate founded) {
        this.founded = founded;
    }

    public Set<Musician> getMembers() {
        return members;
    }

    public Band members(Set<Musician> musicians) {
        this.members = musicians;
        return this;
    }

    public Band addMembers(Musician musician) {
        this.members.add(musician);
        musician.getBands().add(this);
        return this;
    }

    public Band removeMembers(Musician musician) {
        this.members.remove(musician);
        musician.getBands().remove(this);
        return this;
    }

    public void setMembers(Set<Musician> musicians) {
        this.members = musicians;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public Band albums(Set<Album> albums) {
        this.albums = albums;
        return this;
    }

    public Band addAlbums(Album album) {
        this.albums.add(album);
        album.setBand(this);
        return this;
    }

    public Band removeAlbums(Album album) {
        this.albums.remove(album);
        album.setBand(null);
        return this;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public Band tracks(Set<Track> tracks) {
        this.tracks = tracks;
        return this;
    }

    public Band addTracks(Track track) {
        this.tracks.add(track);
        track.setBand(this);
        return this;
    }

    public Band removeTracks(Track track) {
        this.tracks.remove(track);
        track.setBand(null);
        return this;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
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
        Band band = (Band) o;
        if (band.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), band.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Band{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", founded='" + getFounded() + "'" +
            "}";
    }
}

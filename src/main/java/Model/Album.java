package Model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@SequenceGenerator(name = "seqPK", sequenceName = "seqPK")
@Entity
@Table(name = "Album")
public class Album implements Serializable {

    @Id
    @GeneratedValue(generator = "seqPK")
    @Column(name = "id")
    private int id;

    @Column(name = "genre")
    private String genre;

    @Column(name = "album_name",unique = true)
    private String albumName;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Author author;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "album")
    private List<Composition> compositions = new LinkedList<>();

    public Album(String genre, String albumName, Author author) {
        this.genre = genre;
        this.albumName = albumName;
        this.author = author;
    }

    public List<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<Composition> compositions) {
        this.compositions = compositions;
    }

    public Album() {
    }

    public Album(String genre, String albumName) {
        this.genre = genre;
        this.albumName = albumName;
    }

    public int getAuthorId()
    {
        return author.getId();
    }
    public String getAuthorName()
    {
        return author.getName();
    }

    public int getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbumName() {
        return albumName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

package Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@SequenceGenerator(name = "seqPK", sequenceName = "seqPK")
@Entity
@Table(name = "Author")
public class Author implements Serializable {

    @Id
    @GeneratedValue(generator = "seqPK")
    @Column(name = "id")
    private int id;

    @Column(name = "author_name",unique = true)
    private String name;

    public int getId()
    {
        return id;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "author")
    private List<Album> albums = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Author()
    {}

    public Author(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}

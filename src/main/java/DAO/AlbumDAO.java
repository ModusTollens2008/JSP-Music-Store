package DAO;


import Model.Album;
import Model.Author;
import com.example.MusicStore.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AlbumDAO {

    public  void createAlbum(Album album) throws SQLException
    {
        try(Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            session.save(album);
            tx.commit();
        }
    }
    public  Album selectAlbumById(int id)
    {
        try (Session session = HibernateUtil.configureSession()) {
            Album al = session.get(Album.class, id);
            return al;
        }
    }
    public List<Album> getAlbums()
    {
        try (Session session = HibernateUtil.configureSession()) {
            Query query = session.createQuery("from Album ");
            List albums = query.list();
            for (Iterator it = albums.iterator(); it.hasNext(); ) {
                Album mcRead = (Album) it.next();
                System.out.println(mcRead.getAlbumName() + " " + mcRead.getId());
            }
            return albums;
        }
    }

    public void deleteAlbumbyId(int id)
    {
        try (Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("delete from Album " +
                    "where id = :authorId");

            query.setParameter("authorId", id);
            query.executeUpdate();
            tx.commit();
        }
    }

    public List<Album> getAlbumsByAuthor(String authorName)
    {
        try (Session session = HibernateUtil.configureSession()) {
            Query query = session.createQuery("from Album where author_id in (SELECT id from Author where author_name=:name) ");
            query.setParameter("name",authorName);
            List albums = query.list();
            for (Iterator it = albums.iterator(); it.hasNext(); ) {
                Album mcRead = (Album) it.next();
                System.out.println(mcRead.getAlbumName() + " " + mcRead.getId());
            }
            return albums;
        }
    }

    public List<Album> getAlbumsLike(String name)
    {
        try (Session session = HibernateUtil.configureSession()) {
            Query query = session.createQuery("from Album where album_name like :name");
            query.setParameter("name","%"+name+"%");
            List authors = query.list();
            for (Iterator it = authors.iterator(); it.hasNext(); ) {
                Album mcRead = (Album) it.next();
            }
            return authors;
        }
    }

    public Album getAlbumByName(String name)
    {
        try(Session session = HibernateUtil.configureSession())
        {
            Query query = session.createQuery("from Album where albumName =:name ")
                    .setParameter("name", name);
            return (Album) query.uniqueResult();
        }
    }

    public void updateAlbum(int albumId,int authorId,String newName,String newGenre) throws SQLException
    {
        try (Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Album set album_name =:newName" +
                    ", genre =:newGenre"+
                    ",author_id =:authorId"+
                    " where id =:userId");
            query.setParameter("newName", newName);
            query.setParameter("newGenre", newGenre);
            query.setParameter("authorId", authorId);
            query.setParameter("userId", albumId);
            query.executeUpdate();
            tx.commit();
        }

    }

}

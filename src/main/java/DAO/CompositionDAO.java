package DAO;


import Model.Author;
import Model.Composition;
import com.example.MusicStore.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class CompositionDAO {

    public  void createComposition(Composition composition) throws SQLException
    {
        try(Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            session.save(composition);
            tx.commit();
        }
    }
    public  Composition selectCompositionById(int id)
    {
        try (Session session = HibernateUtil.configureSession()) {
            Composition au = session.get(Composition.class, id);
            return au;
        }
    }
    public List<Composition> getCompositions()
    {
        try (Session session = HibernateUtil.configureSession()) {
            Query query = session.createQuery("from Composition");
            List authors = query.list();
            for (Iterator it = authors.iterator(); it.hasNext(); ) {
                Composition mcRead = (Composition) it.next();

            }
            return authors;
        }
    }

    public void deleteCompositionbyId(int id)
    {
        try (Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("delete from Composition " +
                    "where id = :authorId");

            query.setParameter("authorId", id);
            query.executeUpdate();
            tx.commit();
        }
    }

    public void updateComposition(int albumId,String newName,int duration,int id) throws SQLException
    {

        try (Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Composition set composition_name =:newName" +
                    ",duration =:duration"+
                    ",album_id =:albumId"+
                    " where id =:Id");
            query.setParameter("newName", newName);
            query.setParameter("duration", duration);
            query.setParameter("albumId", albumId);
            query.setParameter("Id", id);
            query.executeUpdate();
            tx.commit();
        }

    }
}

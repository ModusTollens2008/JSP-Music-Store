package DAO;


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

public class AuthorDAO {

    public  void createAuthor(Author author) throws SQLException
    {
        try(Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            session.save(author);
            tx.commit();
        }
    }
    public  Author selectAuthorById(int id)
    {
        try (Session session = HibernateUtil.configureSession()) {
            Author au = session.get(Author.class, id);
            return au;
        }
    }
    public Author getAuthorByName(String authorName) {
        try (Session session = HibernateUtil.configureSession()) {
            Query query = session.createQuery("from Author where name =:paramName");
            query.setParameter("paramName", authorName);
            return (Author) query.uniqueResult();
        }

    }
    public List<Author> getAuthors()
    {
        try (Session session = HibernateUtil.configureSession()) {
            Query query = session.createQuery("from Author");
            List authors = query.list();
            for (Iterator it = authors.iterator(); it.hasNext(); ) {
                Author mcRead = (Author) it.next();
                System.out.println(mcRead.getName() + " " + mcRead.getId());
            }
            return authors;
        }
    }
    public List<Author> getAuthorsLike(String name)
    {
        try (Session session = HibernateUtil.configureSession()) {
            Query query = session.createQuery("from Author where name like :name");
            query.setParameter("name","%"+name+"%");
            List authors = query.list();
            for (Iterator it = authors.iterator(); it.hasNext(); ) {
                Author mcRead = (Author) it.next();
                System.out.println(mcRead.getName() + " " + mcRead.getId());
            }
            return authors;
        }
    }

    public void deleteAuthorbyId(int id)
    {
        try (Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("delete from Author " +
                    "where id = :authorId");

            query.setParameter("authorId", id);
            query.executeUpdate();
            tx.commit();
        }
    }

    public void updateAuthor(int authorId,String newName) throws SQLException
    {

        try (Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Author set author_name =:newName" +
                    " where id =: userId");
            query.setParameter("newName", newName);
            query.setParameter("userId", authorId);
            query.executeUpdate();
            tx.commit();
        }

    }

}

package com.wojcik.demo.dao;

import com.wojcik.demo.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void save(Book book) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();

        session.beginTransaction();

        session.saveOrUpdate(book);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void remove(int bookId) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();

        session.beginTransaction();

        Query query = session.createQuery("delete from Book where id = " + bookId);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public Book getById(int id) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Book book = session.get(Book.class, id);
        return book;
    }

    @Override
    public List<Book> getBooks() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<Book> books = session.createQuery("from Book").getResultList();
        session.close();

        return books;
    }

    @Override
    public List<Book> getBestsellers() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<Book> books = session.createQuery("from Book b where b.id in (select book.id from PurchaseDetails group by book.id order by sum(quantity) desc)").setMaxResults(10).getResultList();
        session.close();

        return books;
    }
}

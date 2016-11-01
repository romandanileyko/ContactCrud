package ru.danileyko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.danileyko.model.Hello;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by danil on 28.10.2016.
 */
@Repository
public class HelloDAOImpl implements HelloDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addContact(Hello hello) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(hello);
        transaction.commit();
        session.close();
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Hello> list() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Hello> list = session.createQuery("from Hello").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public void removeContact(int id) {
        Session session = this.sessionFactory.openSession();
        Hello contact = (Hello) session.load(Hello.class, new Integer(id));
        Transaction transaction = session.beginTransaction();
        if(contact != null)
        {
            session.delete(contact);
        }
        transaction.commit();
        session.close();
    }

//Обновление объекта , полученного в методе getContactById(int id).
    @Override
    @Transactional
    public void updateContact(Hello hello) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(hello);
        transaction.commit();
        session.close();
    }

//Получение объекта по его id.
    @Override
    @Transactional
    public Hello getContactById(int id) {
        Session session = this.sessionFactory.openSession();

        String hql = "from Hello where id=" + id;
        Transaction transaction = session.beginTransaction();
        Query query = this.sessionFactory.openSession().createQuery(hql);
        transaction.commit();
        session.close();
        List<Hello> listContact = (List<Hello>) query.list();
        if(listContact !=null && !listContact.isEmpty())
        {
            return listContact.get(0);
        }
        return null;

    }
}

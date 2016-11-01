package ru.danileyko.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.danileyko.dao.HelloDAO;
import ru.danileyko.model.Hello;

import java.util.List;

/**
 * Created by danil on 28.10.2016.
 */
@Service
public class HelloServiceImpl implements HelloService {

    private HelloDAO helloDAO;

    public void setHelloDAO(HelloDAO helloDAO) {
        this.helloDAO = helloDAO;
    }

    @Override
    @Transactional
    public void addContact(Hello hello) {
        helloDAO.addContact(hello);
    }

    @Override
    @Transactional
    public List<Hello> list() {
        return helloDAO.list();
    }

    @Override
    @Transactional
    public void removeContact(int id) {
        helloDAO.removeContact(id);
    }

    @Override
    public void updateContact(Hello hello) {
        helloDAO.updateContact(hello);
    }

    @Override
    public Hello getContactById(int id) {
        return helloDAO.getContactById(id);
    }
}

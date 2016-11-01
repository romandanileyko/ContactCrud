package ru.danileyko.service;

import ru.danileyko.model.Hello;

import java.util.List;

/**
 * Created by danil on 28.10.2016.
 */
public interface HelloService {
    public void addContact(Hello hello);
    public void removeContact(int id);
    public void updateContact(Hello hello);
    public Hello getContactById(int id);
    public List<Hello> list();
}

package com.skypro.dao.impl;

import com.skypro.City;
import com.skypro.HibernateSessionFactoryUtil;
import com.skypro.dao.CityDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDAOImpl implements CityDAO {

    @Override
    public void create(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
    }

    @Override
    public City readById(int id) {
        City city;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            city = session.get(City.class, id);
        }
        return city;
    }

    @Override
    public List<City> readAll() {
        List<City> cities;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            cities = session.createQuery("from City", City.class).list();
        }
        return cities;
    }

    @Override
    public void update(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }
    }

    @Override
    public void delete(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }
}

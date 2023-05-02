package com.skypro.dao.impl;

import com.skypro.Employee;
import com.skypro.HibernateSessionFactoryUtil;
import com.skypro.dao.EmployeeDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void create(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    @Override
    public Employee readById(int id) {
        Employee employee;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            employee = session.get(Employee.class, id);
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employees;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            employees = session.createQuery("from Employee", Employee.class).list();
        }
        return employees;
    }
    @Override
    public void update(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }
    @Override
    public void delete(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }
}
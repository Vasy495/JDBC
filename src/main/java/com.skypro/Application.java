package com.skypro;

import com.skypro.dao.CityDAO;
import com.skypro.dao.impl.CityDAOImpl;
import com.skypro.dao.EmployeeDAO;
import com.skypro.dao.impl.EmployeeDAOImpl;

import java.util.List;

public class Application {


//    public static void main(String[] args) throws SQLException {
//        final String user = "postgres";
//        final String password = "1786232t";
//        final String url = "jdbc:postgresql://localhost:5432/skypro2";

    public static void main(String[] args) {

        CityDAO cityDAO = new CityDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        City city = new City("Los Angeles");
        cityDAO.create(city);

        Employee employee = new Employee("Vasy", "Vasya", "male", 30, city);
        employeeDAO.create(employee);


        System.out.println(cityDAO.readById(1));
        System.out.println(employeeDAO.readById(1));

        List<City> cities = cityDAO.readAll();
        for (City entry : cities) {
            System.out.println(entry);
        }

        List<Employee> employees = employeeDAO.readAll();
        for (Employee entry : employees) {
            System.out.println(entry);
        }

        city.setName("Piter");
        cityDAO.update(city);

        employee.setAge(32);
        employeeDAO.update(employee);


        employeeDAO.delete(employee);

        cityDAO.create(city);
        cityDAO.delete(city);
    }
}

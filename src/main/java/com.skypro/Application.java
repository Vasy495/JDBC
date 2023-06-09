package com.skypro;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "1786232t";
        final String url = "jdbc:postgresql://localhost:5432/skypro2";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT first_name, last_name, gender, city_name " +
                             "FROM employee INNER JOIN city " +
                             "ON employee.city_id = city.city_id AND id = (?)"
             )) {
            statement.setInt(1, 3);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String firstName = "First name: " + resultSet.getString("first_name");
                String lastName = "Last name: " + resultSet.getString("last_name");
                String gender = "Gender: " + resultSet.getString("gender");
                String city = "City: " + resultSet.getString("city_name");

                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(gender);
                System.out.println(city);
            }
        }

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            City city = new City(1, null);
            Employee employee = new Employee("Test", "Test", "male", 20, city);

            System.out.println("CREATE ENTRY");
            employeeDAO.create(employee);
            System.out.println(employeeDAO.readAll());

            System.out.println("UPDATE AGE");
            employeeDAO.updateAgeById(1, 45);
            System.out.println(employeeDAO.readById(3));

            System.out.println("UPDATE CITY");
            employeeDAO.updateCityById(1, new City(2, null));
            System.out.println(employeeDAO.readById(3));

            System.out.println("DELETE ENTRY");
            employeeDAO.deleteById(2);
            System.out.println(employeeDAO.readAll());
        }
    }
}

package org.example;

import org.courses.Courses;

import java.io.IOException;
import java.sql.*;
import java.util.Random;

/*
Создайте базу данных (например, SchoolDB).
В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
Настройте Hibernate для работы с вашей базой данных.
Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
Убедитесь, что каждая операция выполняется в отдельной транзакции.
 */
public class Main {
    private static final Random random = new Random();
    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "password";

        // подключение к базе данных
        try(Connection connection = DriverManager.getConnection(url,user,password)) {


            // создание базы данных
            createDatabase(connection);
            System.out.println("База данных создана успешно! ");

            // использование базы данных
            useDatabase(connection);
            System.out.println("Успещное использование базы данных! ");

            // создание таблицы
            createTable(connection);
            System.out.println("Таблица успешно создана! ");


            // добавление данных
            int count = random.nextInt(5,11);
            for (int i= 0; i < count; i ++)
                insertData(connection,Courses.create());
            System.out.println("Данные вставлены успешно");


            // закрытие соединения
//            connection.close();
//            System.out.println("Соединение с базой данных закрыто");
        }
        catch (SQLException e){
            e.printStackTrace();
        }



    }

    //region Вспомогательные методы
    private static void createDatabase(Connection connection) throws SQLException {
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS schoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(createDatabaseSQL)) {
            statement.execute();
        }
    }


        private static void useDatabase (Connection connection) throws SQLException {
            String useDatabaseSQL = "USE schoolDB;";
            try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
                statement.execute();
            }
        }

        private static void createTable(Connection connection) throws SQLException{
            String createTableSQL = "СОЗДАНИЕ ТАБЛИЦЫ ЕСЛИ НЕ СОЗДАНА курсов (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255),duration INT);";
            try (PreparedStatement statement = connection.prepareStatement(createTableSQL)){
                statement.execute();
            }
        }

    /**
     * добавление данных в таблицу courses
     * @param connection соединение с БД
     * @param course курс
     * @throws SQLException  исключение при выполнении запроса
     */

        private static void insertData(Connection connection, Courses course) throws SQLException {
            String insertDataSQL = "INSERT INTO students (title, duration) VALUES (?, ?);";
            try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
                statement.setString(1, course.getTitle());
                statement.setInt(2, course.getDuration());
                statement.executeUpdate();
            }
        }

        //endregion
    }
package com.example.demo;

import java.sql.*;
import java.util.ArrayList;

public class DB {

    // Данные для подключения к локальной базе данных
    private final String HOST = "localhost";
    private final String PORT = "1521";
    private final String DB_NAME = "java_todo_example";
    private final String LOGIN = "system"; // Если OpenServer, то здесь mysql напишите
    private final String PASS = "Qweqweqwe_123"; // Если OpenServer, то здесь mysql напишите

    private Connection dbConn = null;

    // Метод для подключения к БД с использованием значений выше
    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String login = "system";
        String password = "Qweqweqwe_123";
        System.out.println("Getting connection...");

        dbConn = DriverManager.getConnection(url, login, password);
        System.out.println("Ok");
        return dbConn;
    }

    // Метод для добавления новых заданий внуть таблицы `todo`
    public void insertTask(String task) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `todo` (task) VALUES (?)";

        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, task);

        prSt.executeUpdate();

    }

    // Метод для получения всех заданий из таблицы todo
    public ArrayList<String> getTasks() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM todo ORDER BY `id` DESC";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> tasks = new ArrayList<>();
        while(res.next())
            tasks.add(res.getString("task"));

        return tasks;
    }

}

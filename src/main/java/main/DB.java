package main;

import java.sql.*;
import java.util.ArrayList;

public class DB {

    // Метод для подключения к БД с использованием значений выше
    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String login = "system";
        String password = "Qweqweqwe_123";
        System.out.println("Getting connection...");
        Connection con = DriverManager.getConnection(url, login, password);
        System.out.println("OK");
        return con;
    }
    String nick;
//     Метод для добавления новых рекордов внуть таблицы
    public void insertNick(String nick) {
       this.nick = nick;

    }

    public void insertScore(int score) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Space " + "(Nickname, Score) " +"VALUES" + "('"  + nick + "'," + score + ")";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql);

    }
    public void setPrevScore(){
        String sql = "select score from Space where nickname="+nick;

    }
    // Метод для получения всех заданий из таблицы
    public ArrayList<String> getTasks() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM todo ORDER BY id";

        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);

        ArrayList<String> tasks = new ArrayList<>();
        while(res.next())
            tasks.add(res.getString("task"));

        return tasks;
    }

}

package client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DB {

    // Метод для подключения к БД
    static Connection getDbConnection() throws ClassNotFoundException, SQLException {
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
    public void deleteScore() throws SQLException, ClassNotFoundException {
        String sql =  "delete from space where nickname = '" + nick +"'";
        Statement statement = getDbConnection().createStatement();
        statement.executeUpdate(sql);

    }
    String nc = "name";
    public void insertScore(int score) throws SQLException, ClassNotFoundException {
        Statement statement = getDbConnection().createStatement();
        String sql = "select nickname from space where nickname = '" + nick + "'";
        statement.executeUpdate(sql);
        ResultSet res = statement.getResultSet();
        while (res.next()) {
            nc = res.getString("Nickname");
        }
        if (!(nc.equals(nick))){
            sql = "INSERT INTO Space (Nickname, Score) VALUES('"  + nick + "'," + score + ")";
            statement.executeUpdate(sql);
        }
        else if (getPrevScore() < score){
            sql = "UPDATE space  SET score = " + score + "WHERE nickname = '"+nick+ "'";
            statement.executeUpdate(sql);
        }
    }
    int sc;
    public int getPrevScore() throws SQLException, ClassNotFoundException {

        String sql = "select score from Space where nickname='" + nick + "'";
        Statement statement = getDbConnection().createStatement();
        statement.executeQuery(sql);
        ResultSet res = statement.getResultSet();
        while (res.next()) {
            sc = res.getInt("Score");
        }

        return sc;
    }


//    public void getAllScore() throws SQLException, ClassNotFoundException {
//        String sql = "select * from Space";
//        Statement statement = getDbConnection().createStatement();
//        statement.executeUpdate(sql);
//        ResultSet res = statement.getResultSet();
//        ScoreController sc = new ScoreController();
//        while (res.next()) {
//           sc.res.getInt("Score");
//        }
//    }

}

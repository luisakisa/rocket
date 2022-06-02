package com.company;


import java.sql.*;
import java.util.HashMap;

public class DB{
    String nick;

    // Метод для подключения к БД
    public static Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String login = "system";
        String password = "Qweqweqwe_123";
        System.out.println("Getting connection...");
        Connection con = DriverManager.getConnection(url, login, password);
        System.out.println("OK");
        return con;
    }
//     Метод для добавления новых рекордов внуть таблицы
    public void insertNick(String nick) {
       this.nick = nick;
    }
    String nc = "name";
    public void insertScore(int score) {
        try {
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
    public  HashMap<String, Integer> getAllPlayer(){
    HashMap map = new HashMap<String, Integer>();
        try {
            Connection conn = getDbConnection();
            PreparedStatement ps = conn.prepareStatement("select * from Space");
            ResultSet res = ps.executeQuery();
            while (res.next()){
                map.put(res.getString("Nickname"),Integer.parseInt(res.getString("Score")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;}

}

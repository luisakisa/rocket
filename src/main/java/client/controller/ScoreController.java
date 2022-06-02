package client.controller;

import client.db_thread.DB;
import client.elements.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ScoreController {
    public void secondWindow() throws SQLException, ClassNotFoundException {

            FXMLLoader fxmlLoader = new FXMLLoader();
             fxmlLoader.setLocation(getClass().getResource("/Score.fxml"));
            // определяем таблицу и устанавливаем данные
            TableView<Player> table = new TableView<>(getAllPlayer());
            table.setStyle("-fx-background-color: #161c22;");
            table.setPrefWidth(350);
            table.setPrefHeight(200);

            // столбец для вывода имени
            TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
            // определяем фабрику для столбца с привязкой к свойству name
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            // добавляем столбец
            table.getColumns().add(nameColumn);

            // столбец для вывода возраста
            TableColumn<Player, Integer> scoreColumn = new TableColumn<>("Score");
            scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
            scoreColumn.setPrefWidth(175);
            nameColumn.setPrefWidth(175);
            table.getColumns().add(scoreColumn);
            FlowPane root = new FlowPane(60, 50, table);
            Scene scene = new Scene(root, 350, 200);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Score");
            stage.show();

    }
    public  static ObservableList<Player> getAllPlayer(){
        ObservableList<Player> list = FXCollections.observableArrayList();
        try {
            Connection conn = DB.getDbConnection();
            PreparedStatement ps = conn.prepareStatement("select * from Space");
            ResultSet res = ps.executeQuery();
            while (res.next()){
                list.add(new Player(res.getString("Nickname"),Integer.parseInt(res.getString("Score"))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
}}

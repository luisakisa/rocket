package client.controller;

import client.net.Net;
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
import java.util.Comparator;
import java.util.Map;


public class ScoreController {
    Net net;

    ScoreController(Net net) {
        this.net = net;
    }

    public void secondWindow() throws SQLException, ClassNotFoundException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Score.fxml"));
        // определяем таблицу и устанавливаем данные
        TableView<Player> table = new TableView<>(getAllPlayer());
        table.setStyle("-fx-background-color: #161c22;");
        table.setPrefWidth(400);
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
        scoreColumn.setStyle("-fx-background-color: #161c22; -fx-text-fill: white;");
        nameColumn.setStyle("-fx-background-color: #161c22; -fx-text-fill: white;");
        scoreColumn.setPrefWidth(200);
        nameColumn.setPrefWidth(182);
        table.getColumns().add(scoreColumn);
        FlowPane root = new FlowPane(60, 50, table);
        Scene scene = new Scene(root, 400, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Score");
        stage.show();

    }

    public ObservableList<Player> getAllPlayer() {
        ObservableList<Player> list = FXCollections.observableArrayList();


        try {
            for (Map.Entry<String, Integer> entry : net.getPlayers().entrySet()) {
                list.add(new Player(entry.getKey(), entry.getValue()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        list.sort(new ScoreComparator());
        return list;
    }

    static class  ScoreComparator implements Comparator<Player> {
        @Override
        public int compare(Player a, Player b) {
            return a.getScore() < b.getScore() ? 1 : a.getScore() == b.getScore() ? 0 : -1;
        }
    }

}

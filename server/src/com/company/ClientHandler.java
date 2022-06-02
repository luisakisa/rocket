package com.company;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler extends Thread {

    public Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    public BufferedReader in; // поток чтения из сокета
    public BufferedWriter out; // поток записи в сокет

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        // если потоку ввода/вывода приведут к генерированию исключения, оно проброситься дальше
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start(); // вызываем run()
    }
    DB db =  new DB();
    @Override
    public void run() {
        String nickname = "";
        int score = 0;
        try {
            nickname = in.readLine();
            score = in.read();
            System.out.println(nickname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.insertNick(nickname);
        db.insertScore(score);
        HashMap<String, Integer> map =  db.getAllPlayer();
        try {
            out.write(map.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            try {
                out.write(entry.getKey());
                out.newLine();
                out.write(entry.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

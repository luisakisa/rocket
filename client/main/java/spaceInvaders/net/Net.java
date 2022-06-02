package spaceInvaders.net;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class Net {
    private BufferedReader reader;
    private BufferedReader in;
    private BufferedWriter out;

    public Net(){
        try {
            Socket s = new Socket("127.0.0.1",1111);

            reader = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        }
        catch (Exception e)
        {

        }

    }

    public HashMap<String, Integer> getPlayers() {
        HashMap map = new HashMap<String, Integer>();
        try {
            int count = in.read();
            for (int i = 0; i<count;i++) {
                String nickname = in.readLine();
                int score = in.read();
                map.put(nickname, score);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

           return map;
    }


    //     Метод для добавления новых рекордов внуть таблицы
    public void insertNick(String nick) throws IOException {
        out.write(nick);
        out.newLine();
        out.flush();
    }

    public void insertScore(int score) throws IOException {
        out.write(score);
        out.flush();
    }
}
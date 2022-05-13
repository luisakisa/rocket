package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

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
    public BufferedReader getIn() {
        return in;
    }

    public BufferedWriter getOut() {
        return out;
    }
}
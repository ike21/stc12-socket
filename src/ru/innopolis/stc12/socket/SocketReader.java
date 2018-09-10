package ru.innopolis.stc12.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketReader implements Runnable {
    private Socket socket;
    private ClientListener clientListener;
    private BufferedReader read;

    public SocketReader(Socket socket, ClientListener clientListener) {
        this.socket = socket;
        this.clientListener = clientListener;
        try {
            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            String word = null;
            try {
                word = read.readLine();
            } catch (IOException e) {
                if (e.getMessage().equals("Socket closed")) {
                    break;
                }
                System.out.println("Server was disconnected");
                clientListener.close();
            }
            System.out.println(word);

        }
    }
}

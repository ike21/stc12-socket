package ru.innopolis.stc12.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {

    public static final int PORT = 8769;
    public static CopyOnWriteArrayList<ServerListener> serverList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        try {
            while (true) {
                Socket socket = server.accept();
                serverList.add(new ServerListener(socket));
            }
        } finally {
            server.close();
        }
    }
}
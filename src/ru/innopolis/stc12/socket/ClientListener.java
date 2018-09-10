package ru.innopolis.stc12.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientListener {
    private Socket socket;
    private Scanner scaner;
    private BufferedWriter write;

    public ClientListener(String host, int port) {
        try {
            socket = new Socket(host, port);
            write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scaner = new Scanner(System.in);
        new Thread(new SocketReader(socket, this)).start();
    }

    public void writeMsg() {
        while (true) {
            String msg = scaner.next();
            if (socket.isClosed()) {
                close();
                break;
            } else {
                try {
                    write.write(msg + "\n");
                    write.flush();
                } catch (IOException e) {
                    close();
                }
            }
        }
    }

    public synchronized void close() {
        if (!socket.isClosed()) {
            try {
                socket.close();
                System.exit(0);
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
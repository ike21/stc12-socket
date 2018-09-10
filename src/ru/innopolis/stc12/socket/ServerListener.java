package ru.innopolis.stc12.socket;

import java.io.*;
import java.net.Socket;

class ServerListener extends Thread {

    private static int id = 0;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerListener(Socket socket) {
        this.socket = socket;
        id++;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        String word;
        try {

            while (true) {
                word = in.readLine();
                if (word.equals("quit")) {
                    break;
                }
                word = "client №" + id + ": " + word;
                System.out.println("Server log: " + word);

                String finalWord = word;
                Server.serverList.forEach((ServerListener serverListener) -> serverListener.send(finalWord));
            }

        } catch (IOException e) {
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {
        }
    }
}

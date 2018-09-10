package ru.innopolis.stc12.socket;

public class Client {
    public static void main(String[] args) {
        ClientListener clientListener = new ClientListener("localhost", 8769);
        clientListener.writeMsg();
    }
}

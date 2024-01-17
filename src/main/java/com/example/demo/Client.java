package com.example.demo;

public class Client {
    public String clientName;
    public SocketWrapper socketWrapper;
    public Client(String clientName, SocketWrapper socketWrapper){
        this.clientName = clientName;
        this.socketWrapper = socketWrapper;
    }
}
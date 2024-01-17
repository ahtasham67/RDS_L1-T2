package com.example.demo;

import javafx.application.Platform;

import java.io.IOException;
import java.util.ArrayList;

//import static client.project.ClientMain.companyMovie;

public class ReadThread implements Runnable {
    public ClientMain client;
    public SocketWrapper socketWrapper;
    public Thread thread;
    public static boolean run;

    public static void startKor() {
        run = true;
    }

    public static void stopKor() {
        run = false;
    }

    ReadThread(SocketWrapper socketWrapper, ClientMain client) {
        this.client = client;
        this.socketWrapper = socketWrapper;
        thread = new Thread(this);
        thread.start();
        run=true;
    }

    @Override
    public void run() {
        System.out.println("read thread started");
        while (true) {
            try {
                Object str = ClientMain.socketWrapper.read();
                System.out.println("reading");
                if (str instanceof String) {

                    if (((String) str).equalsIgnoreCase("order jacche")) {
                        System.out.println("order jacche");

                        try {
                            ArrayList<orderedFood> temp = new ArrayList<>();
                            temp = (ArrayList<orderedFood>) ClientMain.socketWrapper.read();
                            for (orderedFood food : temp) {
                                if (food.getFood().getRestaurant_Id() == ClientMain.restaurantID) {
                                    ClientMain.foodOFOrder.add(food);
                                    //food.getFood().showFoodDetails();
                                }
                            }

                            Platform.runLater(() -> {
                                try {
                                    client.updateFound();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });


                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
                else if (str instanceof ArrayList<?>) {

                    ArrayList<orderedFood> temp1 = new ArrayList<>();
                    temp1 = (ArrayList<orderedFood>) str;
                    for (orderedFood food : temp1) {
                        if (food.getFood().getRestaurant_Id() == ClientMain.restaurantID) {
                            ClientMain.foodOFOrder.add(food);
                        }
                    }
                    Platform.runLater(() -> {
                        try {
                            // if(ClientMain.start == 1) clientMain.close();
                            client.showOrderPagefromRestaurant();

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }


    }
}


package ServerAll;

import com.example.demo.Food;
import com.example.demo.orderedFood;

import java.io.IOException;
import java.util.ArrayList;

public class userReadWriteThread implements Runnable {
    String fromClient;
    //private final Client client;
    //private final Client client;
    //private SocketWrapper sckt;
    private Client client;

    public userReadWriteThread(ServerAll.Client client) {
        // sckt = client.socketWrapper;
        //ServerMain.clientList.add(client);
        this.client = client;
        new Thread(this).start();

    }


//    public SetuserReadWriteThread(Client client) {
//        ServerMain.clientList.add(client);
//        this.client = client;
//        new Thread(this).start();
//    }

    @Override
    public void run() {
//        String USERclientName = null;
//
//        try {
//            USERclientName = (String) client.socketWrapper.read();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(USERclientName + " connected");

//        while (!doesRestuarantExist(clientName)){
        //socketWrapper.write("You are not registered.SIGN UP new restaurant!!!");
//            clientName = (String) socketWrapper.read();
//        }
        //socketWrapper.write("No objection");
        //socketWrapper.write(getRestaurantIdByName(clientName));
        //ArrayList<Food>temp=getfoodMenuList(clientName);
//        for(Food food:temp){
//            System.out.println(food.getName());
//        }
        //socketWrapper.write(restaurantList);
        //socketWrapper.write(getRestaurantIdByName(clientName));
        try {
            client.socketWrapper.write(ServerMain.restaurantList); //sending rsetaurant list to customer
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            client.socketWrapper.write(ServerMain.foodItems);//sending food list to custome
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            while (true) {
                fromClient = (String) client.socketWrapper.read();

                switch (fromClient) {

//                    case "order" -> {
////                        ServerMain.ashcheKiNa=true;
////                        ArrayList<Food> temp = new ArrayList<>();
////                        temp = (ArrayList<Food>) client.socketWrapper.read();
////
////                        ServerMain.orderlist.addAll(temp);
//                        ServerMain.ashcheKiNa = true;
//                        ArrayList<orderedFood> temp = new ArrayList<>();
//                        //Object o=client.socketWrapper.read();
//                        // if(o instanceof ArrayList<?>) {
//                        temp = (ArrayList<orderedFood>) client.socketWrapper.read();
//                        ServerMain.orderlistNEW.addAll(temp);
//
//                        client.socketWrapper.write("order jacche");
//                        client.socketWrapper.write(ServerMain.orderlistNEW);
//                        ///////
//                        for(orderedFood f:ServerMain.orderlistNEW){
//                            f.getFood().showFoodDetails();
//                        }
//                        temp.clear();
//                        // }
//                    }
                    case "order" -> {
//                        ServerMain.ashcheKiNa=true;
//                        ArrayList<Food> temp = new ArrayList<>();
//                        temp = (ArrayList<Food>) client.socketWrapper.read();
//
//                        ServerMain.orderlist.addAll(temp);
                        System.out.println("order received");
                        ServerMain.ashcheKiNa = true;
                        ArrayList<orderedFood> temp = new ArrayList<>();
                        //Object o=client.socketWrapper.read();
                        // if(o instanceof ArrayList<?>) {
                        temp = (ArrayList<orderedFood>) client.socketWrapper.read();
                        //ServerMain.orderlistNEW.addAll(temp);
                        ServerMain.orderlistNEW=temp;
for(SocketWrapper sckt:ServerMain.saveSocketwrapperofServer) {
    sckt.write("order jacche");
   sckt.write(ServerMain.orderlistNEW);
}
                        ///////
                        for(orderedFood f:ServerMain.orderlistNEW){
                            f.getFood().showFoodDetails();
                        }
                        temp.clear();
                        // }
                    }


                    case "Close" -> client.socketWrapper.write(null);
                }
            }
        } catch (Exception exception) {
            System.out.println("Connection lost: " + exception);
        } finally {
            try {
                client.socketWrapper.closeConnection();
                ServerMain.clientList.remove(client);

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }


//        try {
//            while (true){
//                fromClient = (String) client.socketWrapper.read();
//                switch (fromClient) {
//                    case "Add movie" -> {
//                        Food food = (Food) client.socketWrapper.read();
//                        ServerMain.foodItems.add(food);
////                        for (Client x : ServerMain.clientList)
////                            if (client.clientName.equalsIgnoreCase(x.clientName))
////                                x.socketWrapper.write(getMoviesOfCompany(client.clientName));
////                        writeToDatabase();
//                    }
//                    case "order"->{
//                        ArrayList<Food> orderlist=new ArrayList<>();
//                        orderlist= (ArrayList<Food>) client.socketWrapper.read();
//                        //distribute the ordred food item to the restauarant
//                    }
////                    case "Transfer movie" -> {
////                        String movieTitle, destinationCompany;
////                        movieTitle = (String) client.socketWrapper.read();
////                        destinationCompany = (String) client.socketWrapper.read();
////                        if (ServerMain.companyList.contains(destinationCompany)) {
////                            getMovieByTitle(movieTitle).setProductionCompany(destinationCompany);
////                            for (Client x : ServerMain.clientList)
////                                if (client.clientName.equalsIgnoreCase(x.clientName))
////                                    x.socketWrapper.write(getMoviesOfCompany(client.clientName));
////                            for (Client x : ServerMain.clientList)
////                                if (destinationCompany.equalsIgnoreCase(x.clientName))
////                                    x.socketWrapper.write(getMoviesOfCompany(x.clientName));
////                            writeToDatabase();
////                        }
////                    }
//                    case "Close" -> client.socketWrapper.write(null);
//                }
//            }
//        }catch (Exception exception){
//            System.out.println("Connection lost: " + exception);
//        }finally {
//            try {
//                client.socketWrapper.closeConnection();
//                ServerMain.clientList.remove(client);
//
//            }catch (IOException exception){
//                exception.printStackTrace();
//            }
//        }
        // Client client = new Client(USERclientName, sckt);
        //new userReadWriteThread(client);
//        try {
//            while (true){
//                fromClient = (String) client.socketWrapper.read();
//                switch (fromClient) {
//                    case "Add movie" -> {
//                        Food food = (Food) client.socketWrapper.read();
//                        ServerMain.foodItems.add(food);
////                        for (Client x : ServerMain.clientList)
////                            if (client.clientName.equalsIgnoreCase(x.clientName))
////                                x.socketWrapper.write(getMoviesOfCompany(client.clientName));
////                        writeToDatabase();
//                    }
////                    case "Transfer movie" -> {
////                        String movieTitle, destinationCompany;
////                        movieTitle = (String) client.socketWrapper.read();
////                        destinationCompany = (String) client.socketWrapper.read();
////                        if (ServerMain.companyList.contains(destinationCompany)) {
////                            getMovieByTitle(movieTitle).setProductionCompany(destinationCompany);
////                            for (Client x : ServerMain.clientList)
////                                if (client.clientName.equalsIgnoreCase(x.clientName))
////                                    x.socketWrapper.write(getMoviesOfCompany(client.clientName));
////                            for (Client x : ServerMain.clientList)
////                                if (destinationCompany.equalsIgnoreCase(x.clientName))
////                                    x.socketWrapper.write(getMoviesOfCompany(x.clientName));
////                            writeToDatabase();
////                        }
////                    }
//                    case "Close" -> client.socketWrapper.write(null);
//                }
//            }
//        }catch (Exception exception){
//            System.out.println("Connection lost: " + exception);
//        }finally {
//            try {
//                client.socketWrapper.closeConnection();
//                ServerMain.clientList.remove(client);
//
//            }catch (IOException exception){
//                exception.printStackTrace();
//            }
//        }
//
//    }
//    public ArrayList<Movie> getMoviesOfCompany(String company) {
//        ArrayList<Movie> moviesOfACompany = new ArrayList<>();
//        for (Movie movie : ServerMain.movieDataBase)
//            if (company.equalsIgnoreCase(movie.getProductionCompany()))
//                moviesOfACompany.add(movie);
//        return moviesOfACompany;
//    }
//    public Movie getMovieByTitle(String movieTitle){
//        for (Movie movie: ServerMain.movieDataBase)
//            if (movie.getTitle().equalsIgnoreCase(movieTitle))
//                return movie;
//        return null;
//    }
//    void writeToDatabase() throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ServerMain.FILE_NAME));
//        for (Movie movie : ServerMain.movieDataBase){
//            String[] movieAttribute = new String[9];
//            movieAttribute[0] = movie.getTitle();
//            movieAttribute[1] = String.valueOf(movie.getYearOfRelease());
//            movieAttribute[2] = movie.getGenre1();
//            movieAttribute[3] = movie.getGenre2();
//            movieAttribute[4] = movie.getGenre3();
//            movieAttribute[5] = String.valueOf(movie.getRunningTime());
//            movieAttribute[6] = movie.getProductionCompany();
//            movieAttribute[7] = String.valueOf(movie.getBudget());
//            movieAttribute[8] = String.valueOf(movie.getRevenue());
//
//            bufferedWriter.write(
//                    IntStream.of(0, 1, 2, 3, 4, 5, 6, 7, 8).mapToObj(i -> movieAttribute[i]).collect(Collectors.joining(",")));
//            bufferedWriter.write("\n");
//        }
//        bufferedWriter.close();
//    }
    }
}



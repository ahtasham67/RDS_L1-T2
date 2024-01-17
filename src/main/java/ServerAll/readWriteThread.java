package ServerAll;

import com.example.demo.Food;
import com.example.demo.Restaurant;
import com.example.demo.orderedFood;

import java.io.IOException;
import java.util.ArrayList;

public class readWriteThread implements Runnable {
    String fromClient;
    private Client client;

    public readWriteThread(Client client) {
        ServerMain.clientList.add(client);
        this.client = client;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                fromClient = (String) client.socketWrapper.read();
                switch (fromClient) {
//                    case "get order" -> {
//                        if (ServerMain.ashcheKiNa) {
//                            client.socketWrapper.write("order gese pagla");
//                            //String str = (String) client.socketWrapper.read();
//
////                            int restId = -1;
////                            for (Restaurant restaurant : ServerMain.restaurantList) {
////                                if (restaurant.getName().equalsIgnoreCase(str)) {
////                                    restId = restaurant.getId();
////                                }
////                            }
//
////                            ArrayList<Food> order = new ArrayList<>();
////                            for (Food food : ServerMain.orderlist) {
////                                if (food.getRestaurant_Id() == restId) {
////                                    //client.socketWrapper.write(food);
////                                    order.add(food);
////                                    // food.showFoodDetails();
////                                }
////                            }
//                            //int i=5;
//                            client.socketWrapper.write(ServerMain.orderlistNEW);
//                            ServerMain.ashcheKiNa = false;
//                            // client.socketWrapper.write(i);
//                        }
//                        else {
//                           client.socketWrapper.write("order nei pagla");
//                        }
//                    }


                    case "Add food" -> {
                        Food food = (Food) client.socketWrapper.read();
                        ServerMain.foodItems.add(food);
                        ServerMain.writeMenuFile();
//                        for (Client x : ServerMain.clientList)
//                            if (client.clientName.equalsIgnoreCase(x.clientName))
//                                x.socketWrapper.write(getMoviesOfCompany(client.clientName));
//                        writeToDatabase();
                        break;
                    }

//
                    case "Close" -> client.socketWrapper.write(null);
                }
            }
        } catch (Exception exception) {
            System.out.println("Connection lost: " + exception);
        }
        finally {
            try {
                client.socketWrapper.closeConnection();
                ServerMain.clientList.remove(client);

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

    }
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
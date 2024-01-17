package ServerAll;//import com.example.project.*;

//import client.food.Food;

import com.example.demo.Food;
import com.example.demo.Restaurant;
import com.example.demo.orderedFood;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    static ArrayList<Restaurant> restaurantList;
    static ArrayList<String> restaurantNameList;
    static ArrayList<Food> foodItems;
    public static ArrayList<Client> clientList;
    private static final String INPUT_FILE_NAME1 = "restaurant.txt";
    private static final String INPUT_FILE_NAME2 = "menu.txt";
    public static ArrayList<Food> orderlist;
    public static ArrayList<orderedFood> orderlistNEW = new ArrayList<>();
    public static boolean ashcheKiNa;
    public static ArrayList<SocketWrapper> saveSocketwrapperofServer=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new ServerMain();
    }

    ServerMain() throws IOException {
        clientList = new ArrayList<>();
        restaurantList = getRestaurantList();
        foodItems = getMenuList();
        orderlist = new ArrayList<>();
        //print();
        connectClient();
    }

    //just to be sure whether file has read properly or not
//    public void print() {
//        for (int i = 0; i < restaurantList.size(); i++) {
//            restaurantList.get(i).ShowDetails();
//        }
//    }

    private void serve(SocketWrapper socketWrapper) throws IOException, ClassNotFoundException {
        // SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        String clientName = (String) socketWrapper.read();
        System.out.println(clientName + "connected");
        if (clientName.equalsIgnoreCase("add restaurant")) {
            Restaurant restaurant = (Restaurant) socketWrapper.read();
            if (!doesRestuarantExist(restaurant.getName(), restaurant.getId())) {
                restaurantList.add(restaurant);
                writeRestaurantFile();
                socketWrapper.write("okay");
            } else {
                socketWrapper.write("already exist");
            }
//            for (Restaurant r : ServerMain.restaurantList) {
//                //System.out.prr
//                r.ShowDetails();
//            }
        } else {
            while (!doesRestuarantExist(clientName)) {
                socketWrapper.write("You are not registered.SIGN UP new restaurant!!!");
                clientName = (String) socketWrapper.read();
            }
            socketWrapper.write("No objection");
            //socketWrapper.write(getRestaurantIdByName(clientName));
            ArrayList<Food> temp = getfoodMenuList(clientName);
            for (Food food : temp) {
                System.out.println(food.getName());
            }
            socketWrapper.write(temp);
            socketWrapper.write(getRestaurantIdByName(clientName));
            //socketWrapper.write(restaurantList);
            //socketWrapper.write(foodItems);
            Client client = new Client(clientName, socketWrapper);
            new readWriteThread(client);
        }
    }

//    private void userServe(SocketWrapper socketWrapper) throws IOException, ClassNotFoundException {
//        //SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
//        String USERclientName = (String) socketWrapper.read();
//         System.out.println(USERclientName);
////        while (!doesRestuarantExist(clientName)){
//            //socketWrapper.write("You are not registered.SIGN UP new restaurant!!!");
////            clientName = (String) socketWrapper.read();
////        }
//        //socketWrapper.write("No objection");
//        //socketWrapper.write(getRestaurantIdByName(clientName));
//        //ArrayList<Food>temp=getfoodMenuList(clientName);
////        for(Food food:temp){
////            System.out.println(food.getName());
////        }
//        //socketWrapper.write(restaurantList);
//        //socketWrapper.write(getRestaurantIdByName(clientName));
//        socketWrapper.write(restaurantList);
//        socketWrapper.write(foodItems);
//        Client client = new Client(USERclientName, socketWrapper);
//        new userReadWriteThread(client);
//    }

    private void connectClient() {
        try {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                while (true) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
                        String str = (String) socketWrapper.read();
                        if (str.equals("as user")) {
                            String str1 = (String) socketWrapper.read();
                            Client client = new Client(str1, socketWrapper);
                            System.out.println(str1 + " connected");
                            new userReadWriteThread(client);
                            //userServe(socketWrapper);
                        } else {
                            System.out.println("connected");
                            saveSocketwrapperofServer.add(socketWrapper);
                            serve(socketWrapper);
                        }
                        //userServe(clientSocket);
                    } catch (IOException | ClassNotFoundException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        } catch (Exception exception) {
            System.out.println("Server starts: " + exception);
        }
    }

    //create a Restaurant name list for valid login

    /*private ArrayList<String> getRestaurantNameList(){
        ArrayList<String> resNameList = new ArrayList<>();
        for(int i=0; i<restaurantList.size(); i++) {
           resNameList.add(restaurantList.get(i).getName());
        }
        return resNameList;
    }*/

    //read restaurant file
    private ArrayList<Restaurant> getRestaurantList() throws IOException {
        ArrayList<Restaurant> restList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME1));
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            // System.out.println(line);
            String[] array = line.split(",", -1);
            ArrayList<String> category = new ArrayList<>();
            for (int i = 5; i <= 7; i++) {
                // System.out.println(array[i]);
                if (!array[i].isEmpty()) {
                    category.add(array[i]);
                }
            }
            Restaurant restaurant = new Restaurant(Integer.parseInt(array[0]), array[1], Double.parseDouble(array[2]),
                    array[3], array[4], category);
            //addRestaurant(restaurant);
            restList.add(restaurant);
        }
        br.close();
        return restList;
    }

    //read food menu file
    private ArrayList<Food> getMenuList() throws IOException {
        ArrayList<Food> menuList = new ArrayList<>();
        BufferedReader Br = new BufferedReader(new FileReader(INPUT_FILE_NAME2));
        while (true) {
            String Line = Br.readLine();
            if (Line == null)
                break;
            // System.out.println(line);
            String[] arr = Line.split(",", -1);
            Food fooditem = new Food(Integer.parseInt(arr[0]), arr[1], arr[2], Double.parseDouble(arr[3]));
            // RDS.addFoodItem(fooditem);
            menuList.add(fooditem);
        }
        Br.close();
        return menuList;
    }

    public ArrayList<Food> getfoodMenuList(String clientName) {
        ArrayList<Food> getMenuOfSpecificRestaurant = new ArrayList<>();
        for (int i = 0; i < foodItems.size(); i++) {
            if (getRestaurantIdByName(clientName) == foodItems.get(i).getRestaurant_Id()) {
                getMenuOfSpecificRestaurant.add(foodItems.get(i));
            }
        }
        return getMenuOfSpecificRestaurant;
    }

    //hregfiuyreiufghurghgiuhriughiuerhgukehigrik
    //rjkgjrebgjkbrjkgjrenbgnekjgjkdebjkdebgkdej

    public void addRestaurant(Restaurant restaurant) {
        boolean alreadyExistRestaurant = false;
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getName().equalsIgnoreCase(restaurant.getName())
                    && restaurantList.get(i).getId() == restaurant.getId()) {
                alreadyExistRestaurant = true;
            }
        }
        if (!alreadyExistRestaurant)
            restaurantList.add(restaurant);
        else
            return;
    }

    public boolean doesRestuarantExist(String resStr) {
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getName().equalsIgnoreCase(resStr)) {
                return true;
            }
        }
        return false;
    }

    public boolean doesRestuarantExist(String resStr, int n) {
        for (int i = 0; i < restaurantList.size(); i++) {
            if ((restaurantList.get(i).getName().equalsIgnoreCase(resStr)) || (restaurantList.get(i).getId() == n)) {
                return true;
            }
        }
        return false;
    }

    public boolean foodAlreadyExist(Food food) {
        for (int i = 0; i < foodItems.size(); i++) {
            if ((foodItems.get(i).getRestaurant_Id() == food.getRestaurant_Id())
                    && (foodItems.get(i).getName().equalsIgnoreCase(food.getName()))
                    && (foodItems.get(i).getCategory().equalsIgnoreCase(food.getCategory()))
                    && (foodItems.get(i).getPrice() == food.getPrice()))
                return true;
        }
        return false;
    }

    public void addFoodItem(Food food) {
        // There cannot be two food items with the same name and same category in a
        // specific
        // restaurant. You need to consider this while adding food items
        /*
         * for (int i = 0; i < foodItems.size(); i++) {
         * if ((foodItems.get(i).getRestaurant_Id() == food.getRestaurant_Id()
         * && foodItems.get(i).getName().equalsIgnoreCase(food.getName())
         * && foodItems.get(i).getCategory().equalsIgnoreCase(food.getCategory()))) {
         * //foodItems.add(food);
         * return;
         * } else {
         * //System.out.println("Food already in the restaurant");
         * foodItems.add(food);
         * }
         * }
         */
        if (!foodAlreadyExist(food))
            foodItems.add(food);
        else
            return;
    }

    public int getRestaurantIdByName(String name) {
        int temp = -1;
        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getName().equalsIgnoreCase(name)) {
                temp = restaurantList.get(i).getId();
                break;
            }
        }
        return temp;
    }

    public static void writeRestaurantFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(INPUT_FILE_NAME1));

        for (Restaurant restaurant : restaurantList) {
            StringBuilder line = new StringBuilder();
            line.append(restaurant.getId()).append(",");
            line.append(restaurant.getName()).append(",");
            line.append(restaurant.getScore()).append(",");
            line.append(restaurant.getPrice()).append(",");
            line.append(restaurant.getZipCode()).append(",");
            List<String> categories = restaurant.getCategories();
            for (int i = 0; i < 3; i++) {
                if (i < categories.size()) {
                    line.append(categories.get(i));
                }
                if (i < 2) {
                    line.append(",");
                }
            }
            bw.write(line.toString());
            bw.write(System.lineSeparator());
        }

        bw.close();
    }

    public static void writeMenuFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(INPUT_FILE_NAME2));

        for (Food food : foodItems) {
            StringBuilder line = new StringBuilder();
            line.append(food.getRestaurant_Id()).append(",");
            line.append(food.getCategory()).append(",");
            line.append(food.getName()).append(",");
            line.append(food.getPrice());
            bw.write(line.toString());
            bw.write(System.lineSeparator());
        }
        bw.close();
    }

}

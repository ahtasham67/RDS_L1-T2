package com.example.demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ClientMain extends Application {
    final String serverAddress = "127.0.0.1";
    final int serverPort = 12345;
    boolean isOrderPage;
    Stage stage;
    public static SocketWrapper socketWrapper;
    // public static ArrayList<Food> restaurantMenu;// need to use it later
    public static ArrayList<Food> restaurantMenu;
    public static String restaurantName;
    public static int restaurantID;
    public static ReadThread readThread;
    public static boolean runReadThread = true;
    public static ObservableList<Restaurant> allRestaurants;
    public static ObservableList<Food> foodList;
    public static ObservableList<Food> RestaurantFoodList;
    public static ObservableList<String> CategoryWiseList;
    public static ObservableList<RestaurantMenuItem> count;
    public static ArrayList<Food>orderList=new ArrayList<>();
    public static ArrayList<Food>RestaurantOrderList=new ArrayList<>();
    public static ArrayList<orderedFood>foodOFOrder=new ArrayList<>();
    public static String usrname;
    Restaurant_Database_System RDS;



    // public static ObservableList<Movie> maxRevenueMovie;

    public static void main(String[] args) {
        launch();
    }

    public void updateFound() throws IOException {
//        System.out.println("Update found");
//        System.out.println("restaurant");
//
//        for (orderedFood food : foodOFOrder) {
//            if (food.getFood().getRestaurant_Id() == ClientMain.restaurantID) {
//                //ClientMain.foodOFOrder.add(food);
//                food.getFood().showFoodDetails();
//            }
//        }
        System.out.println(isOrderPage);
        if(isOrderPage) showOrderPagefromRestaurant();

    }
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        isOrderPage=false;
        /////////////
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        loginController companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("HOME");
        stage.setScene(scene);
        stage.show();
//
        stage.setOnCloseRequest(event -> close(stage));


    }

    //For Restaurant client
    void showLoginPage() throws IOException {
        isOrderPage=false;
        allRestaurants = FXCollections.observableArrayList();
        foodList = FXCollections.observableArrayList();
        RestaurantFoodList = FXCollections.observableArrayList();
        RestaurantOrderList=new ArrayList<>();
        restaurantMenu = new ArrayList<>();
        //maxRevenueMovie = FXCollections.observableArrayList();
        runReadThread = true;
        socketWrapper = new SocketWrapper(new Socket(serverAddress, serverPort));
        socketWrapper.write("as restaurant");
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("restaurantLogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        // LoginController loginController = fxmlLoader.getController();
        RestaurantLogInController loginController = fxmlLoader.getController();
        loginController.setClient(this);
        stage.setTitle("LOGIN");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> close(stage));
    }

    void showCompanyHome() throws IOException {
        isOrderPage=false;
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("RestaurantMainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        RestaurantMainMenuController companyHomeController = fxmlLoader.getController();

        companyHomeController.setClient(this);
        companyHomeController.init();
        stage.setTitle("HOME");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showMenuListPage() throws IOException {
        isOrderPage=false;
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("RestaurantMenuPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        RestaurantMenuPageController showAllitemsPageController = fxmlLoader.getController();
        showAllitemsPageController.setClient(this);
        for (Food food : restaurantMenu) {
            RestaurantFoodList.add(food);
        }
        showAllitemsPageController.setTable(RestaurantFoodList);
        stage.setTitle("MENU LIST");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void addFoodPage() throws IOException {
        isOrderPage=false;
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("addFood.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddFoodController addfoodcontoller = fxmlLoader.getController();
        addfoodcontoller.setClient(this);
//        for(Food food:restaurantMenu){
//            RestaurantFoodList.add(food);
//        }
        // addfoodcontoller.setTable(RestaurantFoodList);
        stage.setTitle("ADD FOOD");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showUserlogInPage() throws IOException {
        isOrderPage=false;
        allRestaurants = FXCollections.observableArrayList();
        foodList = FXCollections.observableArrayList();
        RestaurantFoodList = FXCollections.observableArrayList();
        restaurantMenu = new ArrayList<>();
        orderList=new ArrayList<>();
        CategoryWiseList=FXCollections.observableArrayList();
        //maxRevenueMovie = FXCollections.observableArrayList();
        runReadThread = true;
        socketWrapper = new SocketWrapper(new Socket(serverAddress, serverPort));
        socketWrapper.write("as user");
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("userLogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        // LoginController loginController = fxmlLoader.getController();

        UserLogInController loginController = fxmlLoader.getController();
        loginController.setClient(this);
        stage.setTitle("USER LOGIN");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> close(stage));
    }

    void showUserHome() throws IOException{
        isOrderPage=false;
        System.out.println("00000000000000000000000000000000000000");
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("userHomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //new UserHomePageController();
        UserHomePageController companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("HOME");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showRestaurantSearch() throws IOException{
        isOrderPage=false;
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("searchRestaurant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SearchRestaurantController companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("RESTAURANT SEARCH");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showRestaurantSearchBYNAME() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("searchRestaurantByNAME.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SearchRestaurantByNAMEcontroller companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("RESTAURANT SEARCH BY NAME");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showRestaurantSearchBYSCORE() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("searchRestaurantBySCORE.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SearchRestaurantBySCOREcontroller companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("RESTAURANT SEARCH BY SCORE");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showRestaurantSearchBYCategory() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("searchRestaurantByCATEGORY.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SearchRestaurantByCATEGORYcontroller companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("RESTAURANT SEARCH BY CATEGORY");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showRestaurantSearchBYPrice() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("searchRestaurantByPRICE.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SearchRestaurantByPRICEcontroller companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("RESTAURANT SEARCH BY PRICE");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showRestaurantSearchBYZipCode() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("searchRestaurantByZIPCODE.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SearchRestaurantByZIPCODEcontroller companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("RESTAURANT SEARCH BY ZIP CODE");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showFoodSearch() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("searchFood.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SearchFoodController companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("FOOD SEARCH");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void CategoryWiseRestaurantPage() throws IOException {
        isOrderPage=false;
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("categoryWIseRestaurant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        CategoryWIseRestaurantController showAllitemsPageController = fxmlLoader.getController();
        showAllitemsPageController.setClient(this);
        CategoryWiseList=FXCollections.observableArrayList();
        RDS=new Restaurant_Database_System();
        ArrayList<String> categoryWiseRestaurants =new ArrayList<>();
        categoryWiseRestaurants = (ArrayList<String>) RDS.getCategoryWiseRestaurantList();
        for(String str :categoryWiseRestaurants ){
            CategoryWiseList.add(str);
        }
        showAllitemsPageController.setTable(CategoryWiseList);
        //CategoryWiseList.clear();
        stage.setTitle("MENU LIST");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );


    }

    void totalFoodCountInRestaurantList() throws IOException {
        count=FXCollections.observableArrayList();
        RDS=new Restaurant_Database_System();


        for (Restaurant restaurant : RDS.getRestaurantList()) {
            //System.out.println(restaurant.getName() + ":" + RDS.getFoodCount(restaurant));
           // System.out.println();
            count.add(new RestaurantMenuItem(restaurant.getName(),RDS.getFoodCount(restaurant)));
        }
//        for(RestaurantMenuItem r:count){
//            System.out.println(r.getFoodCount());
//        }
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("TotalFoodItemOnMenuInEveryRestaurant.fxml"));
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
        Scene scene = new Scene(fxmlLoader.load());

        TotalFoodItemOnMenuInEveryRestaurantController showAllitemsPageController = fxmlLoader.getController();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
        showAllitemsPageController.setClient(this);
        showAllitemsPageController.setTable(count);

        //CategoryWiseList.clear();
        stage.setTitle("TOTAL FOOD ON MENU LIST");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );


    }

    void showFoodSearchBYNAME() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("serachFoodByNAME.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SerachFoodByNAMEcontroller companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("FOOD SEARCH BY NAME");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }
    void showFoodSearchBYNAMEandRESTAURANT() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("searchFoodByNameinRestaurant.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SearchFoodByNameinRestaurantController companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("FOOD SEARCH BY NAME and RESTAURANT");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showFoodSearchBYCATEGORY() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("serachFoodByCATEGORY.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SerachFoodByCATEGORYcontroller companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("FOOD SEARCH BY CATEGORY");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showFoodSearchBYCATEGORYinRESTAURANT() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("serachFoodByCATEGORYinRESTAURANT.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SerachFoodByCATEGORYinRESTAURANTcontroller companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("FOOD SEARCH BY CATEGORY IN RESTAURANT");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showFoodSearchBYPRICERANGE() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("serachFoodByPRICERANGE.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SerachFoodByPRICERANGEcontroller companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("FOOD SEARCH BY PRICE RANGE");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    void showFoodSearchBYPRICERANGEinRESTAURANT() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("serachFoodByPRICERANGEinRESTAURANT.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SerachFoodByPRICERANGEinRESTAURANTcontroller companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("FOOD SEARCH BY PRICE RANGE in RESTAUARNT");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }
    void showCostliestFoodofRestaurant() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("searchCOSTLIESTfood.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SearchCOSTLIESTfoodController companyHomeController = fxmlLoader.getController();
        companyHomeController.setClient(this);
        stage.setTitle("COSTLIEST FOOD ITEMS OF RESTAURANT");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    public void showCartWindow() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("userShowCart.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        UserShowCartController showAllitemsPageController = fxmlLoader.getController();
        showAllitemsPageController.setClient(this);
        ObservableList<Food> allOrder=FXCollections.observableArrayList();
        allOrder.addAll(orderList);
        //showAllitemsPageController.setTable(allOrder);
        stage.setTitle("CART");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

    public void close(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("You are about to close");
        alert.setContentText("Are you confirm?");
        if (alert.showAndWait().get() == ButtonType.YES) {
            stage.close();
            try {
                ClientMain.socketWrapper.write("Close");
                ClientMain.socketWrapper.closeConnection();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    void logOut() {
        showLogoutConfirmationMessage();
    }

    void showLogoutConfirmationMessage() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogoutConfirmation.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        LogoutConfirmationController logoutConfirmationController = fxmlLoader.getController();
        logoutConfirmationController.setClient(this);
        logoutConfirmationController.messageLabel.setText("ARE YOU CONFIRM?");
        stage.setTitle("LOGOUT CONFIRMATION");
        stage.setScene(scene);
        stage.show();
        logoutConfirmationController.setStage(stage);
    }
    void showOrderPagefromRestaurant() throws IOException{
        isOrderPage=true;
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("orderPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        OrderPageController companyHomeController = fxmlLoader.getController();
        //Stage stage =new Stage();
        companyHomeController.setClient(this);
        stage.setTitle("PENDING ORDERS");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
                    event.consume();
                    logOut();
                }
        );
    }

   void showAddRsetaurantPage() throws IOException{
       isOrderPage=false;
       FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("addRestaurant.fxml"));
       Scene scene = new Scene(fxmlLoader.load());
       AddRestaurantController companyHomeController = fxmlLoader.getController();
       //Stage stage =new Stage();
       companyHomeController.setClient(this);
       stage.setTitle("addRestaurant");
       stage.setScene(scene);
       stage.show();
       stage.setOnCloseRequest(event -> {
                   event.consume();
                   logOut();
               }
       );
   }


}
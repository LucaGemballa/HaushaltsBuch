package view;

import entity.CategorySeries;
import entity.SavingsAccount;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;

import javafx.stage.Stage;
import javafx.event.*;
import  javafx.scene.input.MouseEvent;

//import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
//imports for IOException
/*import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;*/
import java.io.IOException;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.collections.FXCollections;
import java.util.Arrays;


public class MainScreenController {

    //public static LinkedList<moneyAction> transferList = new LinkedList<>();

    @FXML
    private Button btnAddEarning;

    @FXML
    private Button btnAddSpending;
    @FXML
    private Button addAccountBtn;

    @FXML
    private Button editCathegories;

    @FXML
    private Label mainAccountBalance;

    @FXML
    private Label currentMonthBalance;

    @FXML
    private Tab mainTab;
    @FXML
    private Pane basePane;

    @FXML
    private ScrollPane savingsScrollPane;
    @FXML
    private AnchorPane savingsAccountsPane;

    @FXML
    public TilePane savingsAccountsTilePane;

    @FXML
    private Button btnAddBooking;

    @FXML
    private Pane savingsAccountsOverviewPane;

    public Boolean notInitialised = true;

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    public static CategorySeries categorySeriesEarnings = new CategorySeries();
    public static CategorySeries categorySeriesSpendings = new CategorySeries();


    @FXML
    private StackedBarChart<String,Number> earningChart =
            new StackedBarChart<String,Number>(xAxis,yAxis);


    final CategoryAxis xAxisSpending = new CategoryAxis();
    final NumberAxis yAxisSpending = new NumberAxis();

    @FXML
    private StackedBarChart<String,Number> spendingChart =
            new StackedBarChart<String,Number>(xAxisSpending,yAxisSpending);

    public void initialize(){
        mainAccountBalance.textProperty().bind(Main.rootService.accountList.getFirst().balanceObserve);
        Main.rootService.accountList.getFirst().balanceObserve.setValue(Main.rootService.accountList.getFirst().getBalanceString());
    }

    @FXML
    public void AddEarningWindow (){
        try {
            Pane newLoadedPane =        FXMLLoader.load(getClass().getResource("/view/addTransaction.fxml"));
            basePane.getChildren().clear();
            basePane.getChildren().add(newLoadedPane);

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void AddSpendingWindow (){
        try {

            Pane newLoadedPane =        FXMLLoader.load(getClass().getResource("/view/addSpending.fxml"));
            basePane.getChildren().clear();
            basePane.getChildren().add(newLoadedPane);


        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void addAccountDeletionPrompt(){

    }

    @FXML
    public void addBookingWindow (){
        try {

            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/view/addBooking.fxml"));
            basePane.getChildren().clear();
            basePane.getChildren().add(newLoadedPane);

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void addSavingsAccountOverview(SavingsAccount n){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/savingsAccountsOverview.fxml"));

            Pane newLoadedPane = loader.load();
            //FXMLLoader.load(getClass().getResource("/view/savingsAccountsOverview.fxml"));
            SavingsAccountsOverviewController c = loader.getController();

            savingsAccountsOverviewPane.getChildren().clear();
            savingsAccountsOverviewPane.getChildren().add(newLoadedPane);

            newLoadedPane.getChildren().get(0).setAccessibleText(n.name);
            System.out.println("Alpha" +newLoadedPane.getChildren().get(0));
            //get(0) is supposed to always be the one and only current pane!
            savingsAccountsOverviewPane.getChildren().get(0).setAccessibleText(n.name);
            c.initParameters();
            // To access account data



        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void addSavingsAccountsMainPage(){

        try {
            Pane newLoadedPane =    FXMLLoader.load(getClass().getResource("/view/addSavingsAccount.fxml"));
            basePane.getChildren().clear();
            basePane.getChildren().add(newLoadedPane);

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    public void AddCathegoriesWindow(){
        try {

            Pane newLoadedPane =    FXMLLoader.load(getClass().getResource("/view/editCategories.fxml"));

            basePane.getChildren().clear();
            basePane.getChildren().add(newLoadedPane);

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }

    @FXML
    public void initializeCharts(){
        if(notInitialised){
            /*
            set up categories and months for the charts
             */
            xAxis.setLabel("Monat");
            String[] categories = Main.rootService.categoryService.generateCategoryArray();
            Main.rootService.categoryService.initEarningChart(categorySeriesEarnings);

            xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(categories)));

            yAxis.setLabel("€");
            earningChart.setTitle("Einnahmen");

            /*
            for each month add each transaction to its series
             */

            earningChart.getData().addAll(categorySeriesEarnings.seriesList);

            //-----------------------------------------------------spendingChart------------------------------------------------------

            Main.rootService.categoryService.initSpendingChart(categorySeriesSpendings);

            xAxisSpending.setLabel("Monat");

            xAxisSpending.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(categories)));

            yAxisSpending.setLabel("€");

            /*
            for each month add each transaction to its series
             */

            spendingChart.getData().addAll(categorySeriesSpendings.seriesList);

            notInitialised = false;
        }
    }

    @FXML
    public void refreshMainTab(){
        for(SavingsAccount s : Main.rootService.accountList){
            System.out.println("New Accccc");
            addSavingsAccountPane(s);
        }
        addAccountAdder();

        /*
        mainAccountBalance.setText(Main.rootService.accountList.getFirst().getBalanceString());
        currentMonthBalance.setText(Main.rootService.transactionService.calculateMonthlyBalanceString(LocalDate.now()));

         */
    }

    @FXML
    public void addSavingsAccountPane(SavingsAccount n){
        System.out.println(n.name + " neuer Account");
        GridPane g = new GridPane();
        g.getColumnConstraints().add(new ColumnConstraints(140)); // column 0 is 100 wide
        g.getColumnConstraints().add(new ColumnConstraints(155)); // column 1 is 200 wide
        g.getRowConstraints().add(new RowConstraints(57));
        g.getRowConstraints().add(new RowConstraints(73));
        g.setStyle("-fx-background-color: #C4C4CC;");
        g.setGridLinesVisible(true);
        g.setAccessibleText(n.name);
        Label l = new Label(n.name);
        Label m = new Label(n.getBalanceString());
        m.textProperty().bind(n.balanceObserve);
        n.balanceObserve.setValue(n.getBalanceString());
        Label t = new Label("");
        Button b = new Button("Ansicht");
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override public void handle(MouseEvent e) {
                                    addSavingsAccountOverview(n);
                                }
                            });
        g.addRow(0,l,m);
        g.addRow(1,t,b);
        System.out.println("f");
        savingsAccountsTilePane.getChildren().add(g);
        savingsScrollPane.setFitToHeight(true);
    }

    @FXML
    public void addAccountAdder(){
        Button b = new Button("Neues Sparkonto erstellen");
        b.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                System.out.println("Halloween");
                addSavingsAccountCreationWindow();
            }
        });

        savingsAccountsTilePane.getChildren().add(b);
        savingsScrollPane.setFitToHeight(true);
    }

    @FXML
    public void addSavingsAccountCreationWindow(){
        try {
            Pane newLoadedPane =    FXMLLoader.load(getClass().getResource("/view/addSavingsAccount.fxml"));

            savingsAccountsOverviewPane.getChildren().clear();
            savingsAccountsOverviewPane.getChildren().add(newLoadedPane);

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    //general method for opening new window
    @FXML
    public void handleButtonClick (){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addTransaction.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }

    @FXML
    private Button btnShowTransactionHistory;

    @FXML
    public void showTransactionHistory(){
        try {
            Pane newLoadedPane =    FXMLLoader.load(getClass().getResource("/view/transactionHistory.fxml"));
            basePane.getChildren().clear();
            basePane.getChildren().add(newLoadedPane);

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }
}

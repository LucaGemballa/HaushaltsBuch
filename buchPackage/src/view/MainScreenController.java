package view;

import entity.CategorySeries;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.collections.FXCollections;
import java.util.Arrays;


public class MainScreenController {

    //public static LinkedList<moneyAction> transferList = new LinkedList<>();

    @FXML
    private Button btnEarnedThis;

    @FXML
    private Button btnSpending;

    @FXML
    private Button editCathegories;


    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    public static CategorySeries categorySeries = new CategorySeries();

    @FXML
    private StackedBarChart<String,Number> earningChart =
            new StackedBarChart<String,Number>(xAxis,yAxis);

    static XYChart.Series<String,Number> series1 =
            new XYChart.Series<String,Number>();
    public Boolean notInitialised = true;

    @FXML
    void display(){
        System.out.println("666");
    }

    @FXML
    public void AddTransactionWindow (){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addTransaction.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */

            Scene scene = new Scene(fxmlLoader.load(), 700, 720);
            Stage stage = new Stage();
            stage.setTitle("Einnahme hinzufügen");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }

    @FXML
    public void AddCathegoriesWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("editCategories.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            Stage stage = new Stage();
            stage.setTitle("Kathegoriemanager");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }

    @FXML
    public  void initializeCharts(){
        if(notInitialised){
            /*
            set up categories and months for the charts
             */
            xAxis.setLabel("Monat");
            String[] categories = Main.rootService.categoryService.generateCategoryArray();
            Main.rootService.categoryService.initCategorySeries(categorySeries);

            xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(categories)));

            yAxis.setLabel("€");
            earningChart.setTitle("Einnahmen");

            /*
            for each month add each transaction to its series
             */

            earningChart.getData().addAll(categorySeries.seriesList);
            notInitialised = false;
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
    //@Override
    //public void initialize(URL url, ResourceBundle rb){
    //nope
    //}
}

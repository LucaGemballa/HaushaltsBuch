package view;

import entity.CategorySeries;
import entity.SavingsAccount;
import entity.Transaction;
import entity.TransactionWeight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
//import java.awt.geom.Rectangle2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;

import java.time.LocalDate;
import java.util.LinkedList;

import java.io.File;
import java.util.Scanner;

import java.nio.file.*;

import service.*;

public class Main extends Application{

    public static String printToData ="";
    public static String printToDataCats ="";
    public static String printToDataAccs ="";

    // global paths for data access
    public static final String categoryPath =  "C:\\Users\\lucag\\Documents\\HaushaltsbuchDaten\\kathegorienListe.txt";
    public static final String transactionPath = "C:\\Users\\lucag\\Documents\\HaushaltsbuchDaten\\transaktionsListe.txt";
    public static final String accountPath = "C:\\Users\\lucag\\Documents\\HaushaltsbuchDaten\\kontenListe.txt";


    public static RootService rootService = new RootService();
    //public static CategorySeries categorySeries = new CategorySeries();


    public static LinkedList<String> categoryList = new LinkedList<String>();
    public static int transactionCount = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Einlesen alter Transaktionen
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        Scanner scan = null;
        try{
            scan = new Scanner(new File(transactionPath));

        }
        catch (Exception e){
        }
        int newID = 0;
        if(scan.hasNext()){
            newID = rootService.transactionList.transactionIDCounter++ ;
            //right now split string by spaces, will change when using CSV
            String[] newStr = scan.nextLine().split("\\s+");
            for (int i = 0; i < newStr.length; i++) {
                System.out.println(newStr[i]);
                printToData = printToData + newStr[i] + " ";
            }
            printToData = printToData + "\n";
            //printToData = scan.nextLine().split("\\s+");
            if(Float.parseFloat(newStr[0]) > 0) {
                if(!(newStr.length < 6)){
                    rootService.transactionList.add(new Transaction(Float.parseFloat(newStr[0]), newStr[1], LocalDate.parse(newStr[2]),
                            newStr[3],newStr[4], TransactionWeight.EINNAHME,newStr[5], newID));
                }
                else{
                    rootService.transactionList.add(new Transaction(Float.parseFloat(newStr[0]), newStr[1], LocalDate.parse(newStr[2]),
                            newStr[3],newStr[4], TransactionWeight.EINNAHME,"", newID));
                }
            }
            else {
                if(!(newStr.length < 6)){
                    rootService.transactionList.add(new Transaction(Float.parseFloat(newStr[0]), newStr[1], LocalDate.parse(newStr[2]),
                            newStr[3],newStr[4], TransactionWeight.UNWICHTIG,newStr[5], newID));
                }
                else{
                    rootService.transactionList.add(new Transaction(Float.parseFloat(newStr[0]), newStr[1], LocalDate.parse(newStr[2]),
                            newStr[3],newStr[4], TransactionWeight.UNWICHTIG,"", newID));
                }
            }
            transactionCount++;
        }

        while (scan.hasNext()){
            newID = rootService.transactionList.transactionIDCounter++ ;
            //Text an Leerzeichen splitten
            String[] newStr = scan.nextLine().split("\\s+");
            for (int i = 0; i < newStr.length; i++) {
                System.out.println(newStr[i]);
                printToData = printToData + newStr[i] + " " ;
            }
            printToData = printToData + "\n";
            String description = "";
            //Fall, wenn es eine Beschreibung gibt
            if(!(newStr.length < 6)){

                for (int i = 5; i < newStr.length; i++) {
                    description = description + " " + newStr[i];
                }
            }

            /*
            adapt this part for new Transaction attributes
             */

            if(Float.parseFloat(newStr[0]) > 0) {
                rootService.transactionList.add(new Transaction(Float.parseFloat(newStr[0]), newStr[1], LocalDate.parse(newStr[2]),
                        newStr[3],newStr[4], TransactionWeight.EINNAHME,description, newID));


            }
            else {
                rootService.transactionList.add(new Transaction(Float.parseFloat(newStr[0]), newStr[1], LocalDate.parse(newStr[2]),
                        newStr[3],newStr[4], TransactionWeight.UNWICHTIG,description, newID));
            }

            transactionCount++;
        }

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


        // Einlesen alter Kategorien
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        Scanner scanCathegories = null;
        try{
            scanCathegories = new Scanner(new File(categoryPath));

        }
        catch (Exception e){
        }

        if(scanCathegories.hasNext()){
            String[] newStr2 = scanCathegories.nextLine().split("\\s+");
            for (int i = 0; i < newStr2.length; i++) {
                System.out.println(newStr2[i]);
                categoryList.add(newStr2[i]);
                printToDataCats = printToDataCats + newStr2[i];

            }
            //printToData = scan.nextLine().split("\\s+");
        }

        while (scanCathegories.hasNext()){
            //Text an Leerzeichen splitten
            String[] newStr2 = scanCathegories.nextLine().split("\\s+");
            for (int i = 0; i < newStr2.length; i++) {
                System.out.println(newStr2[i]);
                categoryList.add(newStr2[i]);
                printToDataCats = printToDataCats + "\n" + newStr2[i];
            }
        }

        //rootService.categoryService.initCategorySeries(categorySeries);
        String[] categories = rootService.categoryService.generateCategoryArray();

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // Einlesen der Kontostände
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        Scanner scanAccounts = null;
        try{
            scanAccounts = new Scanner(new File(accountPath));
        }
        catch (Exception e){
        }
        int numberOfAccounts = 0;
        if(scanAccounts.hasNext()){
            String[] newStr3 = scanAccounts.nextLine().split("\\s+");
            for (int i = 0; i < newStr3.length; i++) {
                System.out.println(newStr3[i]);
                printToDataAccs = printToDataAccs + newStr3[i] + " ";
            }
            printToDataAccs = printToDataAccs + "\n";

            rootService.accountList.add(new SavingsAccount(newStr3[0], Float.parseFloat(newStr3[1]), rootService.savingsAccountService.numberOfAccounts++));

        }

        while (scanAccounts.hasNext()){
            //Text an Leerzeichen splitten
            String[] newStr3 = scanAccounts.nextLine().split("\\s+");
            for (int i = 0; i < newStr3.length; i++) {
                System.out.println(newStr3[i]);
                printToDataAccs = printToDataAccs + newStr3[i] + " " ;
            }
            printToDataAccs = printToDataAccs + "\n";

            rootService.accountList.add(new SavingsAccount(newStr3[0], Float.parseFloat(newStr3[1]), rootService.savingsAccountService.numberOfAccounts++));
        }

        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------


        //Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
        Parent root = loader.load();

        MainScreenController c = loader.getController();

        //c.refreshMainTab();
        rootService.savingsAccountService.setMainController(c);


        primaryStage.setTitle("Haushaltsbuch Assistent");
        primaryStage.setScene(new Scene(root, 1920, 1080));


        // maximise window while showing taskbar
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setMaximized(true);
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        //check if still counts as maximised
        System.out.println(primaryStage.isMaximized());
        //primaryStage.setResizable(false);


        primaryStage.show();
        /*
        In the future, add parameters to addsavingsaccs
         */
        for(SavingsAccount s : rootService.accountList){
            System.out.println("New Accccc");
            c.addSavingsAccountPane(s);
        }

        //Add button for creating another savings account
        c.addAccountAdder();
    }

    public static void main (String args[]){
        //System.out.println("hello mfer");
        //LinkedList<moneyAction> transferList = new LinkedList<>();
        System.out.println("hallo");

        launch(args);
    }
}
package view;

import entity.Transaction;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
//import java.awt.geom.Rectangle2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;
import java.util.LinkedList;

import java.io.File;
import java.util.Scanner;

import service.*;

public class Main extends Application{

    public static String printToData ="";
    public static String printToDataCats ="";


    public static RootService rootService = new RootService();


    public static LinkedList<String> cathegoryList = new LinkedList<>();
    public static int transactionCount = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Einlesen alter Transaktionen
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        Scanner scan = null;
        try{
            scan = new Scanner(new File("C:\\Users\\Luca\\Desktop\\HaushaltsbuchDaten\\transaktionsListe.txt"));

        }
        catch (Exception e){
        }

        if(scan.hasNext()){
            String[] newStr = scan.nextLine().split("\\s+");
            for (int i = 0; i < newStr.length; i++) {
                System.out.println(newStr[i]);
                printToData = printToData + newStr[i];

            }
            //printToData = scan.nextLine().split("\\s+");
        }

        while (scan.hasNext()){
            //Text an Leerzeichen splitten
            String[] newStr = scan.nextLine().split("\\s+");
            for (int i = 0; i < newStr.length; i++) {
                System.out.println(newStr[i]);
                printToData = printToData + "\n" + newStr[i];
            }
            rootService.transactionList.add(new Transaction(Float.parseFloat(newStr[0]),"",""));

            transactionCount++;
        }

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


        // Einlesen alter Kathegorien
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        Scanner scanCathegories = null;
        try{
            scanCathegories = new Scanner(new File("C:\\Users\\Luca\\Desktop\\HaushaltsbuchDaten\\kathegorienListe.txt"));

        }
        catch (Exception e){
        }

        if(scanCathegories.hasNext()){
            String[] newStr2 = scanCathegories.nextLine().split("\\s+");
            for (int i = 0; i < newStr2.length; i++) {
                System.out.println(newStr2[i]);
                cathegoryList.add(newStr2[i]);
                printToDataCats = printToDataCats + newStr2[i];

            }
            //printToData = scan.nextLine().split("\\s+");
        }

        while (scanCathegories.hasNext()){
            //Text an Leerzeichen splitten
            String[] newStr2 = scanCathegories.nextLine().split("\\s+");
            for (int i = 0; i < newStr2.length; i++) {
                System.out.println(newStr2[i]);
                cathegoryList.add(newStr2[i]);
                printToDataCats = printToDataCats + "\n" + newStr2[i];

            }
        }
        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



        Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
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

    }

    public static void main (String args[]){
        //System.out.println("hello mfer");
        //LinkedList<moneyAction> transferList = new LinkedList<>();
        System.out.println("hallo");

        launch(args);


    }
}
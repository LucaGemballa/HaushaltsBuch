package buchAnwendung;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
//import java.awt.geom.Rectangle2D;
import javafx.geometry.Rectangle2D;
import java.lang.Object;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;
import java.util.LinkedList;
import reportedActions.moneyAction;
import reportedActions.addingAction;
import reportedActions.spendingAction;

import java.nio.file.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application{

    public static String printToData ="";
    public static LinkedList<moneyAction> transferList = new LinkedList<>();
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
            if (Float.parseFloat(newStr[0]) >= 0){
                transferList.add(new addingAction(Float.parseFloat(newStr[0]),"",""));
            }
            else{
                transferList.add(new spendingAction(Float.parseFloat(newStr[0]),"",""));
            }

            transactionCount++;
        }

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
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
        LinkedList<moneyAction> transferList = new LinkedList<>();
        System.out.println("hallo");

        launch(args);


    }
}

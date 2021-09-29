package buchAnwendung;

import java.lang.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

import reportedActions.moneyAction;
import reportedActions.*;
import java.util.LinkedList;
import java.nio.file.*;

public class AddEarningsController {

    //vorläufige Auswahl an Kathegorien:
    public boolean cathHasShown = false;
    private String cath1= "Unterhalt";
    private String cath2= "Taschengeld";
    private String cath3= "Dividende";

    @FXML
    public ChoiceBox<String> boxCathegory;

    @FXML
    private Button btnConfirmAction;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private TextField fldSum;

    public AddEarningsController(){
        System.out.println("44");

        //boxCathegory.getItems().add(cath1);
        //boxCathegory.getItems().add(cath2);
        //boxCathegory.getItems().add(cath3);

    }

    @FXML
    public void confirmAction(){
        try{
            LinkedList<moneyAction> list1 = new LinkedList<moneyAction>();

            //zugefügte Summe auslesen
            Float transSum = Float.parseFloat(fldSum.getText());
            System.out.println(transSum);

            // Kathegorie der Transaktion auslesen


            //Beschreibung auslesen


            //
            // Einlesen der Daten in Liste und Datei
            //


            System.out.println("Eingabeprozess");
            reportedActions.addingAction.registerAction(transSum,"","",Main.transferList);
            Main.printToData = Main.printToData + "\n" +  reportedActions.addingAction.actionToText(Main.transactionCount++,Main.transferList);
            //transactionCount++;
            System.out.println(Main.printToData);

            Path p = Path.of("C:\\Users\\Luca\\Desktop\\HaushaltsbuchDaten\\transaktionsListe.txt");
            try{
                Path filePath = Files.writeString(p,Main.printToData);

            }
            catch (Exception e){

            }
        }
        catch(Exception e){
            System.out.println("Ein Wert wurde in ungültiger Weise eingegeben !");

        }
    }

    @FXML
    public void revealCathegories(){
        System.out.println("1");
        System.out.println(cathHasShown);
        if(cathHasShown == false){
            System.out.println("1");
            boxCathegory.getItems().add(cath1);
            boxCathegory.getItems().add(cath2);
            boxCathegory.getItems().add(cath3);
            cathHasShown= true;
        }
        System.out.println("2");

    }

}

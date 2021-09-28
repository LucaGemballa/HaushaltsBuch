package buchAnwendung;

import java.lang.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import reportedActions.moneyAction;
import reportedActions.*;
import java.util.LinkedList;
import java.nio.file.*;

public class AddEarningsController {
    private String printToData = "";
    private int transactionCount = 0;

    @FXML
    private Button btnConfirmAction;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private TextField fldSum;

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
            reportedActions.addingAction.registerAction(transSum,"","",Controller.transferList);
            printToData = printToData + "\n " +  reportedActions.addingAction.actionToText(transactionCount++,Controller.transferList);
            //transactionCount++;
            System.out.println(printToData);

            Path p = Path.of("C:\\Users\\Luca\\Desktop\\HaushaltsbuchDaten\\transaktionsListe.txt");
            try{
                Path filePath = Files.writeString(p,printToData);

            }
            catch (Exception e){

            }


        }
        catch(Exception e){
            System.out.println("Ein Wert wurde in ungültiger Weise eingegeben !");

        }
    }

}

package view;

import java.lang.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.util.*;

import javafx.scene.control.*;
import java.time.*;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.nio.file.*;

public class AddTransactionController {

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
    private DatePicker dateInput;

    @FXML
    private TextField fldSum;

    public AddTransactionController(){
        System.out.println("44");

        //boxCathegory.getItems().add(cath1);
        //boxCathegory.getItems().add(cath2);
        //boxCathegory.getItems().add(cath3);

    }

    @FXML
    public void confirmAction(){
        try{
            //LinkedList<moneyAction> list1 = new LinkedList<moneyAction>();

            //zugefügte Summe auslesen
            Float transSum = Float.parseFloat(fldSum.getText());
            System.out.println(transSum);

            // Kathegorie der Transaktion auslesen

            String transCathegory = boxCathegory.getValue();


            //Beschreibung auslesen

            //Datum auslesen

            LocalDate transDate = dateInput.getValue();


            //
            // Einlesen der Daten in Liste und Datei
            //


            System.out.println("Eingabeprozess");
            view.Main.rootService.transactionService.registerTransaction(transSum,transCathegory,transDate,"");
            //view.Main.printToData = view.Main.printToData + "\n" +  reportedActions.addingAction.actionToText(view.Main.transactionCount++, view.Main.transferList);

            System.out.println(view.Main.printToData);
            System.out.println("first order");

            view.Main.printToData = view.Main.printToData + Main.rootService.transactionList.list.get(Main.transactionCount++).toText() + "\n";
            //transactionCount++;
            System.out.println(view.Main.printToData);

            Path p = Path.of("C:\\Users\\Luca\\Desktop\\HaushaltsbuchDaten\\transaktionsListe.txt");
            try{
                Path filePath = Files.writeString(p, view.Main.printToData);

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
            ListIterator<String> catIterator = Main.cathegoryList.listIterator(0);
            while (catIterator.hasNext()){
                String Test = catIterator.next();
                boxCathegory.getItems().add(Test);
            }

            /*
            boxCathegory.getItems().add(cath1);
            boxCathegory.getItems().add(cath2);
            boxCathegory.getItems().add(cath3);

             */
            cathHasShown= true;
        }
        System.out.println("2");

    }

}


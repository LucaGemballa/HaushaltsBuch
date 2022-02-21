package view;

import java.lang.*;

import entity.TransactionWeight;
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
    public  ChoiceBox<String> boxAccount;

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

            fldSum.setText("");
            System.out.println(transSum);

            // Kathegorie der Transaktion auslesen

            String transCathegory = boxCathegory.getValue();
            boxCathegory.setValue(null);


            //Beschreibung auslesen

            //Datum auslesen

            LocalDate transDate = dateInput.getValue();
            dateInput.setValue(null);


            //
            // Einlesen der Daten in Liste und Datei
            //


            view.Main.rootService.transactionService.registerTransaction(transSum,transCathegory,transDate,"", TransactionWeight.EINNAHME,"");

            view.Main.printToData = view.Main.printToData + Main.rootService.transactionList.list.get(Main.transactionCount++).toText() + "\n";

            Path p = Path.of("C:\\Users\\Luca\\Desktop\\HaushaltsbuchDaten\\transaktionsListe.txt");
            try{
                Path filePath = Files.writeString(p, view.Main.printToData);

            }
            catch (Exception e){

            }

            //Aktualisieren des Kontostands

            Main.rootService.accountList.getFirst().bookTransaction(transSum);

            Path c = Path.of("C:\\Users\\Luca\\Desktop\\HaushaltsbuchDaten\\kontenListe.txt");
            try{
                Path filePath = Files.writeString(c, Main.rootService.savingsAccountService.accountListToText());

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


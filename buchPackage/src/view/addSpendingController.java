package view;

import java.lang.*;

import entity.Transaction;
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

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class addSpendingController {

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
    private CheckBox splitSpending;

    @FXML
    private TextField monthsToSplit;

    @FXML
    private TextField monthsToSplitRythm;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private DatePicker dateInput;



    @FXML
    private TextField fldSum;

    public addSpendingController(){
        System.out.println("44");

        //boxCathegory.getItems().add(cath1);
        //boxCathegory.getItems().add(cath2);
        //boxCathegory.getItems().add(cath3);

    }

    @FXML
    public void confirmAction(){
        try{
            final DecimalFormat df = new DecimalFormat("0.00");
            LinkedList<Transaction> listForTransactions = new LinkedList<Transaction>();
            //LinkedList<moneyAction> list1 = new LinkedList<moneyAction>();

            //zugefügte Summe auslesen
            Float transSum = Float.parseFloat(fldSum.getText()) * (-1);
            System.out.println(transSum);
            fldSum.setText("");
            // Kathegorie der Transaktion auslesen

            String transCathegory = boxCathegory.getValue();
            boxCathegory.setValue(null);


            //Beschreibung auslesen

            //Datum auslesen

            LocalDate transDate = dateInput.getValue();
            dateInput.setValue(null);

            //Teilung der Ausgabe kontrollieren und vornehmen
            //Boolean split = splitSpending.isSelected();

            System.out.println(transSum + " " + transCathegory + " " + transDate + " ");

            int nrOfTransactions = 1;
            if(splitSpending.isSelected()){
                int splitForXMonths = Integer.parseInt(monthsToSplit.getCharacters().toString());
                int splitRythm = Integer.parseInt(monthsToSplitRythm.getCharacters().toString());

                assert(splitForXMonths % splitRythm == 0);
                nrOfTransactions = splitForXMonths /splitRythm;
                System.out.println("Konvertierung beginnt");

                float singleTransactionSum = transSum / nrOfTransactions;
                singleTransactionSum = Main.rootService.transactionService.round2(singleTransactionSum,2) ;
                System.out.println("Konvertierung erfolgreich");

                for(int i=0;i<nrOfTransactions;i++){
                    listForTransactions.add(new Transaction(singleTransactionSum,transCathegory,transDate,"", TransactionWeight.UNWICHTIG,""));
                    transDate = transDate.plusMonths(splitRythm);
                }
            }
            else{
                System.out.println("Konvertierung beginnt");
                transSum = Main.rootService.transactionService.round2(transSum,2);
                System.out.println("Konvertierung erfolgreich");
                listForTransactions.add(new Transaction(transSum,transCathegory,transDate,"", TransactionWeight.UNWICHTIG,""));
            }


            //
            // Einlesen der Daten in Liste und Datei
            //
            for(Transaction t : listForTransactions){
                view.Main.rootService.transactionService.registerTransaction(t);
                //view.Main.printToData = view.Main.printToData + "\n" +  reportedActions.addingAction.actionToText(view.Main.transactionCount++, view.Main.transferList);

                System.out.println(view.Main.printToData);


                view.Main.printToData = view.Main.printToData + t.toText() + "\n";
                //transactionCount++;
                System.out.println(view.Main.printToData);

                System.out.println("Es wurden" + nrOfTransactions + "Transaktionen gebucht" );

                Path p = Path.of(Main.transactionPath);
                try{
                    Path filePath = Files.writeString(p, view.Main.printToData);
                }
                catch (Exception e){
                }
            }

            //Aktualisieren des Kontostands
            Main.rootService.accountList.getFirst().bookTransaction(transSum);

            Path c = Path.of(Main.accountPath);
            try{
                Path filePath = Files.writeString(c, Main.rootService.savingsAccountService.accountListToText());
            }
            catch (Exception e){
            }
        }
        catch(Exception e){
            System.out.println(e);
            System.out.println("Ein Wert wurde in ungültiger Weise eingegeben !");

        }
    }

    @FXML
    public void revealCathegories(){
        System.out.println("1");
        System.out.println(cathHasShown);
        if(cathHasShown == false){
            System.out.println("1");
            ListIterator<String> catIterator = Main.categoryList.listIterator(0);
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

package view;

import java.lang.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import view.*;

import java.nio.file.*;

public class EditCategoriesController {

    @FXML
    private Button btnAddCathegory;

    @FXML
    private Button btnRemoveCathegory;

    @FXML
    private TextField cathegoryToBeAdded;

    @FXML
    public void addCathegory(){
        String cat = cathegoryToBeAdded.getText();
        view.Main.cathegoryList.add(cat);
        System.out.println(cat);
        cathegoryToBeAdded.setText("");

        view.Main.printToDataCats= Main.printToDataCats + "\n" + cat;

        Path p = Path.of("C:\\Users\\Luca\\Desktop\\HaushaltsbuchDaten\\kathegorienListe.txt");
        try{
            Path filePath = Files.writeString(p, Main.printToDataCats);

        }
        catch (Exception e){

        }
    }
}
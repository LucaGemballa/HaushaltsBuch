package view;

import java.lang.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.*;

import javafx.scene.control.TextField;

import java.nio.file.*;
import java.util.Collections;

public class EditCategoriesController {


    @FXML
    private Button btnAddCathegory;

    @FXML
    private Button btnRemoveCathegory;

    @FXML
    private TextField categoryToBeAdded;

    @FXML
    public ListView<String> categoryListView;

    @FXML
    public void initialize(){
        for (String ctg : Main.categoryList){
            categoryListView.getItems().add(ctg);
        }
    }

    @FXML
    public void addCathegory(){

        String cat = categoryToBeAdded.getText();

        if(!cat.isBlank()){
            cat = cat.trim();
            view.Main.categoryList.add(cat);
            System.out.println(cat);
            categoryToBeAdded.setText("");

            view.Main.printToDataCats= Main.printToDataCats + "\n" + cat;
            System.out.println(Main.printToDataCats);

            /*
            add new item to the ListView
             */
            categoryListView.getItems().add(cat);
            Collections.sort(categoryListView.getItems());
            categoryListView.scrollTo(categoryListView.getItems().size() - 1);
            categoryListView.layout();
            categoryListView.edit(categoryListView.getItems().size() - 1);

            Path p = Path.of(view.Main.categoryPath);
            try{
                Path filePath = Files.writeString(p, Main.rootService.categoryService.categoryListToText());
            }
            catch (Exception e){
            }
        }
    }

    @FXML
    public void removeCategory(){
        String toDelete = categoryListView.getSelectionModel().getSelectedItem();

        Main.categoryList.remove(toDelete);

        categoryListView.getItems().remove(toDelete);
        Collections.sort(categoryListView.getItems());
        categoryListView.scrollTo(categoryListView.getItems().size() - 1);
        categoryListView.layout();
        categoryListView.edit(categoryListView.getItems().size() - 1);

        Path p = Path.of(view.Main.categoryPath);
        try{
            Path filePath = Files.writeString(p, Main.rootService.categoryService.categoryListToText());
        }
        catch (Exception e){
        }
    }
}
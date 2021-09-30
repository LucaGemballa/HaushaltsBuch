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

public class EditCathegoriesController {

    @FXML
    private Button btnAddCathegory;

    @FXML
    private Button btnRemoveCathegory;

    @FXML
    private TextField cathegoryToBeAdded;

    @FXML
    public void addCathegory(){
        String cat = cathegoryToBeAdded.getText();
        Main.cathegoryList.add(cat);
        System.out.println(cat);
        cathegoryToBeAdded.setText("");
    }
}

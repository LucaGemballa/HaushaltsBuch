package view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteAccountRequestController {

    @FXML
    public Button acceptDeletionBtn;

    @FXML
    private Button rejectDeletionBtn;

    private ModuleLayer.Controller parentController;

    public BooleanProperty ret = new SimpleBooleanProperty(false);

    public boolean retVal;

    @FXML
    public void initialize(){
        retVal = false;
        ret.setValue(false);
    }

    @FXML
    private void setToAcceptDeletion(){
        retVal = true;
        ret.setValue(true);
        System.out.println("Closing");

        Stage stage = (Stage) acceptDeletionBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void setToRejectDeletion(){
        retVal = false;
        Stage stage = (Stage) acceptDeletionBtn.getScene().getWindow();
        stage.close();
    }


}

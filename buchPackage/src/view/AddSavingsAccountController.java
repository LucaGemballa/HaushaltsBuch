package view;

import entity.SavingsAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.nio.file.Files;
import java.nio.file.Path;

public class AddSavingsAccountController {

    @FXML
    private Button addNewAccountBtn;
    @FXML
    private TextField newAccountNameTxtFld;

    @FXML
    public void addNewAccount(){
        String accountName = newAccountNameTxtFld.getText();
        newAccountNameTxtFld.clear();

        SavingsAccount newAcc = new SavingsAccount(accountName,0.0f,Main.rootService.savingsAccountService.numberOfAccounts++);
        Main.rootService.accountList.add(newAcc);

        Path c = Path.of(Main.accountPath);
        try{
            Path filePath = Files.writeString(c, Main.rootService.savingsAccountService.accountListToText());
        }
        catch (Exception e){
        }
    }


}

package buchAnwendung;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
//import java.awt.geom.Rectangle2D;
import javafx.geometry.Rectangle2D;
import java.lang.Object;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;
import java.util.LinkedList;
import reportedActions.moneyAction;
import reportedActions.addingAction;
import reportedActions.spendingAction;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Haushaltsbuch Assistent");
        primaryStage.setScene(new Scene(root, 1920, 1080));


        // maximise window while showing taskbar
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setMaximized(true);
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        //check if still counts as maximised
        System.out.println(primaryStage.isMaximized());
        //primaryStage.setResizable(false);


        primaryStage.show();


    }

    public static void main (String args[]){
        //System.out.println("hello mfer");
        LinkedList<moneyAction> transferList = new LinkedList<>();
        System.out.println("hallo");

        launch(args);


    }
}

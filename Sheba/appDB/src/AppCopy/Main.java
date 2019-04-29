package AppCopy;

import FXML.GiveController;
import FXML.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setTitle("DB");
        primaryStage.show();

        MainController.stage = primaryStage;
    }

    public void openGiveMenu(Stage stage) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Give.fxml"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

            GiveController.stage = stage;
        } catch (Exception e){
            System.out.println("Error at openGiveMenu");
            System.out.println(e.getMessage());
        }
    }
}

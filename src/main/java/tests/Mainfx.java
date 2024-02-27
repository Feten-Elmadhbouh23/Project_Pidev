package tests;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mainfx extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/Homepage.fxml"));
        Parent root = loader.load();
        Scene scene =new Scene(root);
        stage.setTitle("interface");
        stage.setScene(scene);
        stage.show();
    }
    public static void Main(String [] args){
        launch(args);
    }

}


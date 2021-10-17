package berkan.bt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("App.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Zone Counter");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
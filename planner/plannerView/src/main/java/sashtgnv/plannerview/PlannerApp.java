package sashtgnv.plannerview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import sashtgnv.plannerLogic.database.DBConnection;

import java.io.IOException;

public class PlannerApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PlannerApp.class.getResource("fxml/planner_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        Pane pane = new Pane();
//        Scene scene = new Scene(pane,Color.web("#7ADBCB"));
        stage.setTitle("planner!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("notebook.png"));
        stage.show();
    }

    public static void main(String[] args) {
        DBConnection.getInstance().deleteOldNotes();
        launch();
    }
}
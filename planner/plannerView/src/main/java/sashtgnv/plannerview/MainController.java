package sashtgnv.plannerview;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sashtgnv.plannerLogic.dates.DateMethods;

import java.io.IOException;
import java.util.Calendar;

public class MainController extends Control {
    @FXML protected Circle forwardCrcl;
    @FXML protected Circle backwardCrcl;
    @FXML protected Label monthYearLabel;
    @FXML protected GridPane center;
    @FXML protected CenterController centerController;
    @FXML protected Button noteBtn;
    private Stage stage;


    @FXML
    protected void crclBtnExited(Event e) {
        Object btn = e.getSource();
        ((Circle) btn).setFill(Color.web("#b5dbff"));
    }

    @FXML
    protected void crclBtnEntered(Event e) {
        Object btn = e.getSource();
        ((Circle) btn).setFill(Color.web("#4aa7ff"));
    }

    @FXML
    protected void crcrlBtnForwardClick(){
        DateMethods.CURRENT_DATE.add(Calendar.MONTH,1);
        initialize();
        centerController.unfocusLabel();
        centerController.initialize();
    }

    @FXML
    protected void crcrlBtnBackwardClick(){
        if (DateMethods.CURRENT_DATE.get(Calendar.YEAR)>DateMethods.DEFAULT_DATE.get(Calendar.YEAR)
        || DateMethods.CURRENT_DATE.get(Calendar.YEAR)==DateMethods.DEFAULT_DATE.get(Calendar.YEAR)
        &&(DateMethods.CURRENT_DATE.get(Calendar.MONTH)>DateMethods.DEFAULT_DATE.get(Calendar.MONTH))){
            DateMethods.CURRENT_DATE.add(Calendar.MONTH,-1);
            initialize();
            centerController.unfocusLabel();
            centerController.initialize();
        }
    }

    @FXML
    protected void rootClickHandler(){
        if (centerController.focusedLabel!=null && (stage==null||!stage.isShowing())){
            noteBtn.setDisable(false);
        }
        else {
            noteBtn.setDisable(true);
        }
    }

    @FXML
    protected void noteBtnClickHandler(){
        noteBtn.setDisable(true);
        stage = new Stage();
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/note.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(loader.getRoot()));
        stage.getIcons().add(new Image("notebook.png"));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    protected void initialize(){
        monthYearLabel.setText(DateMethods.getMonthYear());
    }
}
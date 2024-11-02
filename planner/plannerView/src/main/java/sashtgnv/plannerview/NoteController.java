package sashtgnv.plannerview;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sashtgnv.plannerLogic.database.DBConnection;

public class NoteController {
    @FXML Button saveBtn;
    @FXML TextArea textArea;


    @FXML
    protected void saveBtnHandler(){
        DBConnection.getInstance().addNote(textArea.getText());
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.getOwner();
        stage.close();
    }

    @FXML
    protected void initialize(){
        textArea.setText(DBConnection.getInstance().getNote());
    }
}

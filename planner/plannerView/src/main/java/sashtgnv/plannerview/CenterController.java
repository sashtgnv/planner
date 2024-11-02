package sashtgnv.plannerview;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sashtgnv.plannerLogic.database.DBConnection;
import sashtgnv.plannerLogic.dates.DateMethods;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CenterController {

    @FXML protected Label label01;
    @FXML protected Label label02;
    @FXML protected Label label03;
    @FXML protected Label label04;
    @FXML protected Label label05;
    @FXML protected Label label06;

    @FXML protected Label label11;
    @FXML protected Label label12;
    @FXML protected Label label13;
    @FXML protected Label label14;
    @FXML protected Label label15;
    @FXML protected Label label16;

    @FXML protected Label label21;
    @FXML protected Label label22;
    @FXML protected Label label23;
    @FXML protected Label label24;
    @FXML protected Label label25;

    @FXML protected Label label31;
    @FXML protected Label label32;
    @FXML protected Label label33;
    @FXML protected Label label34;
    @FXML protected Label label35;

    @FXML protected Label label41;
    @FXML protected Label label42;
    @FXML protected Label label43;
    @FXML protected Label label44;
    @FXML protected Label label45;

    @FXML protected Label label51;
    @FXML protected Label label52;
    @FXML protected Label label53;
    @FXML protected Label label54;
    @FXML protected Label label55;

    @FXML protected Label label61;
    @FXML protected Label label62;
    @FXML protected Label label63;
    @FXML protected Label label64;
    @FXML protected Label label65;

    protected Label focusedLabel;

    protected Label[] labelsTable;

    @FXML
    protected void labMouseEntered(Event e){
        Label label = (Label) e.getSource();
        label.getStyleClass().add("direct");

    }

    @FXML
    protected void labMouseExited(Event e){
        Label label = (Label) e.getSource();

        label.getStyleClass().remove("direct");

    }

    @FXML
    protected void labClickHandler(Event e){
        Label label = (Label) e.getSource();
        if (!label.equals(focusedLabel)) {
            unfocusLabel();
            focusLabel(label);
        }
        else {
            unfocusLabel();
        }
    }

    @FXML
    protected void initialize(){
        labelsTable = new Label[]{
                label01,label11,label21,label31,label41,label51,label61,
                label02,label12,label22,label32,label42,label52,label62,
                label03,label13,label23,label33,label43,label53,label63,
                label04,label14,label24,label34,label44,label54,label64,
                label05,label15,label25,label35,label45,label55,label65,
                label06, label16
        };
        //очистка всей таблицы
        for (Label label : labelsTable) {
            label.getStyleClass().clear();
            label.setText("");
            label.setUnderline(false);
            label.setDisable(true);
        }
        int start = DateMethods.getFirstDowInMonth();
        int n = DateMethods.getDaysInMonth();
        var cal = (GregorianCalendar) DateMethods.CURRENT_DATE.clone();
        //изменение необходимых лейблов
        for (int i = 0; i<n; i++){
            cal.set(Calendar.DAY_OF_MONTH,i+1);
            labelsTable[start+i].setText(Integer.toString(i+1));
            labelsTable[start+i].setDisable(false);
            if (cal.equals(DateMethods.DEFAULT_DATE)){
                labelsTable[start+i].getStyleClass().add("today");
            }
            else {
                labelsTable[start+i].getStyleClass().add("default");
            }
            if (DBConnection.getInstance().getDates().contains(cal)){
                labelsTable[start+i].setUnderline(true);
            }
        }
    }

    private void focusLabel(Label label){
        label.getStyleClass().add("focused");
        focusedLabel = label;
        DateMethods.CURRENT_DATE.set(Calendar.DAY_OF_MONTH,Integer.parseInt(label.getText()));
    }

    protected void unfocusLabel(){
        if (focusedLabel!=null) {
            focusedLabel.getStyleClass().remove("focused");
            focusedLabel = null;
        }
    }
}

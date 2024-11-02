module sashtgnv.plannerview {
    requires javafx.controls;
    requires javafx.fxml;
    requires plannerLogic;


    opens sashtgnv.plannerview to javafx.fxml;
    exports sashtgnv.plannerview;
}
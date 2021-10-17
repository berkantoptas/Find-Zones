module berkan.bt {
    requires javafx.controls;
    requires javafx.fxml;


    opens berkan.bt to javafx.fxml;
    exports berkan.bt;
}
module pl.gatomek.life {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens pl.gatomek.life to javafx.fxml;
    exports pl.gatomek.life;
}
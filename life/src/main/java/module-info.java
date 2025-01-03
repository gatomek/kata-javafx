module pl.gatomek.life {
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires jdk.jshell;
    requires java.logging;

    opens pl.gatomek.life to javafx.fxml;
    exports pl.gatomek.life;
    exports pl.gatomek.life.indextree;
    opens pl.gatomek.life.indextree to javafx.fxml;
}
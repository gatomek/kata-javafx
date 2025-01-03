module pl.gatomek.graphs {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens pl.gatomek.graphs to javafx.fxml;
    exports pl.gatomek.graphs;
}
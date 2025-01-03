package pl.gatomek.graphs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphsApplication extends Application {
    @Override
    public void start(Stage stage) {
        Canvas canvas = new pl.gatomek.graphs.GraphsCanvas();

        var root = new VBox();
        root.getChildren().add( canvas);
        Scene scene = new Scene(root, 600, 600);

        stage.setTitle("Graphs");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
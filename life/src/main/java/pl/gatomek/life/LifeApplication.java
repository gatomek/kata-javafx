package pl.gatomek.life;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.StatusBar;

import static javafx.geometry.Pos.CENTER;

public class LifeApplication extends Application {

    private LifeBoard lifeBoard = new LifeBoard(20, 40);
    private LifeCanvas canvas = new LifeCanvas(lifeBoard);

    private EventHandler<ActionEvent> makeStepHandler = (ActionEvent evt) -> {
        Platform.runLater(() -> {
            lifeBoard.calcAliveCells();
            lifeBoard.calcAliveNeighbours();
            canvas.draw();
        });
        evt.consume();
    };

    private EventHandler<ActionEvent> diagnViewToggleHandler = (ActionEvent evt) -> {
        Platform.runLater(() -> canvas.draw());
        evt.consume();
    };

    private EventHandler<ActionEvent> clearLifeBoardHandler = (ActionEvent evt) -> {
        Platform.runLater(() -> {
            lifeBoard.clear();
            canvas.draw();
        });
        evt.consume();
    };

    private EventHandler<MouseEvent> onMouseClickOnCanvasHandler = (MouseEvent evt) -> {
        Platform.runLater(() -> {
            Cell cell = canvas.findCell(evt.getX(), evt.getY());
            if (cell != null) {
                cell.alive = !cell.alive;
                lifeBoard.updateNeighbours(cell);
                canvas.draw();
            }
        });
        evt.consume();
    };

    @Override
    public void start(Stage stage) {
        configCanvas();

        MenuBar menuBar = buildMenuBar();
        HBox toolBar = buildToolBar();
        StatusBar statusBar = new StatusBar();

        BorderPane borderPane = new BorderPane(canvas, menuBar, null, new VBox(toolBar, statusBar), null);
        Scene scene = new Scene(borderPane, 1000, 600);

        stage.setTitle("Life");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    private HBox buildToolBar() {
        Button newButton = new Button("Nowy");
        newButton.setOnAction(clearLifeBoardHandler);

        Button stepButton = new Button("Krok");
        stepButton.setOnAction(makeStepHandler);

        HBox hBox = new HBox(newButton, stepButton);
        hBox.setAlignment(CENTER);
        return hBox;
    }

    private MenuBar buildMenuBar() {
        Menu fileMenu = new Menu("Plik");
        MenuItem newMenuItem = new MenuItem("Nowy");
        newMenuItem.setOnAction(clearLifeBoardHandler);
        MenuItem closeMenuItem = new MenuItem("Zamknij");
        closeMenuItem.setOnAction(evt -> Platform.exit());

        fileMenu.getItems().addAll(newMenuItem, new SeparatorMenuItem(), closeMenuItem);

        Menu viewMenu = new Menu("Widok");

        CheckMenuItem diagnMenuItem = new CheckMenuItem("Diagnostyka");
        canvas.diagnView.bind( diagnMenuItem.selectedProperty());
        diagnMenuItem.setOnAction(diagnViewToggleHandler);
        viewMenu.getItems().add(diagnMenuItem);

        Menu helpMenu = new Menu("Pomoc");
        MenuItem aboutMenuItem = new MenuItem("O programie");
        helpMenu.getItems().add(aboutMenuItem);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, viewMenu, helpMenu);
        return menuBar;
    }

    private void configCanvas() {
        canvas.rows.bind(lifeBoard.rows);
        canvas.cols.bind(lifeBoard.cols);
        canvas.setOnMouseClicked(onMouseClickOnCanvasHandler);
    }

    public static void main(String[] args) {
        launch();
    }
}
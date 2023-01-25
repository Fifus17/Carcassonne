package gui;

import Classes.AbstractTile;
import Classes.Map;
import Classes.Vector2D;
import Enums.Infrastructure;
import Tiles.*;
import javafx.application.Application;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.effect.Glow;
import javafx.scene.Node;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class App extends Application {

    private AbstractTile currentTile;
    private GridPane mapGridPane = new GridPane();
    private Hyperlink[][] hyperlinks = new Hyperlink[31][31];
    private boolean[][] ifPlaced = new boolean[31][31];

    DraggableMaker draggableMaker = new DraggableMaker();

    public App() throws FileNotFoundException {
    }

    public void add(Node node, int i, int j) {
        StackPane stackPane = new StackPane(node);
        mapGridPane.add(stackPane, i, j);
    }

    public void nothing() {}

    @Override
    public void start(Stage primaryStage) throws Exception {
        Map map = new Map();
        this.currentTile = map.getRandomTile();

        for (int i=0; i<31; i++) {
            mapGridPane.getColumnConstraints().add(new ColumnConstraints(100));
            mapGridPane.getRowConstraints().add(new RowConstraints(100));
        }

        for (int i=0; i<31; i++) {
            for (int j=0; j<31; j++) {
//                ifPlaced[i][j] = false;
                hyperlinks[i][j] = new Hyperlink();
                hyperlinks[i][j].setMinSize(100, 100);
                hyperlinks[i][j].setMaxSize(100, 100);
                int finalI = i;
                int finalJ = j;
                hyperlinks[i][j].setOnMouseEntered(mouseEvent -> {
                    if (!ifPlaced[finalI][finalJ]) {
                        StackPane stackPane = new StackPane(currentTile.getImageView(), hyperlinks[finalI][finalJ]);
                        hyperlinks[finalI][finalJ].setOnMouseClicked(mouseEvent3 -> {
                            if (map.canBePlaced(currentTile, finalI, finalJ)) {
                                mapGridPane.add(new StackPane(currentTile.getImageView()), finalI, finalJ);
                                map.placeTile(currentTile, new Vector2D(finalI, finalJ));
                                map.checkForPoints(finalI, finalJ);
                                this.currentTile = map.getRandomTile();
                                ifPlaced[finalI][finalJ] = true;
                            }
                            else {
                                System.out.println(finalI);
                                System.out.println(finalJ);
                                System.out.println(map.canBePlaced(currentTile, finalI, finalJ));
                                System.out.println(currentTile);
                            }
                        });
                        hyperlinks[finalI][finalJ].setOnMouseExited(mouseEvent2 -> {
                            mapGridPane.add(new StackPane(hyperlinks[finalI][finalJ]), finalI, finalJ);
                        });
                        mapGridPane.add(stackPane, finalI, finalJ);
                    }
                });
                mapGridPane.add(new StackPane(hyperlinks[i][j]), i, j);

            }
        }
        mapGridPane.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) currentTile.turnRight();
            else if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) currentTile.turnLeft();

        });

        mapGridPane.add(new StackPane(new TileD(1).getImageView()), 15, 15);
        map.placeTile(new TileD(1), new Vector2D(15, 15));

        draggableMaker.makeDraggable(mapGridPane);
        VBox box = new VBox();

        mapGridPane.setLayoutX(-1000);
        mapGridPane.setLayoutY(-1000);
        primaryStage.setScene(new Scene(mapGridPane));
        primaryStage.show();
    }
}

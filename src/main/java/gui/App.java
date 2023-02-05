package gui;

import Classes.AbstractTile;
import Classes.Map;
import Classes.Player;
import Classes.Vector2D;
import Tiles.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;

public class App extends Application {

    private Player[] players;
    private int turn = 0;
    private AbstractTile currentTile;
    private GridPane mapGridPane = new GridPane();
    private Hyperlink[][] hyperlinks = new Hyperlink[31][31];
    private StackPane[][] stackPanes = new StackPane[31][31];
    private boolean[][] ifPlaced = new boolean[31][31];

    DraggableMaker draggableMaker = new DraggableMaker();

    public App() throws FileNotFoundException {
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Creating new map
        Map map = new Map();

        // Initializing gridpane and running the engine
        initialize(map);

        // Setting up a Stage
        primaryStage.setScene(new Scene(mapGridPane));
        primaryStage.show();
    }

    public void initialize(Map map) throws FileNotFoundException {

        // Getting first Tile
        this.currentTile = map.getRandomTile();

        // Setting up the grid in the Grid Pane
        for (int i=0; i<31; i++) {
            mapGridPane.getColumnConstraints().add(new ColumnConstraints(100));
            mapGridPane.getRowConstraints().add(new RowConstraints(100));
        }

        // Populating the grid with the StackPanes initially with corresponding hyperlinks
        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 31; j++) {

                // Variables with the current cords
                int finalI = i;
                int finalJ = j;

                // Setting up a hyperlink
                hyperlinks[i][j] = new Hyperlink();
                hyperlinks[i][j].setMinSize(100, 100);
                hyperlinks[i][j].setMaxSize(100, 100);

                // Adding hyperlinks to the corresponding StackPane
                stackPanes[i][j] = new StackPane(hyperlinks[i][j]);
                mapGridPane.add(stackPanes[i][j], i, j);

                // Adding functions to the hyperlinks

                // On entering the hyperlink program shows the current Tile ImageView in that place
                hyperlinks[i][j].setOnMouseEntered( entered -> {
                    mapGridPane.getChildren().remove(stackPanes[finalI][finalJ]);
                    stackPanes[finalI][finalJ] = new StackPane(this.currentTile.getImageView(), hyperlinks[finalI][finalJ]);
                    mapGridPane.add(stackPanes[finalI][finalJ], finalI, finalJ);
                });

                // On clicking the ImageView is being set and the hyperlink removed
                hyperlinks[i][j].setOnMouseClicked( clicked -> {
                    if (map.canBePlaced(currentTile, finalI, finalJ)) {
                        stackPanes[finalI][finalJ].getChildren().clear();
                        mapGridPane.getChildren().remove(stackPanes[finalI][finalJ]);
                        mapGridPane.add(this.currentTile.getImageView(), finalI, finalJ);
                        map.placeTile(currentTile, new Vector2D(finalI, finalJ));
                        map.checkForPoints(finalI, finalJ);
                        this.currentTile = map.getRandomTile();
                    }
                });
            }
        }

        // Making rotating the tiles possible
        mapGridPane.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) currentTile.turnRight();
            else if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) currentTile.turnLeft();
        });

        // Adding the starting Tile D
        mapGridPane.add(new StackPane(new TileD(1).getImageView()), 15, 15);
        map.placeTile(new TileD(1), new Vector2D(15, 15));

        // Making the map draggable
        draggableMaker.makeDraggable(mapGridPane);

        // Centering the map
        mapGridPane.setLayoutX(-1000);
        mapGridPane.setLayoutY(-1000);
    }
}

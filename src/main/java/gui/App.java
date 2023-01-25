package gui;

import Classes.AbstractTile;
import Classes.Map;
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

//    @FXML
//    private GridPane mapGridPane;
//
//    @FXML
//    private Hyperlink button;

    private AbstractTile currentTile = new TileG();
    private GridPane mapGridPane = new GridPane();
    private Hyperlink[][] hyperlinks = new Hyperlink[31][31];

    DraggableMaker draggableMaker = new DraggableMaker();

    public App() throws FileNotFoundException {
    }

    public void add(Node node, int i, int j) {
        StackPane stackPane = new StackPane(node);
        mapGridPane.add(stackPane, i, j);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

//        mapGridPane.setGridLinesVisible(true);

        for (int i=0; i<31; i++) {
            mapGridPane.getColumnConstraints().add(new ColumnConstraints(100));
            mapGridPane.getRowConstraints().add(new RowConstraints(100));
        }

        for (int i=0; i<31; i++) {
            for (int j=0; j<31; j++) {
                hyperlinks[i][j] = new Hyperlink();
                hyperlinks[i][j].setMinSize(100, 100);
                hyperlinks[i][j].setMaxSize(100, 100);
                int finalI = i;
                int finalJ = j;
                hyperlinks[i][j].setOnMouseClicked(mouseEvent -> {
                    add(currentTile.getImageView(), finalI, finalJ);
                    try {
                        this.currentTile = new TileG();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
                mapGridPane.add(new StackPane(hyperlinks[i][j]), i, j);
            }
        }

        draggableMaker.makeDraggable(mapGridPane);
        VBox box = new VBox();

//        Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
        Map map = new Map();
        map.setMapGrid(0, 0, new TileC());
        map.setMapGrid(0, 1, new TileD(1));
        map.setMapGrid(1, 0, new TileF());
        this.currentTile = new TileV(1);
        Game game = new Game(map);

        primaryStage.setScene(new Scene(mapGridPane));
        primaryStage.show();
    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        draggableMaker.makeDraggable(mapGridPane);
//        button.setOnMouseClicked(mouseEvent -> {
////            button.idProperty().set("");
//            Hyperlink button = new Hyperlink();
//            mapGridPane.add(button, 1, 1);
//            add(currentTile.getImageView(), 0, 0);
//        });
//    }
}

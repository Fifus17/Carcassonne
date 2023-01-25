package gui;

import Tiles.TileA;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private GridPane mapGridPane;

    @FXML
    private Hyperlink button;

    private TileA tileA = new TileA(1);

    DraggableMaker draggableMaker = new DraggableMaker();

    public Controller() throws FileNotFoundException {
    }

    public void add(Node node, int i, int j) {
        StackPane stackPane = new StackPane(node);
        mapGridPane.add(stackPane, i, j);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        draggableMaker.makeDraggable(mapGridPane);
        button.setOnMouseClicked(mouseEvent -> {
            EventTarget target = mouseEvent.getTarget();
            add(tileA.getImageView(), 0, 0);
        });
    }
}

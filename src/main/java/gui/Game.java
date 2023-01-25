package gui;

import Classes.AbstractTile;
import Classes.Map;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Game {
    Scene gameScene;

    private void drawMap(Map map, GridPane gridPane) {
        gridPane.setGridLinesVisible(false);
        AbstractTile[][] mapGrid = map.getMapGrid();
        int height = map.getSize().y;
        int width = map.getSize().x;
//        Vector2D currentPosition = new Vector2D(0, 0);
        for (int i=0; i <= width; i++) {
            for (int j=0; j <= height; j++) {
//                currentPosition = new Vector2D(i, height - j);
                StackPane tile = new StackPane(mapGrid[i][j].getImageView());
                gridPane.add(tile, i, j);
                GridPane.setHalignment(tile, HPos.CENTER);
            }
        }
    }

    public Game(Map map) {
        GridPane gridPane = new GridPane();
        drawMap(map, gridPane);
        this.gameScene = new Scene(gridPane);
    }

    public Scene getGameScene() {
        return this.gameScene;
    }

}

package gui;

import Classes.*;
import Enums.Color;
import Enums.Infrastructure;
import Enums.Sex;
import Enums.Subject;
import Tiles.TileD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game {

    private Scene gameScene;
    private Player[] players;
    private Pawn[] pawns;
    private AbstractTile currentTile;
    private int turn = 0;
    private GridPane mapGridPane = new GridPane();
    private Hyperlink[][] hyperlinks = new Hyperlink[31][31];
    private StackPane[][] stackPanes = new StackPane[31][31];
    private Parent currentPlayer;
    private CurrentPlayer currentPlayerController;

    private int currentI;
    private int currentJ;

    private Label currentPlayerNameGUI;
    private ImageView currentPlayerPortrait;
    private Label currentPlayerPoints;
    private Label currentPlayerPawns;
    private Pane currentPlayerColor;


    DraggableMaker draggableMaker = new DraggableMaker();

    public Game(Player[] players) throws IOException {
        this.players = players;

        // Creating UI

        // Creating menu Button
        ImageView menuButtonImageView =new ImageView(new Image(new FileInputStream("src/main/resources/materials/menu.png")));
        menuButtonImageView.setFitHeight(40);
        menuButtonImageView.setFitWidth(40);
        Button menuButton = new Button("", menuButtonImageView);
        menuButton.setLayoutX(20);
        menuButton.setLayoutY(20);
        menuButton.setMinSize(50, 50);
        menuButton.setMaxSize(50, 50);

        // Current Player
        FXMLLoader currentPlayerLoader = new FXMLLoader(getClass().getResource("CurrentPlayer.fxml"));
        this.currentPlayer = currentPlayerLoader.load();
        this.currentPlayerController = (CurrentPlayer) currentPlayerLoader.getController();

        // Creating new map
        Map map = new Map();

        // Initializing the Scene and running the engine
        initialize(map);
        AnchorPane anchorPane = new AnchorPane(mapGridPane, menuButton, currentPlayer);
        gameScene = new Scene(anchorPane);

        // currentPlayerGUI nodes
        currentPlayerNameGUI = (Label) gameScene.lookup("#playerName");
        currentPlayerNameGUI.setText(getCurrentPlayer().getName());

        currentPlayerPortrait = (ImageView) gameScene.lookup("#playerPortrait");
        currentPlayerPortrait.setImage(getCurrentPlayer().getPortrait());

        currentPlayerPoints = (Label) gameScene.lookup("#currentPlayerPoints");
        currentPlayerPoints.setText(Integer.toString(getCurrentPlayer().getPoints()));

        currentPlayerPawns = (Label) gameScene.lookup("#currentPlayerPawns");
        currentPlayerPawns.setText(Integer.toString(getCurrentPlayer().getNumberOfPawns()));

        currentPlayerColor = (Pane) gameScene.lookup("#currentPlayerColor");
        currentPlayerColor.setStyle(switch (getCurrentPlayer().getColor()) {
            case BLUE -> "-fx-background-color: blue; -fx-border-radius: 40; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 5px; -fx-background-radius: 40;";
            case YELLOW -> "-fx-background-color: yellow; -fx-border-radius: 40; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 5px; -fx-background-radius: 40;";
            case GREEN -> "-fx-background-color: green; -fx-border-radius: 40; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 5px; -fx-background-radius: 40;";
            case RED -> "-fx-background-color: red; -fx-border-radius: 40; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 5px; -fx-background-radius: 40;";
            case PINK -> "-fx-background-color: pink; -fx-border-radius: 40; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 5px; -fx-background-radius: 40;";
            case BLACK -> "-fx-background-color: black; -fx-border-radius: 40; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 5px; -fx-background-radius: 40;";
        });
    }

    public Scene getGameScene() {
        return this.gameScene;
    }

    public void initialize(Map map) throws FileNotFoundException {

        // Getting first Tile
        this.currentTile = map.getRandomTile();
        this.currentTile.getImageView().setFitWidth(96);
        this.currentTile.getImageView().setFitHeight(96);

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
                    this.currentI = finalI;
                    this.currentJ = finalJ;
                    if (map.canBePlaced(currentTile, finalI, finalJ)) currentTile.getImageView().setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,255,0,0.6), 30, 0, 0, 0);");
                    else currentTile.getImageView().setStyle("-fx-effect: dropshadow(three-pass-box, rgba(255,0,0,0.6), 30, 0, 0, 0);");
                    mapGridPane.getChildren().remove(stackPanes[finalI][finalJ]);
                    stackPanes[finalI][finalJ] = new StackPane(this.currentTile.getImageView(), hyperlinks[finalI][finalJ]);
                    mapGridPane.add(stackPanes[finalI][finalJ], finalI, finalJ);
                });

                // On clicking the ImageView is being set and the hyperlink removed
                hyperlinks[i][j].setOnMouseClicked( clicked -> {
                    if (map.canBePlaced(currentTile, finalI, finalJ)) {
                        turn++;

                        // clearing effects
                        this.currentTile.getImageView().setFitWidth(100);
                        this.currentTile.getImageView().setFitHeight(100);
                        this.currentTile.getImageView().setStyle("");

                        stackPanes[finalI][finalJ].getChildren().clear();
                        mapGridPane.getChildren().remove(stackPanes[finalI][finalJ]);
                        mapGridPane.add(this.currentTile.getImageView(), finalI, finalJ);
                        map.placeTile(currentTile, new Vector2D(finalI, finalJ));
                        map.checkForPoints(finalI, finalJ);

                        // changing the currentPlayer GUI
                        currentPlayerNameGUI.setText(getCurrentPlayer().getName());
                        currentPlayerPortrait.setImage(getCurrentPlayer().getPortrait());
                        currentPlayerPoints.setText(Integer.toString(getCurrentPlayer().getPoints()));
                        currentPlayerPawns.setText(Integer.toString(getCurrentPlayer().getNumberOfPawns()));
                        currentPlayerColor.setStyle(switch (getCurrentPlayer().getColor()) {
                            case BLUE -> "-fx-background-color: blue; -fx-border-radius: 40; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 5px; -fx-background-radius: 40;";
                            case YELLOW -> "-fx-background-color: yellow; -fx-border-radius: 40; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 5px; -fx-background-radius: 40;";
                            case GREEN -> "-fx-background-color: green; -fx-border-radius: 40; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 5px; -fx-background-radius: 40;";
                            case RED -> "-fx-background-color: red; -fx-border-radius: 40; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 5px; -fx-background-radius: 40;";
                            case PINK -> "-fx-background-color: pink; -fx-border-radius: 40; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 5px; -fx-background-radius: 40;";
                            case BLACK -> "-fx-background-color: black; -fx-border-radius: 40; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 5px; -fx-background-radius: 40;";
                        });

                        // getting new random Tile
                        this.currentTile = map.getRandomTile();
                        while (!map.checkIfCanBePlacedSomewhere(currentTile)) currentTile = map.changeTile(currentTile);
                        this.currentTile.getImageView().setFitWidth(96);
                        this.currentTile.getImageView().setFitHeight(96);
                    }
                });
            }
        }

        // Making rotating the tiles possible
        mapGridPane.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                currentTile.turnRight();
                if (map.canBePlaced(currentTile, currentI, currentJ)) currentTile.getImageView().setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,255,0,0.6), 30, 0, 0, 0);");
                else currentTile.getImageView().setStyle("-fx-effect: dropshadow(three-pass-box, rgba(255,0,0,0.6), 30, 0, 0, 0);");
            }
            else if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                currentTile.turnLeft();
                if (map.canBePlaced(currentTile, currentI, currentJ)) currentTile.getImageView().setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,255,0,0.6), 30, 0, 0, 0);");
                else currentTile.getImageView().setStyle("-fx-effect: dropshadow(three-pass-box, rgba(255,0,0,0.6), 30, 0, 0, 0);");
            }
        });


        Pawn testPawn = new Pawn(Color.PINK, Subject.Knight, Sex.Queen, new Vector2D(0, 0), Infrastructure.C);

        // Adding the starting Tile D
        StackPane testPane = new StackPane(new TileD(1).getImageView(), testPawn.getGraphic());
        testPane.setAlignment(Pos.TOP_CENTER);
        mapGridPane.add(testPane, 15, 15);
        map.placeTile(new TileD(1), new Vector2D(15, 15));

        // Making the map draggable
        draggableMaker.makeDraggable(mapGridPane);

        // Moving the map to show the first Tile
        mapGridPane.setLayoutX(-830);
        mapGridPane.setLayoutY(-1150);
    }

    // returning current Player
    public Player getCurrentPlayer() { return this.players[turn % players.length]; }

    // returning the currentPlayer GUI
    public Parent getCurrentPlayerGUI() { return this.currentPlayer; }

}

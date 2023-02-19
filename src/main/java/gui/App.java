package gui;

import Classes.AbstractTile;
import Classes.Map;
import Classes.Player;
import Classes.Vector2D;
import Enums.Color;
import Enums.Sex;
import Tiles.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class App extends Application {

    private Player[] players;
    private int turn = 0;
    private AbstractTile currentTile;
    private GridPane mapGridPane = new GridPane();
    private Hyperlink[][] hyperlinks = new Hyperlink[31][31];
    private StackPane[][] stackPanes = new StackPane[31][31];

    DraggableMaker draggableMaker = new DraggableMaker();

    public App() throws FileNotFoundException {
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        // Loading Main Page fxml files and controllers
        FXMLLoader mainPageLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent mainPage = mainPageLoader.load();
        Scene mainPageScene = new Scene(mainPage);
        MainPage mainPageController = (MainPage) mainPageLoader.getController();

        FXMLLoader newGamePageLoader = new FXMLLoader(getClass().getResource("NewGamePage.fxml"));
        Parent newGamePage = newGamePageLoader.load();
        Scene newGamePageScene = new Scene(newGamePage);
        NewGamePage newGamePageController = (NewGamePage) newGamePageLoader.getController();

        // Setting up the scenes between controllers
        mainPageController.setNewGameScene(newGamePageScene);
        newGamePageController.setMainPageScene(mainPageScene);

        // Adding new Game method to the newGame Button
        Button newGameButton = (Button) newGamePageScene.lookup("#startNewGame");
        TextField[] names = new TextField[]{
                (TextField) newGamePageScene.lookup("#BluePlayerName"),
                (TextField) newGamePageScene.lookup("#YellowPlayerName"),
                (TextField) newGamePageScene.lookup("#GreenPlayerName"),
                (TextField) newGamePageScene.lookup("#RedPlayerName"),
                (TextField) newGamePageScene.lookup("#PinkPlayerName"),
                (TextField) newGamePageScene.lookup("#BlackPlayerName")
        };

        RadioButton[] kingButtons = new RadioButton[]{
                (RadioButton) newGamePageScene.lookup("#BlueKing"),
                (RadioButton) newGamePageScene.lookup("#YellowKing"),
                (RadioButton) newGamePageScene.lookup("#GreenKing"),
                (RadioButton) newGamePageScene.lookup("#RedKing"),
                (RadioButton) newGamePageScene.lookup("#PinkKing"),
                (RadioButton) newGamePageScene.lookup("#BlackKing")
        };

        ImageView[] images = new ImageView[]{
                (ImageView) newGamePageScene.lookup("#BluePlayerPortrait"),
                (ImageView) newGamePageScene.lookup("#YellowPlayerPortrait"),
                (ImageView) newGamePageScene.lookup("#GreenPlayerPortrait"),
                (ImageView) newGamePageScene.lookup("#RedPlayerPortrait"),
                (ImageView) newGamePageScene.lookup("#PinkPlayerPortrait"),
                (ImageView) newGamePageScene.lookup("#BlackPlayerPortrait")
        };

        Color[] colors = { Color.BLUE, Color.YELLOW, Color.GREEN, Color.RED, Color.PINK, Color.BLACK };

        newGameButton.setOnAction(event -> {
            // Creating the players list
            ArrayList<Player> players = new ArrayList<Player>();

            Sex sex;
            // For each textfields, if they're not empty then a player is created
            for (int i = 0; i < 6; i++) {
                if (names[i].getText() != "") {
                    if (kingButtons[i].isSelected()) sex = Sex.King;
                    else sex = Sex.Queen;
                    players.add(new Player(colors[i], names[i].getText(), sex, images[i]));
                }
            }

            // running the app with the players ArrayList
            if (players.size() >= 2) {
                System.out.println(players.size());
                Player[] playersToPass = new Player[players.size()];
                playersToPass = players.toArray(playersToPass);
                try {
                    Game game = new Game(playersToPass);
                    primaryStage.setScene(game.getGameScene());
                    primaryStage.setFullScreen(true);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            else System.out.println("Not enough players");
        });


        // Creating new map
        Map map = new Map();

        // Initializing gridpane and running the engine
        initialize(map);

        // Setting up a Stage
        primaryStage.setScene(mainPageScene);
        primaryStage.setHeight(989);
        primaryStage.show();
        System.out.println(mainPageScene.getHeight());
        System.out.println(mainPageScene.getWidth());
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

        // Moving the map to show the first Tile
        mapGridPane.setLayoutX(-830);
        mapGridPane.setLayoutY(-1150);
    }
}

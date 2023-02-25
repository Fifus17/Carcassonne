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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class App extends Application {

    private Player[] players;
    private int turn = 0;
    private AbstractTile currentTile;
    private GridPane mapGridPane = new GridPane();
    private Hyperlink[][] hyperlinks = new Hyperlink[31][31];
    private StackPane[][] stackPanes = new StackPane[31][31];


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

        Color[] colors = {Color.BLUE, Color.YELLOW, Color.GREEN, Color.RED, Color.PINK, Color.BLACK};

        newGameButton.setOnAction(event -> {
            // Creating the players list
            ArrayList<Player> players = new ArrayList<Player>();
            Sex sex;

            // For each textfields, if they're not empty then a player is created
            for (int i = 0; i < 6; i++) {
                if (kingButtons[i].isSelected()) sex = Sex.King;
                else sex = Sex.Queen;
                if (names[i].getText() == "Zosia") sex = Sex.Zosia; // special case just for my purposes, feel free to change that
                if (names[i].getText() != "") players.add(new Player(colors[i], names[i].getText(), sex, images[i].getImage()));
            }

            // running the app with the players ArrayList
            if (players.size() >= 2) {
                System.out.println(players.size());
                Player[] playersToPass = new Player[players.size()];
                playersToPass = players.toArray(playersToPass);
                try {
                    Game game = new Game(playersToPass);
                    Parent currentPlayerGUI = game.getCurrentPlayerGUI();
                    primaryStage.setScene(game.getGameScene());
                    currentPlayerGUI.setLayoutX(primaryStage.getWidth() - 300 - 25);
                    currentPlayerGUI.setLayoutY(25);
                    StackPane tilesLeftStackPane = game.getTilesLeft();
                    tilesLeftStackPane.setLayoutX(25);
                    tilesLeftStackPane.setLayoutY(primaryStage.getHeight() - 25);
                    primaryStage.setFullScreen(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else System.out.println("Not enough players");
        });


        // Setting up a Stage
        primaryStage.setScene(mainPageScene);
        primaryStage.setHeight(989);
        primaryStage.show();
        System.out.println(mainPageScene.getHeight());
        System.out.println(mainPageScene.getWidth());
    }

}
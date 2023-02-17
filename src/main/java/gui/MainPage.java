package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPage {

    @FXML
    private Button credits;

    @FXML
    private Button exit;

    @FXML
    private Button history;

    @FXML
    private Button instructions;

    @FXML
    private Button newGame;

    private Scene newGameScene;

    public Button getNewGameButton() {
        return newGame;
    }

    public void startNewGameView(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(newGameScene);
    }

    // setting up new Game Scene
    public void setNewGameScene(Scene scene) {
        this.newGameScene = scene;
    }

}

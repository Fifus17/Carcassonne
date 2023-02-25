package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CurrentPlayer {

    @FXML
    private ImageView playerPortrait;

    @FXML
    private Pane currentPlayer;

    @FXML
    private Label currentPlayerPawns;

    @FXML
    private Label currentPlayerPoints;

    @FXML
    private Label playerName;

    public void setPlayerPortrait(ImageView change) { this.playerPortrait = change; }

}

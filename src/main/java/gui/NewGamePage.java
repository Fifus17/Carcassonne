package gui;

import Classes.Player;
import Enums.Color;
import Enums.Sex;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class NewGamePage {

    @FXML
    private RadioButton BlackKing;

    @FXML
    private TextField BlackPlayerName;

    @FXML
    private ImageView BlackPlayerPortrait;

    @FXML
    private ToggleGroup BlackPlayerToggleGroup;

    @FXML
    private RadioButton BlackQueen;

    @FXML
    private RadioButton BlueKing;

    @FXML
    private TextField BluePlayerName;

    @FXML
    private ImageView BluePlayerPortrait;

    @FXML
    private ToggleGroup BluePlayerToggleGroup;

    @FXML
    private RadioButton BlueQueen;

    @FXML
    private RadioButton GreenKing;

    @FXML
    private TextField GreenPlayerName;

    @FXML
    private ImageView GreenPlayerPortrait;

    @FXML
    private ToggleGroup GreenPlayerToggleGroup;

    @FXML
    private RadioButton GreenQueen;

    @FXML
    private RadioButton PinkKing;

    @FXML
    private TextField PinkPlayerName;

    @FXML
    private ImageView PinkPlayerPortrait;

    @FXML
    private ToggleGroup PinkPlayerToggleGroup;

    @FXML
    private RadioButton PinkQueen;

    @FXML
    private RadioButton RedKing;

    @FXML
    private TextField RedPlayerName;

    @FXML
    private ImageView RedPlayerPortrait;

    @FXML
    private ToggleGroup RedPlayerToggleGroup;

    @FXML
    private RadioButton RedQueen;

    @FXML
    private RadioButton YellowKing;

    @FXML
    private TextField YellowPlayerName;

    @FXML
    private ImageView YellowPlayerPortrait;

    @FXML
    private ToggleGroup YellowPlayerToggleGroup;

    @FXML
    private RadioButton YellowQueen;

    @FXML
    private Button goBack;

    @FXML
    private Button startNewGame;

    private Scene mainPageScene;

    private RadioButton[] kingButtons = { BlueKing, YellowKing, GreenKing, RedKing, PinkKing, BlackKing };
    private Color[] colors = { Color.BLUE, Color.YELLOW, Color.GREEN, Color.RED, Color.PINK, Color.BLACK };
    private ImageView[] images = { BluePlayerPortrait, YellowPlayerPortrait, GreenPlayerPortrait, RedPlayerPortrait, PinkPlayerPortrait, BlackPlayerPortrait };
    private TextField[] names = { BluePlayerName, YellowPlayerName, GreenPlayerName, RedPlayerName, PinkPlayerName, BlackPlayerName };

    // array with images
    Image[] graphics =  {
            new Image(new FileInputStream("src/main/resources/materials/BluePlayer/king.png")),
            new Image(new FileInputStream("src/main/resources/materials/BluePlayer/queen.png")),
            new Image(new FileInputStream("src/main/resources/materials/YellowPlayer/king.png")),
            new Image(new FileInputStream("src/main/resources/materials/YellowPlayer/queen.png")),
            new Image(new FileInputStream("src/main/resources/materials/GreenPlayer/king.png")),
            new Image(new FileInputStream("src/main/resources/materials/GreenPlayer/queen.png")),
            new Image(new FileInputStream("src/main/resources/materials/RedPlayer/king.png")),
            new Image(new FileInputStream("src/main/resources/materials/RedPlayer/queen.png")),
            new Image(new FileInputStream("src/main/resources/materials/PinkPlayer/king.png")),
            new Image(new FileInputStream("src/main/resources/materials/PinkPlayer/queen.png")),
            new Image(new FileInputStream("src/main/resources/materials/BlackPlayer/king.png")),
            new Image(new FileInputStream("src/main/resources/materials/BlackPlayer/queen.png"))
    };

    public NewGamePage() throws FileNotFoundException {
//        goBack.setGraphic(new ImageView(new Image(new FileInputStream("src/main/resources/materials/back-arrow-2.png"))));
    }

    private void StartGame() {
        // Creating the players list
        ArrayList<Player> players = new ArrayList<Player>();

        Sex sex;
        // For each textfields, if they're not empty then a player is created
        for (int i = 0; i < 6; i++) {
            if (names[i].getText() != null) {
                if (kingButtons[i].isSelected()) sex = Sex.King;
                else sex = Sex.Queen;
                players.add(new Player(colors[i], names[i].getText(), sex, images[i]));
            }
        }


        // running the app with the players ArrayList

    }

    public void changeBlueKing() { BluePlayerPortrait.setImage(this.graphics[0]); }

    public void changeBlueQueen() { BluePlayerPortrait.setImage(this.graphics[1]); }

    public void changeYellowKing() { YellowPlayerPortrait.setImage(this.graphics[2]); }

    public void changeYellowQueen() { YellowPlayerPortrait.setImage(this.graphics[3]); }

    public void changeGreenKing() { GreenPlayerPortrait.setImage(this.graphics[4]); }

    public void changeGreenQueen() { GreenPlayerPortrait.setImage(this.graphics[5]); }

    public void changeRedKing() { RedPlayerPortrait.setImage(this.graphics[6]); }

    public void changeRedQueen() { RedPlayerPortrait.setImage(this.graphics[7]); }

    public void changePinkKing() { PinkPlayerPortrait.setImage(this.graphics[8]); }

    public void changePinkQueen() { PinkPlayerPortrait.setImage(this.graphics[9]); }

    public void changeBlackKing() { BlackPlayerPortrait.setImage(this.graphics[10]); }

    public void changeBlackQueen() { BlackPlayerPortrait.setImage(this.graphics[11]); }

//     setting up mainPageScene
    public void setMainPageScene(Scene scene) { this.mainPageScene = scene; }

    public void goBack(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(mainPageScene);
    }


}

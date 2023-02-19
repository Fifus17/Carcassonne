package gui;

import Classes.Player;
import Enums.Color;
import Enums.Sex;
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
    private ImageView BlackPlayerPortrait;

    @FXML
    private ImageView BluePlayerPortrait;

    @FXML
    private ImageView GreenPlayerPortrait;

    @FXML
    private ImageView PinkPlayerPortrait;

    @FXML
    private ImageView RedPlayerPortrait;

    @FXML
    private ImageView YellowPlayerPortrait;

    private Scene mainPageScene;

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

    public NewGamePage() throws FileNotFoundException { }

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

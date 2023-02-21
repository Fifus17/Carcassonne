package Classes;

import Enums.Color;
import Enums.Infrastructure;
import Enums.Subject;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Pawn {
    Color color;
    Image image;
    ImageView imageView;
    Infrastructure placedOn;
    Vector2D position;
    StackPane backgroundPane;
    Subject type;

    public Pawn(Color color, Subject type,Vector2D position, Infrastructure infrastructure) throws FileNotFoundException {
        this.placedOn = infrastructure;
        this.position = position;
        this.color = color;
        this.type = type;

        // using image that corresponds to the type of Pawn
        switch (type) {
            case Knight -> image = new Image(new FileInputStream("src/main/resources/materials/knight.png"));
            case Robber -> image = new Image(new FileInputStream("src/main/resources/materials/robber.png"));
            case Priest -> image = new Image(new FileInputStream("src/main/resources/materials/priest.png"));
        }

        // Creating all graphic nodes
        imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        Pane frontPane = new Pane();
        frontPane.setMinHeight(40);
        frontPane.setMaxHeight(40);
        frontPane.setMinWidth(40);
        frontPane.setMaxWidth(40);
        frontPane.setStyle("-fx-border-radius: 20; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 4; -fx-background-radius: 20;");
        backgroundPane = new StackPane(imageView, frontPane);
        backgroundPane.setAlignment(Pos.CENTER);
        backgroundPane.setMinWidth(40);
        backgroundPane.setMaxWidth(40);
        backgroundPane.setMinHeight(40);
        backgroundPane.setMaxHeight(40);

        // setting style depending on the player color
        switch (color) {
            case BLUE -> backgroundPane.setStyle("-fx-border-radius: 20; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 4; -fx-background-radius: 20; -fx-background-color: blue;");
            case YELLOW -> backgroundPane.setStyle("-fx-border-radius: 20; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 4; -fx-background-radius: 20; -fx-background-color: yellow;");
            case GREEN -> backgroundPane.setStyle("-fx-border-radius: 20; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 4; -fx-background-radius: 20; -fx-background-color: green;");
            case RED -> backgroundPane.setStyle("-fx-border-radius: 20; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 4; -fx-background-radius: 20; -fx-background-color: red;");
            case PINK -> backgroundPane.setStyle("-fx-border-radius: 20; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 4; -fx-background-radius: 20; -fx-background-color: pink;");
            case BLACK -> backgroundPane.setStyle("-fx-border-radius: 20; -fx-border-color: rgb(255, 215, 0); -fx-border-width: 4; -fx-background-radius: 20; -fx-background-color: black;");
        }
    }

    // returning the complete graphic of the Pawn
    public StackPane getGraphic() { return backgroundPane; }
}

package Classes;

import Enums.Color;
import Enums.Infrastructure;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Pawn {
//    int id;
    Color color;
    Image image;
    ImageView imageView;
    Infrastructure placedOn;
    Vector2D position;

    public Pawn(Color color, Vector2D position, Infrastructure infrastructure) throws FileNotFoundException {
//        this.id = id;
        this.placedOn = infrastructure;
        this.position = position;
        this.color = color;
        switch (color) {
            case BLUE -> image = new Image(new FileInputStream("resources/colors/bluedot.png"));
            case PINK -> image = new Image(new FileInputStream("resources/colors/pinkdot.png"));
        }
        imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
    }
}

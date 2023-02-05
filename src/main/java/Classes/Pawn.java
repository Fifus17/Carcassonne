package Classes;

import Enums.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Pawn {
    int id;
    Color color;
    Image image;
    ImageView imageView;

    public Pawn(Color color, int id) throws FileNotFoundException {
        this.id = id;
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

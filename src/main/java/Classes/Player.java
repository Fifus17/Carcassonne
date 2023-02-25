package Classes;

import Enums.Color;
import Enums.Infrastructure;
import Enums.Sex;
import Enums.Subject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public class Player {
    private Color color;
    private String name;
    private int points = 0;
    private int numberOfPawns = 7;
    private Sex sex;
    private Image image;

    public Player(Color color, String name, Sex sex, Image image) {
        this.color = color;
        this.name = name;
        this.sex = sex;
        this.image = image;
    }

    public boolean canPlacePawn() {
        if (numberOfPawns > 1) {
            numberOfPawns -= 1;
            return true;
        }
        return false;
    }

    public Pawn placePawn(Subject subject, Vector2D position, Infrastructure infrastructure) throws FileNotFoundException {
        numberOfPawns -= 1;
        return new Pawn(this.color, subject, this.sex, position, infrastructure);
    }

    public void pawnBack() {
        numberOfPawns += 1;
    }

    public void addPoints(int points) { this.points += points; }


    public Image getPortrait() { return this.image; }

    public String getName() {
        switch (sex) {
            case King -> { return "King " + this.name; }
            case Zosia, Queen -> { return "Queen " + this.name;
            }
        }
        return null;
    }

    public int getPoints() { return this.points; }

    public int getNumberOfPawns() { return this.numberOfPawns; }

    public Color getColor() { return this.color; }
}

package Classes;

import Enums.Color;

public class Player {
    private Color color;
    private String name;
    private int points = 0;
    private int numberOfPawns = 7;

    public Player(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public boolean placePawn() {
        if (numberOfPawns > 1) {
            numberOfPawns -= 1;
            return true;
        }
        return false;
    }

    public void pawnBack() {
        numberOfPawns += 1;
    }

    public void addPoints(int points) { this.points += points; }
}

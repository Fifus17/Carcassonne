import Classes.Map;
import Tiles.TileA;
import Tiles.TileJ;
import Tiles.TileT;
import Tiles.TileU;
import gui.App;
import javafx.application.Application;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Map map = new Map();
        map.setMapGrid(0, 0, new TileA(1));
        map.setMapGrid(1, 0, new TileJ(1));
        map.getMapGrid()[1][0].turnLeft();
        System.out.print(map);
        Application.launch(App.class, args);
    }
}
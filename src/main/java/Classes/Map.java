package Classes;

import Tiles.TileD;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Map {
    private ArrayList<AbstractTile> tileSet;
    private AbstractTile[][] mapGrid;
    private Vector2D size = new Vector2D(1, 1);

    public Map(Vector2D size) throws FileNotFoundException {
        this.size = size;
        this.mapGrid = new AbstractTile[size.x][size.y];
        this.mapGrid[size.x+1/2][size.y+1/2] = new TileD(1);
    }

    public Map() throws FileNotFoundException {
        this.mapGrid = new AbstractTile[3][3];
        this.mapGrid[1][1] = new TileD(1);
    }

    public AbstractTile[][] getMapGrid() { return this.mapGrid; }

    public void setMapGrid(int x, int y, AbstractTile tile) {
        this.mapGrid[x][y] = tile;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < mapGrid.length; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < mapGrid[0].length; j++) {
                    if(mapGrid[i][j] == null) {
                        result = result + "P, P, P ,";
                    }
                     else result = result + mapGrid[i][j].toStringRow(k);
                }
                result = result + "\n";
            }
        }
        return result;
    }

    public void drawMap() {
        for (int i = 0; i < this.mapGrid.length; i++) {
            for (int j = 0; j < this.mapGrid.length; j++) {
                if(mapGrid[i][j] == null) System.out.print("P, P, P ,");
                else System.out.print(this.mapGrid[i][j].toString());
            }
        }
    }

    public Vector2D getSize() { return this.size; }

    public AbstractTile getRandomTile() {
        int rnd = new Random().nextInt(tileSet.size());
        AbstractTile tile = tileSet.get(rnd);
        tileSet.remove(rnd);
        return tile;
    }

    public void placeTile(AbstractTile tile, Vector2D position) {
        this.mapGrid[position.x][position.y] = tile;
    }
}

package Classes;

import Tiles.TileD;

import java.io.FileNotFoundException;

public class Map {
    private AbstractTile[] tileSet;
    private AbstractTile[][] mapGrid;

    public Map(int size) throws FileNotFoundException {
        this.mapGrid = new AbstractTile[size][size];
        this.mapGrid[size+1/2][size+1/2] = new TileD(1);
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
}

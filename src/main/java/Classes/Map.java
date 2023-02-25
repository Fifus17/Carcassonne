package Classes;

import Enums.Color;
import Enums.Direction;
import Enums.Infrastructure;
import Tiles.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

public class Map {
    private ArrayList<AbstractTile> tileSet = new ArrayList<AbstractTile>(
            Arrays.asList(
                    new TileA(1),
                    new TileA(2),
                    new TileB(1),
                    new TileB(1),
                    new TileB(2),
                    new TileB(3),
                    new TileC(),
//                    new TileD(1), starting Tile
                    new TileD(1),
                    new TileD(2),
                    new TileD(3),
                    new TileE(1),
                    new TileE(2),
                    new TileE(3),
                    new TileE(4),
                    new TileE(4),
                    new TileF(),
                    new TileG(),
                    new TileH(1),
                    new TileH(2),
                    new TileH(3),
                    new TileI(1),
                    new TileI(2),
                    new TileJ(1),
                    new TileJ(2),
                    new TileJ(3),
                    new TileK(1),
                    new TileK(2),
                    new TileK(3),
                    new TileL(),
                    new TileM(1),
                    new TileM(2),
                    new TileN(1),
                    new TileN(2),
                    new TileN(3),
                    new TileO(1),
                    new TileO(2),
                    new TileP(1),
                    new TileP(2),
                    new TileP(3),
                    new TileQ(),
                    new TileS(1),
                    new TileS(2),
                    new TileT(),
                    new TileU(1),
                    new TileU(2),
                    new TileU(3),
                    new TileU(4),
                    new TileU(4),
                    new TileU(4),
                    new TileU(5),
                    new TileU(5),
                    new TileV(1),
                    new TileV(2),
                    new TileV(3),
                    new TileV(3),
                    new TileV(4),
                    new TileV(4),
                    new TileV(5),
                    new TileV(5),
                    new TileV(6),
                    new TileW(),
                    new TileW(),
                    new TileW(),
                    new TileW(),
                    new TileX()
                    )
    );
    private AbstractTile[][] mapGrid;
    private Infrastructure[][] mapInfrastructureGrid = new Infrastructure[93][93];
    private Color[][] pawnsPlaced = new Color[93][93];
    private Vector2D size = new Vector2D(31, 31);

    public Map(Vector2D size) throws FileNotFoundException {
        this.size = size;
        this.mapGrid = new AbstractTile[size.x][size.y];
        this.mapGrid[size.x+1/2][size.y+1/2] = new TileD(1);
    }

    public Map() throws FileNotFoundException {
        this.mapGrid = new AbstractTile[31][31];
        this.mapGrid[15][15] = new TileD(1);
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
        if (tileSet.size() > 0) {
            int rnd = new Random().nextInt(tileSet.size());
            AbstractTile tile = tileSet.get(rnd);
            System.out.println("Tiles left: " + tileSet.size());
            tileSet.remove(rnd);
            return tile;
        }
        else {
            System.out.println("End of tiles");
            return null;
        }
    }

    public AbstractTile changeTile(AbstractTile tileToChange) {
        int rnd = new Random().nextInt(tileSet.size());
        AbstractTile tile = tileSet.get(rnd);
        tileSet.remove(rnd);
        tileSet.add(tileToChange);
        System.out.println("Tile changed");
        return tile;
    }

    public void placeTile(AbstractTile tile, Vector2D position) {
        this.mapGrid[position.x][position.y] = tile;
        this.mapInfrastructureGrid[3*position.x][3* position.y] = tile.scheme[0];
        this.mapInfrastructureGrid[3*position.x+1][3* position.y] = tile.scheme[1];
        this.mapInfrastructureGrid[3*position.x+2][3* position.y] = tile.scheme[2];
        this.mapInfrastructureGrid[3*position.x][3* position.y+1] = tile.scheme[3];
        this.mapInfrastructureGrid[3*position.x+1][3* position.y+1] = tile.scheme[4];
        this.mapInfrastructureGrid[3*position.x+2][3* position.y+1] = tile.scheme[5];
        this.mapInfrastructureGrid[3*position.x][3* position.y+2] = tile.scheme[6];
        this.mapInfrastructureGrid[3*position.x+1][3* position.y+2] = tile.scheme[7];
        this.mapInfrastructureGrid[3*position.x+2][3* position.y+2] = tile.scheme[8];

    }

    public boolean canBePlaced(AbstractTile tile, int i, int j) {
        if(mapGrid[i-1][j]==null && mapGrid[i+1][j]==null && mapGrid[i][j-1]==null && mapGrid[i][j+1]==null) return false;
        else if(i > 0 && mapGrid[i-1][j] != null && !match(tile.scheme[3], (mapGrid[i-1][j].getScheme()[5]))) return false;
        else if(i < 31 && mapGrid[i+1][j] != null && !match(tile.scheme[5], (mapGrid[i+1][j].getScheme()[3]))) return false;
        else if(j > 0 && mapGrid[i][j-1] != null && !match(tile.scheme[1], (mapGrid[i][j-1].getScheme()[7]))) return false;
        else if(j < 31 && mapGrid[i][j+1] != null && !match(tile.scheme[7], (mapGrid[i][j+1].getScheme()[1]))) return false;
        return true;
    }

    public boolean match(Infrastructure inf1, Infrastructure inf2) {
        return switch (inf1) {
            case R -> inf2.equals(Infrastructure.R) || inf2.equals(Infrastructure.G) || inf2.equals(Infrastructure.R1) || inf2.equals(Infrastructure.R2);
            case P -> inf2.equals(Infrastructure.P) || inf2.equals(Infrastructure.CB);
            case C -> inf2.equals(Infrastructure.C) || inf2.equals(Infrastructure.S);
            case S -> inf2.equals(Infrastructure.C) || inf2.equals(Infrastructure.S);
            default -> false;
        };
    }

    public int checkForPoints(int i, int j) {
        int points = 0;
        // check for monasteries on placed tile and the adjacent
        for (int a=0; a<3; a++) {
            for (int b=0; b<3; b++) {
                if (mapGrid[i - 1 + a][j - 1 + b] != null) {
                    if (monasteryCheck(i - 1 + a, j - 1 + b)) {
                        System.out.println("Monastery closed");
                        points += 8;
                    }
                }
            }
        }
        // check for the roads
        points += newRoadCheck(i, j);
        return points;
    }

    private boolean monasteryCheck(int i, int j) {
        if(mapInfrastructureGrid[3*i+1][3*j+1].equals(Infrastructure.M)) {
            return !(mapGrid[i-1][j-1]==null || mapGrid[i-1][j]==null || mapGrid[i-1][j+1]==null ||
                    mapGrid[i][j-1]==null || mapGrid[i][j+1]==null ||
                    mapGrid[i+1][j-1]==null || mapGrid[i+1][j]==null || mapGrid[i+1][j+1]==null);
        }
        return false;
    }

    private int roadRecursiveCheck(int i, int j, int d, int x, int y, Direction direction) {
        // check if we did a loop
        if (i==x && j==y) { return d-1; }
        // check if there's and endpoint on the tile
        if      (mapGrid[i][j].getScheme()[4]==Infrastructure.V || // later we need to check for the new village roads
                mapGrid[i][j].getScheme()[4]==Infrastructure.G ||
                mapGrid[i][j].getScheme()[4]==Infrastructure.M)
        { return d; }

        // else, find where the road is going and then check if next tile is a null, if it is then return 0, else return f(new tile)
        if (direction != Direction.TOP && mapGrid[i][j].getScheme()[1]==Infrastructure.R)  {
            if (mapGrid[i][j-1]==null) return 0;
            else return roadRecursiveCheck(i, j-1, d+1, x, y, Direction.BOT);
        }
        else if (direction != Direction.LEFT && mapGrid[i][j].getScheme()[3]==Infrastructure.R) {
            if (mapGrid[i-1][j]==null) return 0;
            else return roadRecursiveCheck(i-1, j, d+1, x, y, Direction.RIGHT);
        }
        else if (direction != Direction.RIGHT && mapGrid[i][j].getScheme()[5]==Infrastructure.R) {
            if (mapGrid[i+1][j]==null) return 0;
            else return roadRecursiveCheck(i+1, j, d+1, x, y, Direction.LEFT);
        }
        else if (direction != Direction.BOT && mapGrid[i][j].getScheme()[7]==Infrastructure.R) {
            if (mapGrid[i][j+1]==null) return 0;
            else return roadRecursiveCheck(i, j+1, d+1, x, y, Direction.TOP);
        }
        return 0;
    }

    private int newRoadCheck(int i, int j) {
        boolean[] roads = {false, false, false, false};
        int roadsCount = 0;
        int closed = 0;
        int points = 1;

        // finding where the roads are going and how many are there
        if (mapGrid[i][j].getScheme()[1]==Infrastructure.R)
        {
            roads[0] = true;
            roadsCount += 1;
        }
        if (mapGrid[i][j].getScheme()[3]==Infrastructure.R) {
            roads[1] = true;
            roadsCount += 1;
        }
        if (mapGrid[i][j].getScheme()[5]==Infrastructure.R) {
            roads[2] = true;
            roadsCount += 1;
        }
        if (mapGrid[i][j].getScheme()[7]==Infrastructure.R) {
            roads[3] = true;
            roadsCount += 1;
        }

        // No road on tile
        if (roadsCount == 0) { return 0; }

        // checking the roads
        if (roadsCount < 3) {
            if (roads[0] && mapGrid[i][j-1]!=null) {
            int tmp = roadRecursiveCheck(i, j-1, 1, i, j, Direction.BOT);
            if (tmp > 0) {
                points += tmp;
                closed += 1;
            }
            }
            if (roads[1] && mapGrid[i-1][j]!=null) {
                int tmp = roadRecursiveCheck(i-1, j, 1, i, j, Direction.RIGHT);
                if (tmp > 0) {
                    points += tmp;
                    closed += 1;
                }
            }
            if (roads[2] && mapGrid[i+1][j]!=null) {
                int tmp = roadRecursiveCheck(i+1, j, 1, i, j, Direction.LEFT);
                if (tmp > 0) {
                    points += tmp;
                    closed += 1;
                }
            }
            if (roads[3] && mapGrid[i][j+1]!=null) {
                int tmp = roadRecursiveCheck(i, j+1, 1, i, j, Direction.TOP);
                if (tmp > 0) {
                    points += tmp;
                    closed += 1;
                }
            }
        }
        if (roadsCount == 1 && closed == 1) {
            System.out.println("One way road closed!");
            return points;
        }
        if (roadsCount == 2 && closed == 2) {
            System.out.println("Two way road closed!");
            return points;
        }
        if (roadsCount == 3) {
            if (roads[0] && mapGrid[i][j-1]!=null) {
                int tmp = roadRecursiveCheck(i, j-1, 1, i, j, Direction.BOT);
                if (tmp > 0) {
                    System.out.println("Upper road closed!");
                    points += tmp;
                    closed += 1;
                }
            }
            if (roads[1] && mapGrid[i-1][j]!=null) {
                int tmp = roadRecursiveCheck(i-1, j, 1, i, j, Direction.RIGHT);
                if (tmp > 0) {
                    System.out.println("Left road closed!");
                    points += tmp;
                    closed += 1;
                }
            }
            if (roads[2] && mapGrid[i+1][j]!=null) {
                int tmp = roadRecursiveCheck(i+1, j, 1, i, j, Direction.LEFT);
                if (tmp > 0) {
                    System.out.println("Right road closed!");
                    points += tmp;
                    closed += 1;
                }
            }
            if (roads[3] && mapGrid[i][j+1]!=null) {
                int tmp = roadRecursiveCheck(i, j+1, 1, i, j, Direction.TOP);
                if (tmp > 0) {
                    System.out.println("Lower road closed");
                    points += tmp;
                    closed += 1;
                }
            }
        }
        return 0;
    }

    // I am too lazy to optimize this, but you can hold somewhere the boundary of the current map and just check its surroundings
    public boolean checkIfCanBePlacedSomewhere(AbstractTile tile) {
        for (int i=1; i < mapGrid.length-1; i++) {
            for (int j=1; j < mapGrid.length-1; j++) {
                if (mapGrid[i][j] == null) {
                    if (canBePlaced(tile, i, j)) return true;
                    tile.turnRight();
                    if (canBePlaced(tile, i, j)) return true;
                    tile.turnRight();
                    if (canBePlaced(tile, i, j)) return true;
                    tile.turnRight();
                    if (canBePlaced(tile, i, j)) return true;
                }
            }
        }
        return false;
    }

    public int tilesLeft() { return tileSet.size(); }

}

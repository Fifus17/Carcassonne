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
        int rnd = new Random().nextInt(tileSet.size());
        AbstractTile tile = tileSet.get(rnd);
        tileSet.remove(rnd);
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
        points += roadCheck(i, j);
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

    private int roadCheck(int i, int j) {
        //tile with village
        if (mapInfrastructureGrid[3*i+1][3*j+1]==(Infrastructure.V)) {
            int points = 0;
            int tmp = 0;
            tmp = checkNextRoadNode(3*i+1, 3*j, Direction.BOT, 1);
            if (tmp > 0) {
                points += tmp;
                System.out.println("Road completed");
            }
            tmp = checkNextRoadNode(3*i, 3*j+1, Direction.RIGHT, 1);
            if (tmp > 0) {
                points += tmp;
                System.out.println("Road completed");
            }
            tmp = checkNextRoadNode(3*i+2, 3*j+1, Direction.LEFT, 1);
            if (tmp > 0) {
                points += tmp;
                System.out.println("Road completed");
            }
            tmp = checkNextRoadNode(3*i+1, 3*j+2, Direction.TOP, 1);
            if (tmp > 0) {
                points += tmp;
                System.out.println("Road completed");
            }
            return points;
        }
        //tile without village
        else {
            int a=0;
            // finding first road on the tile
            while (mapInfrastructureGrid[3*i+a%3][3*j+(a/3)]!=Infrastructure.R) {
                if(a < 8) a+=1;
                else {
                    a = 100;
                    break;
                }
            }
            if (a!=100) {
                int points = 1;
                int ends = 0;
                int tmp = checkNextRoadNode(3*i+a%3, 3*j+(a/3)-1, Direction.BOT, 0);
                if(tmp > 0) {
                    points += tmp;
                    ends += 1;
                }
                tmp = checkNextRoadNode(3*i+a%3-1, 3*j+(a/3), Direction.RIGHT, 0);
                if(tmp > 0) {
                    points += tmp;
                    ends += 1;
                }
                tmp = checkNextRoadNode(3*i+a%3+1, 3*j+(a/3), Direction.LEFT, 0);
                if (tmp > 0) {
                    points += tmp;
                    ends += 1;
                }
                tmp = checkNextRoadNode(3*i+a%3, 3*j+(a/3)+1, Direction.TOP, 0);
                if (tmp > 0) {
                    points += tmp;
                    ends += 1;
                }
                if (ends == 2) {
                    return points/3 + points%3;
                }
            }
            else return 0;
        }
        return 0;
    }

    private int checkNextRoadNode(int i, int j, Direction direction, int points) { // i and j in mapInfrastructureGrid
        if(direction!=Direction.LEFT && mapInfrastructureGrid[i-1][j] != null) {
                switch (mapInfrastructureGrid[i - 1][j]) {
                    case R:
                        checkNextRoadNode(i - 1, j, Direction.RIGHT, points + 1);
                        break;
                    case V:
                        return points;
                    case G:
                        return points;
                    case M:
                        return points;
                    default:
                        break;
                }
            }
        if(direction!= Direction.TOP && mapInfrastructureGrid[i][j-1] != null) {
            switch (mapInfrastructureGrid[i][j-1]) {
                case R: checkNextRoadNode(i, j-1, Direction.BOT, points+1); break;
                case V: return points;
                case G: return points;
                case M: return points;
                default: break;
            }
        }
        if(direction!= Direction.RIGHT && mapInfrastructureGrid[i+1][j] != null) {
            switch (mapInfrastructureGrid[i+1][j]) {
                case R: checkNextRoadNode(i+1, j, Direction.LEFT, points+1); break;
                case V: return points;
                case G: return points;
                case M: return points;
                default: break;
            }
        }
        if(direction!= Direction.BOT && mapInfrastructureGrid[i][j+1] != null) {
            switch (mapInfrastructureGrid[i][j+1]) {
                case R: checkNextRoadNode(i, j+1, Direction.TOP, points+1); break;
                case V: return points;
                case G: return points;
                case M: return points;
                default: break;
            }
        }
        return 0;
    }

}

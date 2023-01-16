package Classes;

import Enums.Direction;
import Enums.Infrastructure;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class AbstractTile {
    protected String id;
    protected Direction direction = Direction.TOP;
    protected Infrastructure[] scheme;
    protected Image graphic;
    protected ImageView graphicView;

    public AbstractTile(String id, Infrastructure[] scheme, int version) throws FileNotFoundException {
        this.id = id;
        this.scheme = scheme;
        this.graphic = new Image(new FileInputStream("src/main/resources/Tiles/Tile"+id+Integer.toString(version)+".png"));
        this.graphicView = new ImageView(this.graphic);
    }

    public AbstractTile(String id, Infrastructure[] scheme) throws FileNotFoundException {
        this.id = id;
        this.scheme = scheme;
        this.graphic = new Image(new FileInputStream("src/main/resources/Tiles/Tile"+id+".png"));
        this.graphicView = new ImageView(this.graphic);
    }

    public Infrastructure[] getScheme() { return this.scheme; }

    public ImageView getImageView() { return this.graphicView; }

    public String getId() { return this.id; }

    public Direction getDirection() { return this.direction; }

    public String toString() {
        String result = "";
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch(this.scheme[3*i+j]) {
                    case P: result = result + "P, "; break;
                    case R: result = result + "R, "; break;
                    case R1: result = result + "R1, "; break;
                    case R2: result = result + "R2, "; break;
                    case C: result = result + "C, "; break;
                    case M: result = result + "M, "; break;
                    case V: result = result + "V, "; break;
                    case G: result = result + "G, "; break;
                    case CB: result = result + "B, "; break;
                    case S: result = result + "S, "; break;
                }
            }
        }
        return result;
    }

    public String toStringRow(int row) {
        String result = "";
        for(int i = 0; i < 3; i++) {
                switch(this.scheme[3*row + i]) {
                    case P: result = result + "P, "; break;
                    case R: result = result + "R, "; break;
                    case R1: result = result + "R1, "; break;
                    case R2: result = result + "R2, "; break;
                    case C: result = result + "C, "; break;
                    case M: result = result + "M, "; break;
                    case V: result = result + "V, "; break;
                    case G: result = result + "G, "; break;
                    case CB: result = result + "B, "; break;
                    case S: result = result + "S, "; break;
            }
        }
        return result;
    }

    public void draw() {
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch(this.scheme[3*i+j]) {
                    case P: System.out.print("P, "); break;
                    case R: System.out.print("R, "); break;
                    case R1: System.out.print("R1, "); break;
                    case R2: System.out.print("R2, "); break;
                    case C: System.out.print("C, "); break;
                    case M: System.out.print("M, "); break;
                    case V: System.out.print("V, "); break;
                    case G: System.out.print("G, "); break;
                    case CB: System.out.print("CB, "); break;
                    case S: System.out.print("S, "); break;
                }
            }
            System.out.println();
        }
    }

    public void drawRow(int x) {
        if(x>=0 && x<=2) {
            for (int i=3*x; i<3*(x+1); i++) {
                switch(this.scheme[i]) {
                    case P: System.out.print("P,"); break;
                    case R: System.out.print("R,"); break;
                    case R1: System.out.print("R1,"); break;
                    case R2: System.out.print("R2,"); break;
                    case C: System.out.print("C,"); break;
                    case M: System.out.print("M,"); break;
                    case V: System.out.print("V,"); break;
                    case G: System.out.print("G,"); break;
                    case S: System.out.print("S,"); break;
                    case CB: System.out.print("CB,"); break;
                }
            }
        }
        else System.out.println("Choose from 0, 1 and 2 row");
    }

    public void turnRight() {
        Infrastructure tmp1 = this.scheme[0];
        this.scheme[0] = this.scheme[6];
        this.scheme[6] = this.scheme[8];
        this.scheme[8] = this.scheme[2];
        this.scheme[2] = tmp1;
        tmp1 = this.scheme[1];
        this.scheme[1] = this.scheme[3];
        this.scheme[3] = this.scheme[7];
        this.scheme[7] = this.scheme[5];
        this.scheme[5] = tmp1;
        this.graphicView.setRotate(this.graphicView.getRotate() + 90);
    }

    public void turnLeft() {
        Infrastructure tmp1 = this.scheme[0];
        this.scheme[0] = this.scheme[2];
        this.scheme[2] = this.scheme[8];
        this.scheme[8] = this.scheme[6];
        this.scheme[6] = tmp1;
        tmp1 = this.scheme[1];
        this.scheme[1] = this.scheme[5];
        this.scheme[5] = this.scheme[7];
        this.scheme[7] = this.scheme[3];
        this.scheme[3] = tmp1;
        this.graphicView.setRotate(this.graphicView.getRotate() - 90);
    }

}

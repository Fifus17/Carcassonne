package Classes;

import java.util.Objects;

public class Vector2D {
    public final int x;
    public final int y;

    public Vector2D( int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString(){return "(" + Integer.toString(this.x) + "," +Integer.toString(this.y) + ")";}
    public boolean precedes(Vector2D other){return (this.x <= other.x && this.y <= other.y);}

    public boolean follows(Vector2D other){return (this.x >= other.x && this.y >= other.y);}

    public Vector2D add(Vector2D other){return new Vector2D(this.x+ other.x,this.y + other.y);}

    public Vector2D substract(Vector2D other){return new Vector2D(this.x -other.x, this.y - other.y);}

    public Vector2D upperRight(Vector2D other){return new Vector2D(Math.max(this.x,other.x),Math.max(this.x,other.x));}

    public Vector2D lowerLeft(Vector2D other){return new Vector2D(Math.min(this.x, other.x),Math.min(this.y, other.y));}

    public Vector2D opposite(){return new Vector2D(this.y, this.x);}

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2D))
            return false;
        Vector2D that = (Vector2D) other;
        if(this.x == that.x && this.y == that.y)return true;
        return false;
    }
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}

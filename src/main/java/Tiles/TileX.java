package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileX extends AbstractTile {

    public TileX() throws FileNotFoundException {
        super(
                "X",
                new Infrastructure[]{
                        Infrastructure.P, Infrastructure.R, Infrastructure.P,
                        Infrastructure.R, Infrastructure.V, Infrastructure.R,
                        Infrastructure.P, Infrastructure.R, Infrastructure.P
                }
        );
    }
}

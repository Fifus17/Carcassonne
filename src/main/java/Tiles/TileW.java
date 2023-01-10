package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileW extends AbstractTile {

    public TileW() throws FileNotFoundException {
        super(
                "W",
                new Infrastructure[]{
                        Infrastructure.P, Infrastructure.P, Infrastructure.P,
                        Infrastructure.R, Infrastructure.V, Infrastructure.R,
                        Infrastructure.P, Infrastructure.R, Infrastructure.P
                }
        );
    }
}

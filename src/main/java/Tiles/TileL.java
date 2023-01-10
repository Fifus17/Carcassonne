package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileL extends AbstractTile {

    public TileL() throws FileNotFoundException {
        super(
                "L",
                new Infrastructure[]{
                        Infrastructure.CB, Infrastructure.C, Infrastructure.CB,
                        Infrastructure.R, Infrastructure.V, Infrastructure.R,
                        Infrastructure.P, Infrastructure.R, Infrastructure.P
                }
        );
    }
}

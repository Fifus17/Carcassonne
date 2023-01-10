package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileO extends AbstractTile {

    public TileO(int version) throws FileNotFoundException {
        super(
                "O",
                new Infrastructure[]{
                        Infrastructure.S, Infrastructure.C, Infrastructure.CB,
                        Infrastructure.C, Infrastructure.R, Infrastructure.R,
                        Infrastructure.CB, Infrastructure.R, Infrastructure.P
                },
                version
        );
    }
}

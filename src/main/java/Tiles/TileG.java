package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileG extends AbstractTile {

    public TileG() throws FileNotFoundException {
        super(
                "G",
                new Infrastructure[]{
                        Infrastructure.CB, Infrastructure.P, Infrastructure.CB,
                        Infrastructure.C, Infrastructure.C, Infrastructure.C,
                        Infrastructure.CB, Infrastructure.P, Infrastructure.CB
                }
        );
    }
}

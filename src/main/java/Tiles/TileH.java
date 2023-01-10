package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileH extends AbstractTile {

    public TileH(int version) throws FileNotFoundException {
        super(
                "H",
                new Infrastructure[]{
                        Infrastructure.CB, Infrastructure.C, Infrastructure.CB,
                        Infrastructure.P, Infrastructure.P, Infrastructure.P,
                        Infrastructure.CB, Infrastructure.C, Infrastructure.CB
                },
                version
        );
    }
}

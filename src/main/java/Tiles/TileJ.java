package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileJ extends AbstractTile {

    public TileJ(int version) throws FileNotFoundException {
        super(
                "J",
                new Infrastructure[]{
                        Infrastructure.CB, Infrastructure.C, Infrastructure.CB,
                        Infrastructure.P, Infrastructure.R, Infrastructure.R,
                        Infrastructure.P, Infrastructure.R, Infrastructure.P
                },
                version
        );
    }
}

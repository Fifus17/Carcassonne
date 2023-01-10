package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileP extends AbstractTile {

    public TileP(int version) throws FileNotFoundException {
        super(
                "P",
                new Infrastructure[]{
                        Infrastructure.S, Infrastructure.C, Infrastructure.CB,
                        Infrastructure.C, Infrastructure.R, Infrastructure.R,
                        Infrastructure.CB, Infrastructure.R, Infrastructure.P
                },
                version
        );
    }
}

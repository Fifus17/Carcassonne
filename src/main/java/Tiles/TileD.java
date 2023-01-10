package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileD extends AbstractTile {

    public TileD(int version) throws FileNotFoundException {
        super(
                "D",
                new Infrastructure[]{
                        Infrastructure.CB, Infrastructure.C, Infrastructure.CB,
                        Infrastructure.R, Infrastructure.R, Infrastructure.R,
                        Infrastructure.P, Infrastructure.P, Infrastructure.P
                },
                version
        );
    }
}

package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileK extends AbstractTile {

    public TileK(int version) throws FileNotFoundException {
        super(
                "K",
                new Infrastructure[]{
                        Infrastructure.CB, Infrastructure.C, Infrastructure.CB,
                        Infrastructure.R, Infrastructure.R, Infrastructure.P,
                        Infrastructure.P, Infrastructure.R, Infrastructure.P
                },
                version
        );
    }
}

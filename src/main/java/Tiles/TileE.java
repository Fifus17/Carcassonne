package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileE extends AbstractTile {

    public TileE(int version) throws FileNotFoundException {
        super(
                "E",
                new Infrastructure[]{
                        Infrastructure.CB, Infrastructure.C, Infrastructure.CB,
                        Infrastructure.P, Infrastructure.P, Infrastructure.P,
                        Infrastructure.P, Infrastructure.P, Infrastructure.P
                },
                version
        );
    }
}

package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileN extends AbstractTile {

    public TileN(int version) throws FileNotFoundException {
        super(
                "N",
                new Infrastructure[]{
                        Infrastructure.C, Infrastructure.C, Infrastructure.CB,
                        Infrastructure.C, Infrastructure.C, Infrastructure.P,
                        Infrastructure.CB, Infrastructure.P, Infrastructure.P
                },
                version
        );
    }
}

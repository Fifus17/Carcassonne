package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileI extends AbstractTile {

    public TileI(int version) throws FileNotFoundException {
        super(
                "I",
                new Infrastructure[]{
                        Infrastructure.CB, Infrastructure.C, Infrastructure.CB,
                        Infrastructure.C, Infrastructure.P, Infrastructure.P,
                        Infrastructure.CB, Infrastructure.P, Infrastructure.P
                },
                version
        );
    }
}

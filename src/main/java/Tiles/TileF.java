package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileF extends AbstractTile {

    public TileF() throws FileNotFoundException {
        super(
                "F",
                new Infrastructure[]{
                        Infrastructure.CB, Infrastructure.P, Infrastructure.CB,
                        Infrastructure.C, Infrastructure.S, Infrastructure.C,
                        Infrastructure.CB, Infrastructure.P, Infrastructure.CB
                }
        );
    }
}

package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileQ extends AbstractTile {

    public TileQ() throws FileNotFoundException {
        super(
                "Q",
                new Infrastructure[]{
                        Infrastructure.C, Infrastructure.C, Infrastructure.S,
                        Infrastructure.C, Infrastructure.C, Infrastructure.C,
                        Infrastructure.C, Infrastructure.P, Infrastructure.C
                }
        );
    }
}

package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileS extends AbstractTile {

    public TileS(int version) throws FileNotFoundException {
        super(
                "S",
                new Infrastructure[]{
                        Infrastructure.C, Infrastructure.S, Infrastructure.C,
                        Infrastructure.C, Infrastructure.G, Infrastructure.C,
                        Infrastructure.C, Infrastructure.R, Infrastructure.C
                },
                version
        );
    }
}

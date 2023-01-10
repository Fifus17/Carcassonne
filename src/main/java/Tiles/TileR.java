package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileR extends AbstractTile {

    public TileR(int version) throws FileNotFoundException {
        super(
                "R",
                new Infrastructure[]{
                        Infrastructure.C, Infrastructure.C, Infrastructure.C,
                        Infrastructure.C, Infrastructure.C, Infrastructure.C,
                        Infrastructure.C, Infrastructure.P, Infrastructure.C
                },
                version
        );
    }
}

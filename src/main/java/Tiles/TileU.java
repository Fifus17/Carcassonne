package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileU extends AbstractTile {

    public TileU(int version) throws FileNotFoundException {
        super(
                "U",
                new Infrastructure[]{
                        Infrastructure.P, Infrastructure.R, Infrastructure.P,
                        Infrastructure.P, Infrastructure.R, Infrastructure.P,
                        Infrastructure.P, Infrastructure.R, Infrastructure.P
                },
                version
        );
    }
}

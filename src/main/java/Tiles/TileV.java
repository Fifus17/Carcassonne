package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileV extends AbstractTile {

    public TileV(int version) throws FileNotFoundException {
        super(
                "V",
                new Infrastructure[]{
                        Infrastructure.P, Infrastructure.P, Infrastructure.P,
                        Infrastructure.R, Infrastructure.R, Infrastructure.P,
                        Infrastructure.P, Infrastructure.R, Infrastructure.P
                },
                version
        );
    }
}

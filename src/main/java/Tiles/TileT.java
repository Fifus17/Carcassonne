package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileT extends AbstractTile {

    public TileT() throws FileNotFoundException {
        super(
                "T",
                new Infrastructure[]{
                        Infrastructure.C, Infrastructure.C, Infrastructure.C,
                        Infrastructure.C, Infrastructure.G, Infrastructure.C,
                        Infrastructure.C, Infrastructure.R, Infrastructure.C
                }
        );
    }
}

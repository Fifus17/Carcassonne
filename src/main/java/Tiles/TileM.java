package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileM extends AbstractTile {

    public TileM(int version) throws FileNotFoundException {
        super(
                "M",
                new Infrastructure[]{
                        Infrastructure.CB, Infrastructure.C, Infrastructure.S,
                        Infrastructure.P, Infrastructure.C, Infrastructure.C,
                        Infrastructure.P, Infrastructure.P, Infrastructure.CB
                },
                version
        );
    }
}

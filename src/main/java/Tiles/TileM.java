package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;

import java.io.FileNotFoundException;

public class TileM extends AbstractTile {

    public TileM(int version) throws FileNotFoundException {
        super(
                "M",
                new Infrastructure[]{
                        Infrastructure.S, Infrastructure.C, Infrastructure.CB,
                        Infrastructure.C, Infrastructure.C, Infrastructure.P,
                        Infrastructure.CB, Infrastructure.P, Infrastructure.P
                },
                version
        );
    }
}

package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TileA extends AbstractTile {

    public TileA(int version) throws FileNotFoundException {
        super(
            "A",
                new Infrastructure[] {
                        Infrastructure.P, Infrastructure.P, Infrastructure.P,
                        Infrastructure.P, Infrastructure.M, Infrastructure.P,
                        Infrastructure.P, Infrastructure.R, Infrastructure.P
                },
                version
        );
    }
}

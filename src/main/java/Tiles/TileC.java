package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TileC extends AbstractTile {

    public TileC() throws FileNotFoundException {
        super(
                "C",
                new Infrastructure[]{
                        Infrastructure.C, Infrastructure.C, Infrastructure.C,
                        Infrastructure.C, Infrastructure.S, Infrastructure.C,
                        Infrastructure.C, Infrastructure.C, Infrastructure.C
                }
        );
    }
}

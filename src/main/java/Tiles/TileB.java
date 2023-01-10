package Tiles;

import Classes.AbstractTile;
import Enums.Infrastructure;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TileB extends AbstractTile {

    public TileB(int version) throws FileNotFoundException {
        super(
                "B",
                new Infrastructure[] {
                        Infrastructure.P, Infrastructure.P, Infrastructure.P,
                        Infrastructure.P, Infrastructure.M, Infrastructure.P,
                        Infrastructure.P, Infrastructure.P, Infrastructure.P
                },
                version
        );
    }
}

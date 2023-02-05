package gui;

import javafx.scene.Node;

public class DraggableMaker {

    private double mouseAnchorX;
    private double mouseAnchorY;

    public void makeDraggable(Node node){

        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
            System.out.println(mouseEvent.getX());
            System.out.println(mouseEvent.getSceneX());
            System.out.println(mouseEvent.getScreenX());
            System.out.println(mouseEvent.getTarget());
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
        });
    }
}
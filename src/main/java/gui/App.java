package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox box = new VBox();

//        System.out.println(getClass().getResource("gui/App.fxml"));
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("App.fxml"));
//        System.out.println("bbbb" + getClass().getClassLoader().getResource("App.fxml"));
//        Scene startScene = new Scene(root, 1280, 960);
//        primaryStage.setScene(startScene);
        primaryStage.show();
//        Map map = new Map();
//        Game game = new Game(map);
    }
}

package saveDataEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import saveDataEditor.Data.SpiritFruits;
import saveDataEditor.Data.StackableResources;
import saveDataEditor.Data.Treasures;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    private static Stage primaryStage;
    private static String filePath;
    private static String inventoryFilePath;
    private static StackableResources stackableResources;
    private static Treasures treasures;
    private static SpiritFruits spiritFruits;
    private static boolean useComma;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 460;

    private void setStage(Stage primaryStage) throws IOException {

        App.primaryStage = primaryStage;

        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("base.fxml")));
        App.primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = (bounds.getMaxX() / 2) - (int)(WIDTH/2);
        double y = (bounds.getMaxY() / 2) - (int)(HEIGHT/2);
        App.primaryStage.setX(x);
        App.primaryStage.setY(y);
        App.primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stackableResources = new StackableResources();
        treasures = new Treasures();
        spiritFruits = new SpiritFruits();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        setStage(primaryStage);
    }

    public static void main(String[] args) {

        launch(args);
    }

    public static Stage getStage() {

        return primaryStage;
    }

    public static boolean getUseComma() {

        return useComma;
    }

    public static void setUseComma(boolean val) {

        useComma = val;
    }

    public static String getFilePath() {

        return filePath;
    }

    public static String getInventoryFilePath() {

        return inventoryFilePath;
    }

    public static StackableResources getStackableResources() {

        return stackableResources;
    }

    public static Treasures getTreasures() {

        return treasures;
    }

    public static SpiritFruits getSpiritFruits(){

        return spiritFruits;
    }

    public static void setFilePath(String path) {

        filePath = path;
    }

    public static void setInventoryFilePath(String path) {

        inventoryFilePath = path;
    }
}
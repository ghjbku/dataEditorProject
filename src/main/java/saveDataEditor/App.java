package saveDataEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import saveDataEditor.ItemEntities.StackableResources;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    private static Stage primarystage;
    private static String filePath;
    private static String inventoryFilePath;
    private static StackableResources stackableResources;

    private void setstage(Stage primaryStage) throws IOException {
        final int app_stage_width = 600;
        final int app_stage_height = 400;

        primarystage = primaryStage;

        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("base.fxml")));
        primarystage.setScene(new Scene(root, app_stage_width, app_stage_height));
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = (bounds.getMaxX() / 2) - 300;
        double y = (bounds.getMaxY() / 2) - 200;
        primaryStage.setX(x);
        primaryStage.setY(y);
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stackableResources = new StackableResources();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        setstage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getstage() {
        return primarystage;
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

    public static void setFilePath(String path) {
        filePath = path;
    }

    public static void setInventoryFilePath(String path) {
        inventoryFilePath = path;
    }
}
package saveDataEditor.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import saveDataEditor.App;

import java.io.IOException;
import java.util.Objects;


public class BaseController {
    static Scene mainScene;
    static final int WIDTH = 600;
    static final int INV_HEIGHT = 513;
    static final int PLAYERDATA_HEIGHT = 460;

    @FXML
    ToolBar first_tab;
    @FXML
    ToolBar second_tab;
    @FXML
    Pane version_pane;

    @FXML
    void initialize() {
    }

    @FXML
    public void exit_button_processing(){

        System.exit(0);
    }

    private static void setTheScene(Stage stage) {
        mainScene = stage.getScene();
    }

    @FXML
    public void open_edit_player_data(){

        try{
            Stage primaryStage = App.getStage();
            setTheScene(primaryStage);
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                    getResource("player_data_stage.fxml")));
            primaryStage.setScene(new Scene(root, WIDTH, PLAYERDATA_HEIGHT));
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            double x = (bounds.getMaxX() / 2) - (int)(WIDTH/2);
            double y = (bounds.getMaxY() / 2) - (int)(PLAYERDATA_HEIGHT/2);
            primaryStage.setX(x);
            primaryStage.setY(y);
            primaryStage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void open_edit_player_inventory() throws IOException {

        Stage primaryStage = App.getStage();
        setTheScene(primaryStage);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("player_inventory_stage.fxml")));
        primaryStage.setScene(new Scene(root, WIDTH, INV_HEIGHT));
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = (bounds.getMaxX() / 2) - (int)(WIDTH/2);
        double y = (bounds.getMaxY() / 2) - (int)(PLAYERDATA_HEIGHT/2);
        primaryStage.setX(x);
        primaryStage.setY(y);
        primaryStage.show();
    }

    @FXML
    public void open_edit_player_sect() throws IOException {

        Stage primaryStage = App.getStage();
        setTheScene(primaryStage);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("player_sect_stage.fxml")));
        primaryStage.setScene(new Scene(root, WIDTH, PLAYERDATA_HEIGHT));
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = (bounds.getMaxX() / 2) - (int)(WIDTH/2);
        double y = (bounds.getMaxY() / 2) - (int)(PLAYERDATA_HEIGHT/2);
        primaryStage.setX(x);
        primaryStage.setY(y);
        primaryStage.show();
    }

    @FXML
    public void open_god_realm_editor() throws IOException {

        Stage primaryStage = App.getStage();
        setTheScene(primaryStage);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("god_realm_stage.fxml")));
        primaryStage.setScene(new Scene(root, WIDTH, INV_HEIGHT));
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = (bounds.getMaxX() / 2) - (int)(WIDTH/2);
        double y = (bounds.getMaxY() / 2) - (int)(PLAYERDATA_HEIGHT/2);
        primaryStage.setX(x);
        primaryStage.setY(y);
        primaryStage.show();
    }

    @FXML
    public void next_tab(){

        first_tab.setVisible(false);
        second_tab.setVisible(true);
    }
    @FXML
    public void back_to_last_tab(){

        first_tab.setVisible(true);
        second_tab.setVisible(false);
    }

    @FXML
    public void on_hover_version_num(){

        version_pane.setVisible(true);
    }

    @FXML
    public void on_exit_version_num(){

        version_pane.setVisible(false);
    }

    public static Scene getScene() {

        return mainScene;
    }
}

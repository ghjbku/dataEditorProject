package saveDataEditor.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import saveDataEditor.App;

import java.io.IOException;
import java.util.Objects;

/**
*@description controller class for the main window
* fxml methods are named using lower_snake_case
* class methods are named using camelCase
*/
public class BaseController {
    static final int WIDTH = 600;
    static final int INV_HEIGHT = 513;
    static final int PLAYERDATA_HEIGHT = 460;

    static Scene mainScene;

    @FXML
    ToolBar first_tab;
    @FXML
    ToolBar second_tab;
    @FXML
    Pane version_pane;
    @FXML
    Button comma_button;

    @FXML
    public void on_comma_click() {
        if (App.getUseComma()){
            App.setUseComma(false);
            comma_button.setText("Click to use Comma");
            comma_button.setStyle("");
        }else{
            App.setUseComma(true);
            comma_button.setText("now using Comma");
            comma_button.setStyle("-fx-background-color: #90EE90");
        }
    }

    @FXML
    void initialize() {
    }

    /**
    * Method to exit the program when the corresponding button is clicked
    */
    @FXML
    public void exit_button_processing() {

        System.exit(0);
    }

    /**
    * Sets the value of mainScene variable
    *@param stage the Stage object that contains the Scene we set as value of mainScene
    */
    private static void setTheScene(Stage stage) {
        mainScene = stage.getScene();
    }

     /**
    * Method to change the current scene from the main screen into the player-data editing screen
    */
    @FXML
    public void open_edit_player_data() {

        try {
            Stage primaryStage = App.getStage();
            setTheScene(primaryStage);
            
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                    getResource("player_data_stage.fxml")));
            
            //set the dimensions of the new scene
            primaryStage.setScene(new Scene(root, WIDTH, PLAYERDATA_HEIGHT));
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            
            //set the top left corner of the scene (uniform for all methods)
            double x = (bounds.getMaxX() / 2) - (int) (WIDTH / 2);
            double y = (bounds.getMaxY() / 2) - (int) (PLAYERDATA_HEIGHT / 2);
            
            primaryStage.setX(x);
            primaryStage.setY(y);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * Method to change the current scene from the main screen into the player-inventory editing screen
    */
    @FXML
    public void open_edit_player_inventory() throws IOException {

        Stage primaryStage = App.getStage();
        setTheScene(primaryStage);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("player_inventory_stage.fxml")));
        primaryStage.setScene(new Scene(root, WIDTH, INV_HEIGHT));
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = (bounds.getMaxX() / 2) - (int) (WIDTH / 2);
        double y = (bounds.getMaxY() / 2) - (int) (PLAYERDATA_HEIGHT / 2);
        primaryStage.setX(x);
        primaryStage.setY(y);
        primaryStage.show();
    }

    /**
    * Method to change the current scene from the main screen into the player-sect editing screen
    */
    @FXML
    public void open_edit_player_sect() throws IOException {

        Stage primaryStage = App.getStage();
        setTheScene(primaryStage);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("player_sect_stage.fxml")));
        primaryStage.setScene(new Scene(root, WIDTH, PLAYERDATA_HEIGHT));
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = (bounds.getMaxX() / 2) - (int) (WIDTH / 2);
        double y = (bounds.getMaxY() / 2) - (int) (PLAYERDATA_HEIGHT / 2);
        primaryStage.setX(x);
        primaryStage.setY(y);
        primaryStage.show();
    }

    /**
    * Method to change the current scene from the main screen into the god-realm editing screen
    */
    @FXML
    public void open_god_realm_editor() throws IOException {

        Stage primaryStage = App.getStage();
        setTheScene(primaryStage);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("god_realm_stage.fxml")));
        primaryStage.setScene(new Scene(root, WIDTH, INV_HEIGHT));
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = (bounds.getMaxX() / 2) - (int) (WIDTH / 2);
        double y = (bounds.getMaxY() / 2) - (int) (PLAYERDATA_HEIGHT / 2);
        primaryStage.setX(x);
        primaryStage.setY(y);
        primaryStage.show();
    }

    /**
    * Method to set the visibility of first and second tabs if the user clicks the corresponding button
    */
    @FXML
    public void next_tab() {

        first_tab.setVisible(false);
        second_tab.setVisible(true);
    }

    /**
    * Method to set the visibility of first and second tabs if the user clicks the corresponding button
    */
    @FXML
    public void back_to_last_tab() {

        first_tab.setVisible(true);
        second_tab.setVisible(false);
    }

    /**
    * The method will set the version_pane visible when the user hovers over the version numbers
    */
    @FXML
    public void on_hover_version_num() {

        version_pane.setVisible(true);
    }

    /**
    * The method will set the version_pane back to non-visible when the user is no longer hovering over the version numbers
    */
    @FXML
    public void on_exit_version_num() {

        version_pane.setVisible(false);
    }

    /**
    * Method to get the current value of mainScene
    */
    public static Scene getScene() {

        return mainScene;
    }
}

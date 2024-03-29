package saveDataEditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import saveDataEditor.App;

public class PlayerSectController {
    static Scene thescene;

    @FXML
    void initialize() {
    }

    @FXML
    public void exit_button_processing(){
        System.exit(0);
    }

    @FXML
    public void back_button_processing() {
        Stage stage = App.getStage();
        stage.setScene(BaseController.getScene());
        stage.show();
    }

    private static void setTheScene(Stage stage) {
        thescene = stage.getScene();
    }

    public static Scene getScene() {
        return thescene;
    }
}

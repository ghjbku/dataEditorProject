package saveDataEditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import saveDataEditor.App;

import java.io.*;

public class PlayerInventoryController {
    JSONArray sizeArray = null;
    JSONArray data = null;
    File playerData;

    //region basic fxml data
    @FXML
    Label help_text_file_open;
    @FXML
    Label current_path_label;
    @FXML
    Label current_size_label;
    @FXML
    Pane data_pane;
    //endregion

    @FXML
    void initialize() {
        String filePath = App.getInventoryFilePath();
        if (!(filePath == null)) {
            playerData = new File(filePath);
            initScreen();
        }
    }

    @FXML
    public void exit_button_processing() {
        System.exit(0);
    }

    @FXML
    public void back_button_processing() {
        Stage stage = App.getstage();
        stage.setScene(BaseController.getScene());
        stage.show();
    }

    @FXML
    void show_help() {
        help_text_file_open.setVisible(true);
    }

    @FXML
    void disable_help() {
        help_text_file_open.setVisible(false);
    }

    @FXML
    void open_folder() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open 0svwp.dat");
        try {
            playerData = fileChooser.showOpenDialog(App.getstage());
            App.setInventoryFilePath(playerData.getPath());
            initScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initScreen() {
        setPathLabel(playerData.getPath());
        readFile();
    }

    private void setPathLabel(String filePath) {
        current_path_label.setText("current: " + filePath);
        current_path_label.setVisible(true);
    }

    private void readFile() {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(playerData.getPath())) {

            JSONObject SaveFile = (JSONObject) parser.parse(reader);
            System.out.println(SaveFile);
            sizeArray = (JSONArray) SaveFile.get("size");
            current_size_label.setText("current size of the inventory: "+ sizeArray.get(0));
            data = (JSONArray) SaveFile.get("data");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeFile() {
        JSONObject obj = new JSONObject();
        obj.put("c2array", true);
        JSONArray arr = new JSONArray();
        arr.add(62);//amount of items in the inventory,needs to be properly calculated
        arr.add(21);//amount of properties per item, always 21
        arr.add(1);//number of rows in the json file, always 1
        obj.put("size", arr);
        obj.put("data", data);

        try (FileWriter file = new FileWriter(App.getFilePath())) {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

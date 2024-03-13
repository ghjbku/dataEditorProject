package saveDataEditor.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import jsonParser.JSONParser;
import saveDataEditor.App;
import saveDataEditor.ItemEntities.ItemEntity;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GodRealmEditorController {
    JSONArray data = null;
    ArrayList<ItemEntity> inventoryArray = new ArrayList<>();
    double invSize = 0;
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
    @FXML
    Label success_msg;
    //endregion

    //region edit related objects
    @FXML
    ListView<String> item_list;
    @FXML
    Button edit_item_button;
    @FXML
    Label inv_slot_label;
    @FXML
    Label item_name;
    @FXML
    TextField edit_item_id_field;
    @FXML
    TextField stack_amount;
    @FXML
    TextField treasure_quality;
    @FXML
    TextField plant_age;
    //endregion


    @FXML
    void initialize() {
        String filePath = App.getFilePath();
        if (filePath != null) {
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
        Stage stage = App.getStage();
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
        fileChooser.setTitle("Open 0save.dat");
        try {
            playerData = fileChooser.showOpenDialog(App.getStage());
            App.setFilePath(playerData.getPath());
            initScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void edit_selected_item() {
        ObservableList<Integer> selectedIndices = item_list.getSelectionModel().getSelectedIndices();

        for (Integer o : selectedIndices) {
            System.out.println(item_list.getItems().get(o));
        }
    }

    @FXML
    public void saveItemData() {

        writeFile();
    }

    private void initScreen() {
        setPathLabel(playerData.getPath());
        readFile();
        fillInventoryList();
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
            invSize = (double) ((JSONArray) SaveFile.get("size")).get(0);
            current_size_label.setText("current size of the inventory: " + invSize);
            data = (JSONArray) SaveFile.get("data");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //((JSONArray) ((JSONArray) data.get(5)).get(22)).get(0).toString()
    //ItemInformation itemInformation = new ItemInformation();
    private void fillInventoryList() {

    }

    private void successMsgTimer() {

        TimerTask task = new TimerTask() {
            public void run() {
                success_msg.setVisible(false);
            }
        };
        Timer timer = new Timer("Timer");
        timer.schedule(task, 1000L);
    }

    private void writeFile() {

    }

}

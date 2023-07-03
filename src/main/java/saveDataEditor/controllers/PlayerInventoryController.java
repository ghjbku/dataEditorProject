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
import org.json.simple.parser.JSONParser;
import saveDataEditor.App;
import saveDataEditor.ItemEntities.ItemEntity;
import saveDataEditor.ItemEntities.ResourceInformation;
import saveDataEditor.ItemEntities.SpiritFruitInformation;
import saveDataEditor.ItemEntities.TreasureInformation;

import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerInventoryController {
    JSONArray data = null;
    ArrayList<ItemEntity> inventoryArray = new ArrayList<>();
    long invSize = 0;
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

    @FXML
    void edit_selected_item() {
        ObservableList<Integer> selectedIndices = item_list.getSelectionModel().getSelectedIndices();

        for (Integer o : selectedIndices) {
            System.out.println(item_list.getItems().get(o));
            inv_slot_label.setText("slot: #" + o);
            edit_item_id_field.setText("" + inventoryArray.get(o).getId());

            //should find it based on id, when all items are in the Data package
            item_name.setText("name: " + (inventoryArray.get(o)).getName());
            stack_amount.setText("" + inventoryArray.get(o).getAmount());
            treasure_quality.setText("" + inventoryArray.get(o).getQuality());
            plant_age.setText("" + inventoryArray.get(o).getAge());
        }
    }

    @FXML
    public void saveItemData() {
        if(!inv_slot_label.getText().equals("slot:")) {
            System.out.println(String.format("%s , id: %s , %s, amount: %s, quality: %s , age: %s",
                    inv_slot_label.getText().replaceFirst("#", ""), edit_item_id_field.getText(), item_name.getText(), stack_amount.getText(),
                    treasure_quality.getText(), plant_age.getText()));
            int i = Integer.parseInt(inv_slot_label.getText().replaceFirst("slot: #", ""));
            //id
            ((JSONArray) ((JSONArray) data.get(i)).get(0)).set(0, Long.valueOf(edit_item_id_field.getText()));
            //amount

            if (!stack_amount.getText().equals("null")) {
                ((JSONArray) ((JSONArray) data.get(i)).get(1)).set(0, Long.valueOf(stack_amount.getText()));
            }

            System.out.println(plant_age.getText());
            if (!plant_age.getText().equals("null")) {
                System.out.println("not null");
                ((JSONArray) ((JSONArray) data.get(i)).get(3)).set(0, Long.valueOf(plant_age.getText()));
            }

            System.out.println(treasure_quality.getText());
            if (!treasure_quality.getText().equals("null")) {
                System.out.println("not null");
                ((JSONArray) ((JSONArray) data.get(i)).get(2)).set(0, Double.valueOf(treasure_quality.getText()));
            }

            System.out.println(data.get(i));
            writeFile();
        }
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
            invSize = (long) ((JSONArray) SaveFile.get("size")).get(0);
            current_size_label.setText("current size of the inventory: " + invSize);
            data = (JSONArray) SaveFile.get("data");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //((jsonParser.JSONArray) ((jsonParser.JSONArray) data.get(5)).get(22)).get(0).toString()
    //ItemInformation itemInformation = new ItemInformation();
    private void fillInventoryList() {
        for (int i = 0; i < invSize; i++) {
            Long id = (Long) ((JSONArray) ((JSONArray) data.get(i)).get(0)).get(0);
            Long amount = (Long) ((JSONArray) ((JSONArray) data.get(i)).get(1)).get(0);
            Long age = (Long) ((JSONArray) ((JSONArray) data.get(i)).get(3)).get(0);
            Double quality = Double.parseDouble(((JSONArray) ((JSONArray) data.get(i)).get(2)).get(0).toString());
            String name = "";
            Long price = 0L;

            if (App.getStackableResources().findResource(Math.toIntExact(id)) != null) {
                name = App.getStackableResources().findResource(Math.toIntExact(id)).getName();
            } else if (App.getTreasures().findResource(Math.toIntExact(id)) != null) {
                name = App.getTreasures().findResource(Math.toIntExact(id)).getName();
            } else if (App.getSpiritFruits().findResource(Math.toIntExact(id)) != null) {
                name = App.getSpiritFruits().findResource(Math.toIntExact(id)).getName();

            } else {
                name = "item not in database yet";
            }

            if (App.getStackableResources().findResource(Math.toIntExact(id)) == null) {
                if (App.getTreasures().findResource(Math.toIntExact(id)) == null) {
                    if (App.getSpiritFruits().findResource(Math.toIntExact(id)) == null) {
                        price = 0L;
                    } else {
                        price = App.getSpiritFruits().findResource(Math.toIntExact(id)).getPrice();
                    }
                } else {
                    price = App.getTreasures().findResource(Math.toIntExact(id)).getPrice();
                }
            } else {
                price = App.getStackableResources().findResource(Math.toIntExact(id)).getPrice();
            }

            System.out.println("id: " + id);
            System.out.println("amount: " + amount);
            System.out.println("name: " + name);
            System.out.println("quality: " + quality);
            System.out.println("age: " + age);

            if (id < 46) {
                inventoryArray.add(new ResourceInformation(id, name, amount, price));
            } else if (id < 91) {
                inventoryArray.add(new TreasureInformation(id, name, quality, amount, price));
            } else if (id >= 549 && id < 562) {
                inventoryArray.add(new SpiritFruitInformation(id, name, age, quality, price));
            } else inventoryArray.add(new TreasureInformation(id, name, quality, amount, price));

            item_list.getItems().add(String.format("#%s , id: %s , name: %s , quality: %s , amount: %s , price: %s",
                    i, id, name, quality, amount, price));
        }
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
        JSONObject obj = new JSONObject();
        obj.put("c2array", true);
        JSONArray arr = new JSONArray();
        arr.add(invSize);//amount of items in the inventory,needs to be properly calculated
        arr.add(21);//amount of properties per item, always 21
        arr.add(1);//number of rows in the json file, always 1
        obj.put("size", arr);
        obj.put("data", data);

        try (FileWriter file = new FileWriter(App.getInventoryFilePath())) {
            file.write(obj.toJSONString());
            success_msg.setVisible(true);
            successMsgTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

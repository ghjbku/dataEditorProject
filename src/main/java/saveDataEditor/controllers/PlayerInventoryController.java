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
import saveDataEditor.Data.FileManipulation;
import saveDataEditor.ItemEntities.ItemEntity;
import saveDataEditor.ItemEntities.ResourceInformation;
import saveDataEditor.ItemEntities.SpiritFruitInformation;
import saveDataEditor.ItemEntities.TreasureInformation;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
*@description Controller class for the player inventory scene
*/
public class PlayerInventoryController {
    final NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
    JSONArray data = null;
    ArrayList<ItemEntity> inventoryArray = new ArrayList<>();
    long invSize = 0;
    File playerData;

    //region basic fxml data
    @FXML
    Label help_text_file_open;
    @FXML
    Label comma_text;
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


    /**
     * Will set the filePath if the inventory editor window has been opened before, and the software was not closed since then
     * <p>
     * If the filepath exists, then initialize/re-initialize the screen
     */
    @FXML
    void initialize() {

        comma_text.setVisible(App.getUseComma());
        String filePath = App.getInventoryFilePath();

        if (!(filePath == null)) {
            playerData = new File(filePath);
            initScreen();
        }
    }

    /**
    * Method to stop the program when the user clicks the exit button
    */
    @FXML
    public void exit_button_processing() {

        System.exit(0);
    }

    /**
     * Will change the scene back to the main screen
     */
    @FXML
    public void back_button_processing() {

        Stage stage = App.getStage();
        stage.setScene(BaseController.getScene());
        stage.show();
    }

    /**
     * Will show the help text on hover of the fileChooser button
     */
    @FXML
    void show_help() {

        help_text_file_open.setVisible(true);
    }

    /**
     * Will hide the help text when the user is no longer hovering over the fileChooser button
     */
    @FXML
    void disable_help() {

        help_text_file_open.setVisible(false);
    }

    /**
     * Opens a file selector window to let the user select a save file
     * <p>
     * After the user clicks "open", save the file path for later and initialize the screen
     */
    @FXML
    void open_folder() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open 0svwp.dat");

        try {
            playerData = fileChooser.showOpenDialog(App.getStage());
            App.setInventoryFilePath(playerData.getPath());
            initScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * After the user clicks "Edit this item", it will copy over the data from the selected item into the data-editing textFields
     * <p>
     * so the user can edit the <b>id</b>, <b>amount</b>, <b>quality</b> and <b>age</b> of the item
     */
    @FXML
    void edit_selected_item() {

        ObservableList<Integer> selectedIndices = item_list.getSelectionModel().getSelectedIndices();

        for (Integer indexOfItem : selectedIndices) {
            System.out.println(item_list.getItems().get(indexOfItem));
            inv_slot_label.setText("slot: #" + indexOfItem);
            edit_item_id_field.setText("" + inventoryArray.get(indexOfItem).getId());

            //should find it based on id, when all items are in the Data package
            item_name.setText("name: " + (inventoryArray.get(indexOfItem)).getName());
            stack_amount.setText("" + inventoryArray.get(indexOfItem).getAmount());
            treasure_quality.setText("" + inventoryArray.get(indexOfItem).getQuality());
            plant_age.setText("" + inventoryArray.get(indexOfItem).getAge());
        }
    }

    /**
     * If there is an item selected for editing, find the correct column/member of the <b>data</b> variable with the index of the item <p>
     * and overwrite the corresponding value if the data-editing textField for that value is not null
     */
    @FXML
    public void save_item_data() {

        if (!inv_slot_label.getText().equals("slot:")) {

            try {
                System.out.printf("%s , id: %s , %s, amount: %s, quality: %s , age: %s%n",
                        inv_slot_label.getText().replaceFirst("#", ""), edit_item_id_field.getText(), item_name.getText(), stack_amount.getText(),
                        treasure_quality.getText(), plant_age.getText());

                int itemId = Integer.parseInt(inv_slot_label.getText().replaceFirst("slot: #", ""));

                //id
                ((JSONArray) ((JSONArray) data.get(itemId)).get(0)).set(0, Long.valueOf(edit_item_id_field.getText()));

                if (!stack_amount.getText().equals("null")) {
                    if (App.getUseComma()) {
                        ((JSONArray) ((JSONArray) data.get(itemId)).get(1)).set(0, nf.parse(stack_amount.getText()).doubleValue());
                    } else {
                        ((JSONArray) ((JSONArray) data.get(itemId)).get(1)).set(0, Double.valueOf(stack_amount.getText()));
                    }
                }

                if (!plant_age.getText().equals("null")) {
                    ((JSONArray) ((JSONArray) data.get(itemId)).get(3)).set(0, Long.valueOf(plant_age.getText()));
                }

                if (!treasure_quality.getText().equals("null")) {
                    if (App.getUseComma()) {
                        ((JSONArray) ((JSONArray) data.get(itemId)).get(2)).set(0,  nf.parse(treasure_quality.getText()).doubleValue());
                    }else{
                        ((JSONArray) ((JSONArray) data.get(itemId)).get(2)).set(0, Double.valueOf(treasure_quality.getText()));
                    }
                }

                writeFile();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
    * Initialization wrapper method to init the screen components
    */
    private void initScreen() {

        setPathLabel(playerData.getPath());
        readFile();
        fillInventoryList();
    }
    /**
    * Set the file path label with the value of the given parameter
    *@param filePath current path of the saveFile
    */
    private void setPathLabel(String filePath) {

        current_path_label.setText("current: " + filePath);
        current_path_label.setVisible(true);
    }

    /**
     * The method will read the data from the saveFile and initialize the variables:
     *
     * @invSize how many items are in the player inventory
     * @data the data of all items in the inventory
     * @example_json {"size":[1,3,1],"data":[ [[31],[1.0E39],[0]] ]} , where the prefix consists of [amount of items, fields in an item, number of rows in the json]
     */
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

    /**
     * The method will clear the screen components and re-fill the inventory list
     */
    private void refreshInventory() {
        item_list.getItems().clear();
        inv_slot_label.setText("slot:");
        edit_item_id_field.setText("");
        item_name.setText("name: ");
        stack_amount.setText("");
        treasure_quality.setText("");
        plant_age.setText("");
        fillInventoryList();
    }

    /**
     * The method will run through the saveFile and fill the scrolling frame called inventoryArray with every item found in the player's inventory
     */
    private void fillInventoryList() {

        for (int i = 0; i < invSize; i++) {
            long id = (Long) ((JSONArray) ((JSONArray) data.get(i)).get(0)).get(0);
            Object amountToConvert = ((JSONArray) ((JSONArray) data.get(i)).get(1)).get(0);
            double amount = 0d;

            //will convert the item amount to double based on its current type
            if (amountToConvert.getClass().getName().contains("Long")) {
                amount = ((Long) amountToConvert).doubleValue();
            } else if (amountToConvert.getClass().getName().contains("Double")) {
                amount = (Double) amountToConvert;
            }

            long age = (Long) ((JSONArray) ((JSONArray) data.get(i)).get(3)).get(0);
            double quality = Double.parseDouble(((JSONArray) ((JSONArray) data.get(i)).get(2)).get(0).toString());
            String name;
            long price;

            //get the name of the current item based on it's id
            if (App.getStackableResources().findResource(Math.toIntExact(id)) != null) {
                name = App.getStackableResources().findResource(Math.toIntExact(id)).getName();
            } else if (App.getTreasures().findResource(Math.toIntExact(id)) != null) {
                name = App.getTreasures().findResource(Math.toIntExact(id)).getName();
            } else if (App.getSpiritFruits().findResource(Math.toIntExact(id)) != null) {
                name = App.getSpiritFruits().findResource(Math.toIntExact(id)).getName();
            } else {
                name = "item not in database yet";
            }

            //get the price of the current item based on it's id
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

            //add the current item into the inventory based on it's id
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

    /**
     * The method will update the saveFile with the new data
     */
    private void writeFile() {

        try {
            FileManipulation.writeFile(invSize, data, success_msg);

            refreshInventory();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

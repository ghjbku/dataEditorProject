package saveDataEditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import saveDataEditor.App;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerDataController {
    JSONArray data = null;
    File playerData;

    //region basic fxml data
    @FXML
    Label help_text_file_open;
    @FXML
    Label current_label;
    @FXML
    Label success_msg;
    @FXML
    Pane data_pane;
    //endregion

    //region player attributes
    @FXML
    TextField player_health;
    @FXML
    TextField player_qi_sense;
    @FXML
    TextField player_attunement;
    @FXML
    TextField player_root;
    @FXML
    TextField player_talent;
    @FXML
    TextField player_chance;
    //endregion

    //region player skills
    @FXML
    TextField gathering_level;
    @FXML
    TextField mining_level;
    @FXML
    TextField alchemy_level;
    @FXML
    TextField forging_level;
    @FXML
    TextField planting_level;
    @FXML
    TextField taming_level;
    @FXML
    TextField talisman_level;
    //endregion

    @FXML
    void initialize() {
        String filePath = App.getFilePath();
        if(!(filePath == null)){
            playerData = new File(filePath);
            initScreen();
        }
    }

    private void initScreen(){
        setPathLabel(playerData.getPath());
        readFile();
        setGuiData();
    }

    private void setPathLabel(String filePath){
        current_label.setText("current:"+filePath);
        current_label.setVisible(true);
        setDataPaneVisibility();
    }

    private void setDataPaneVisibility(){
        if (playerData != null) {
            data_pane.setDisable(false);
            return;
        }
        data_pane.setDisable(true);
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
        fileChooser.setTitle("Open xsave.dat");
        try {
            playerData = fileChooser.showOpenDialog(App.getstage());
            App.setFilePath(playerData.getPath());
            initScreen();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setGuiData(){
        final DecimalFormat df = new DecimalFormat("0.000");

        //region attributes
        Double qiSense = Double.parseDouble(((JSONArray)((JSONArray)data.get(5)).get(22)).get(0).toString());
        Double attunement =  Double.parseDouble(((JSONArray)((JSONArray)data.get(6)).get(22)).get(0).toString());
        Double rootAndBones = Double.parseDouble(((JSONArray)((JSONArray)data.get(7)).get(22)).get(0).toString());
        Double talent = Double.parseDouble(((JSONArray)((JSONArray)data.get(8)).get(22)).get(0).toString());
        Double chance = Double.parseDouble(((JSONArray)((JSONArray)data.get(9)).get(22)).get(0).toString());

        player_qi_sense.setText(df.format(qiSense));
        player_attunement.setText(df.format(attunement));
        player_root.setText(df.format(rootAndBones));
        player_talent.setText(df.format(talent));
        player_chance.setText(df.format(chance));
        //endregion

        //region skills
        Long gathering = (Long)((JSONArray)((JSONArray)data.get(23)).get(22)).get(0);
        Long mining = (Long)((JSONArray)((JSONArray)data.get(25)).get(22)).get(0);
        Long alchemy = (Long)((JSONArray)((JSONArray)data.get(29)).get(22)).get(0);
        Long forging = (Long)((JSONArray)((JSONArray)data.get(27)).get(22)).get(0);
        Long planting = (Long)((JSONArray)((JSONArray)data.get(73)).get(22)).get(0);
        Long taming = (Long)((JSONArray)((JSONArray)data.get(31)).get(22)).get(0);
        //Integer talisman = (Integer)((JSONArray)((JSONArray)data.get(2)).get(22)).get(0);

        gathering_level.setText(gathering.toString());
        mining_level.setText(mining.toString());
        alchemy_level.setText(alchemy.toString());
        forging_level.setText(forging.toString());
        planting_level.setText(planting.toString());
        taming_level.setText(taming.toString());
        //talisman_level.setText(talisman.toString());
        //endregion
    }

    private void readFile() {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(playerData.getPath())) {

            JSONObject SaveFile = (JSONObject) parser.parse(reader);
            System.out.println(SaveFile);
            data = (JSONArray) SaveFile.get("data");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void save_data() {
        //region attributes
        ((JSONArray)((JSONArray)data.get(5)).get(22)).set(0,Double.valueOf(player_qi_sense.getText()));
        ((JSONArray)((JSONArray)data.get(6)).get(22)).set(0,Double.valueOf(player_attunement.getText()));
        ((JSONArray)((JSONArray)data.get(7)).get(22)).set(0,Double.valueOf(player_root.getText()));
        ((JSONArray)((JSONArray)data.get(8)).get(22)).set(0,Double.valueOf(player_talent.getText()));
        ((JSONArray)((JSONArray)data.get(9)).get(22)).set(0,Double.valueOf(player_chance.getText()));
        //endregion

        //region skills
        ((JSONArray)((JSONArray)data.get(23)).get(22)).set(0,Long.valueOf(gathering_level.getText()));
        ((JSONArray)((JSONArray)data.get(25)).get(22)).set(0,Long.valueOf(mining_level.getText()));
        ((JSONArray)((JSONArray)data.get(29)).get(22)).set(0,Long.valueOf(alchemy_level.getText()));
        ((JSONArray)((JSONArray)data.get(27)).get(22)).set(0,Long.valueOf(forging_level.getText()));
        ((JSONArray)((JSONArray)data.get(73)).get(22)).set(0,Long.valueOf(planting_level.getText()));
        ((JSONArray)((JSONArray)data.get(31)).get(22)).set(0,Long.valueOf(taming_level.getText()));
        //endregion

        writeFile();
    }

    private void writeFile() {
        JSONObject obj = new JSONObject();
        obj.put("c2array",true);
            JSONArray arr = new JSONArray();
                arr.add(180);
                arr.add(50);
                arr.add(1);
        obj.put("size",arr);
        obj.put("data",data);

        try (FileWriter file = new FileWriter(App.getFilePath())) {
            file.write(obj.toJSONString());
            success_msg.setVisible(true);
            timerthing();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //it will hide the success message after 1 sec
    private void timerthing(){
        TimerTask task = new TimerTask() {
            public void run() {
                success_msg.setVisible(false);
            }
        };
        Timer timer = new Timer("Timer");
        timer.schedule(task, 1000L);
    }

    @FXML
    public void exit_button_processing(){
        System.exit(0);
    }

    @FXML
    public void back_button_processing() {
        Stage stage = App.getstage();
        stage.setScene(BaseController.getScene());
        stage.show();
    }
}

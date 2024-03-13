package saveDataEditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jsonParser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import saveDataEditor.App;
import saveDataEditor.Data.FileManipulation;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.text.DecimalFormat;

public class PlayerDataController {
    JSONArray data = null;
    File playerData;
    final DecimalFormat df = new DecimalFormat("0.000");
    final DecimalFormat df2 = new DecimalFormat("0");

    //region basic fxml data
    @FXML
    Label help_text_file_open;
    @FXML
    Label current_label;
    @FXML
    Label success_msg;
    @FXML
    Label success_msg2;
    @FXML
    Pane data_pane;
    @FXML
    Pane all_ctech;
    //endregion

    //region player attributes
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
    @FXML
    TextField spirit_stones;
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

    //region player cultivation
    @FXML
    ChoiceBox<String> ctech_cbox;
    @FXML
    TextField cbox_data;
    @FXML
    TextField breathing_qi;
    @FXML
    TextField sensing_qi;
    @FXML
    TextField comprehending_qi;
    @FXML
    TextField cleansing_meridians;
    @FXML
    TextField building_foundation;
    @FXML
    TextField core_form;
    @FXML
    TextField core_revolution;
    @FXML
    TextField merging_with_dao;
    @FXML
    TextField comprehending_heavens;
    @FXML
    TextField soul_condensation;
    @FXML
    TextField comprehending_emotions;
    @FXML
    TextField severing_emotions;
    @FXML
    TextField cultivating_soul;
    @FXML
    TextField emergence;
    @FXML
    TextField returning_to_emptiness;
    @FXML
    TextField shedding;
    @FXML
    TextField returning_to_simplicity;
    //endregion

    @FXML
    void initialize() {

        String filePath = App.getFilePath();
        if (filePath != null) {
            playerData = new File(filePath);
            initScreen();
        }
    }

    private void initScreen() {

        try {
            data = FileManipulation.readFile(playerData);

            if (data == null) {
                throw new Exception("couldn't fetch data from the save file");
            } else {
                setPathLabel(playerData.getPath());
                setGuiData();
                initCBox();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPathLabel(String filePath) {

        current_label.setText("current: " + filePath);
        current_label.setVisible(true);
        setDataPaneVisibility();
    }

    private void setDataPaneVisibility() {

        if (playerData != null) {
            data_pane.setDisable(false);
            return;
        }
        data_pane.setDisable(true);
    }

    private void initCBox() {

        ctech_cbox.getItems().addAll("breathing_qi", "sensing_qi", "comprehending_qi", "cleansing_meridians",
                "building_foundation", "core_form", "core_revolution", "merging_with_dao", "comprehending_heavens",
                "soul_condensation", "comprehending_emotions", "severing_emotions", "cultivating_soul", "emergence",
                "returning_to_emptiness", "shedding", "returning_to_simplicity");

        ctech_cbox.setValue("breathing_qi");
        onCboxChoice();

        ctech_cbox.setOnAction((event) -> {
                    System.out.println("   ChoiceBox.getValue(): " + ctech_cbox.getValue());
                    onCboxChoice();
                }
        );
    }

    private void setGuiData() {

        //region attributes
        Double qiSense = Double.parseDouble(((JSONArray) ((JSONArray) data.get(5)).get(22)).get(0).toString());
        Double attunement = Double.parseDouble(((JSONArray) ((JSONArray) data.get(6)).get(22)).get(0).toString());
        Double rootAndBones = Double.parseDouble(((JSONArray) ((JSONArray) data.get(7)).get(22)).get(0).toString());
        Double talent = Double.parseDouble(((JSONArray) ((JSONArray) data.get(8)).get(22)).get(0).toString());
        Double chance = Double.parseDouble(((JSONArray) ((JSONArray) data.get(9)).get(22)).get(0).toString());
        Double sStones = Double.parseDouble(((JSONArray) ((JSONArray) data.get(17)).get(22)).get(0).toString());

        player_qi_sense.setText(df.format(qiSense));
        player_attunement.setText(df.format(attunement));
        player_root.setText(df.format(rootAndBones));
        player_talent.setText(df.format(talent));
        player_chance.setText(df.format(chance));
        spirit_stones.setText(df.format(sStones));

        //endregion

        //region skills
        Double gathering = (Double) ((JSONArray) ((JSONArray) data.get(23)).get(22)).get(0);
        Double mining = (Double) ((JSONArray) ((JSONArray) data.get(25)).get(22)).get(0);
        Double alchemy = (Double) ((JSONArray) ((JSONArray) data.get(29)).get(22)).get(0);
        Double forging = (Double) ((JSONArray) ((JSONArray) data.get(27)).get(22)).get(0);
        Double planting = (Double) ((JSONArray) ((JSONArray) data.get(73)).get(22)).get(0);
        Double taming = (Double) ((JSONArray) ((JSONArray) data.get(31)).get(22)).get(0);
        //Integer talisman = (Integer)((jsonParser.JSONArray)((jsonParser.JSONArray)data.get(2)).get(22)).get(0);

        gathering_level.setText(df2.format(gathering));
        mining_level.setText(df2.format(mining));
        alchemy_level.setText(df2.format(alchemy));
        forging_level.setText(df2.format(forging));
        planting_level.setText(df2.format(planting));
        taming_level.setText(df2.format(taming));
        //talisman_level.setText(talisman.toString());
        //endregion

        //region cultivation
        Double breathing = (Double) ((JSONArray) ((JSONArray) data.get(1)).get(1)).get(0);
        Double sensing = (Double) ((JSONArray) ((JSONArray) data.get(2)).get(1)).get(0);
        Double compr_qi = (Double) ((JSONArray) ((JSONArray) data.get(3)).get(1)).get(0);
        Double cleansing = (Double) ((JSONArray) ((JSONArray) data.get(4)).get(1)).get(0);
        Double building = (Double) ((JSONArray) ((JSONArray) data.get(5)).get(1)).get(0);
        Double core_f = (Double) ((JSONArray) ((JSONArray) data.get(6)).get(1)).get(0);
        Double core_r = (Double) ((JSONArray) ((JSONArray) data.get(7)).get(1)).get(0);
        Double compr_h = (Double) ((JSONArray) ((JSONArray) data.get(8)).get(1)).get(0);
        Double soul_cond = (Double) ((JSONArray) ((JSONArray) data.get(9)).get(1)).get(0);
        Double compr_emo = (Double) ((JSONArray) ((JSONArray) data.get(10)).get(1)).get(0);
        Double severing = (Double) ((JSONArray) ((JSONArray) data.get(11)).get(1)).get(0);
        Double cult_soul = (Double) ((JSONArray) ((JSONArray) data.get(12)).get(1)).get(0);
        Double emerg = (Double) ((JSONArray) ((JSONArray) data.get(13)).get(1)).get(0);
        Double return_e = (Double) ((JSONArray) ((JSONArray) data.get(14)).get(1)).get(0);
        Double merging = (Double) ((JSONArray) ((JSONArray) data.get(15)).get(1)).get(0);
        Double shed = (Double) ((JSONArray) ((JSONArray) data.get(16)).get(1)).get(0);
        Double return_s = (Double) ((JSONArray) ((JSONArray) data.get(17)).get(1)).get(0);

        breathing_qi.setText(df2.format(breathing));
        sensing_qi.setText(df2.format(sensing));
        comprehending_qi.setText(df2.format(compr_qi));
        cleansing_meridians.setText(df2.format(cleansing));
        building_foundation.setText(df2.format(building));
        core_form.setText(df2.format(core_f));
        core_revolution.setText(df2.format(core_r));
        comprehending_heavens.setText(df2.format(compr_h));
        soul_condensation.setText(df2.format(soul_cond));
        comprehending_emotions.setText(df2.format(compr_emo));
        severing_emotions.setText(df2.format(severing));
        cultivating_soul.setText(df2.format(cult_soul));
        emergence.setText(df2.format(emerg));
        returning_to_emptiness.setText(df2.format(return_e));
        merging_with_dao.setText(df2.format(merging));
        shedding.setText(df2.format(shed));
        returning_to_simplicity.setText(df2.format(return_s));
        //endregion

    }

    private void saveBaseAttrs() {

        //region attributes
        ((JSONArray) ((JSONArray) data.get(5)).get(22)).set(0, Double.valueOf(player_qi_sense.getText()));
        ((JSONArray) ((JSONArray) data.get(6)).get(22)).set(0, Double.valueOf(player_attunement.getText()));
        ((JSONArray) ((JSONArray) data.get(7)).get(22)).set(0, Double.valueOf(player_root.getText()));
        ((JSONArray) ((JSONArray) data.get(8)).get(22)).set(0, Double.valueOf(player_talent.getText()));
        ((JSONArray) ((JSONArray) data.get(9)).get(22)).set(0, Double.valueOf(player_chance.getText()));
        ((JSONArray) ((JSONArray) data.get(17)).get(22)).set(0, Double.valueOf(spirit_stones.getText()));
        //endregion

        //region skills
        ((JSONArray) ((JSONArray) data.get(23)).get(22)).set(0, Double.valueOf(gathering_level.getText()));
        ((JSONArray) ((JSONArray) data.get(25)).get(22)).set(0, Double.valueOf(mining_level.getText()));
        ((JSONArray) ((JSONArray) data.get(29)).get(22)).set(0, Double.valueOf(alchemy_level.getText()));
        ((JSONArray) ((JSONArray) data.get(27)).get(22)).set(0, Double.valueOf(forging_level.getText()));
        ((JSONArray) ((JSONArray) data.get(73)).get(22)).set(0, Double.valueOf(planting_level.getText()));
        ((JSONArray) ((JSONArray) data.get(31)).get(22)).set(0, Double.valueOf(taming_level.getText()));
        //endregion
    }

    private void onCboxChoice() {

        Object breathing = ((JSONArray) ((JSONArray) data.get(1)).get(1)).get(0);
        Object sensing = ((JSONArray) ((JSONArray) data.get(2)).get(1)).get(0);
        Object compr_qi = ((JSONArray) ((JSONArray) data.get(3)).get(1)).get(0);
        Object cleansing = ((JSONArray) ((JSONArray) data.get(4)).get(1)).get(0);
        Object building = ((JSONArray) ((JSONArray) data.get(5)).get(1)).get(0);
        Object core_f = ((JSONArray) ((JSONArray) data.get(6)).get(1)).get(0);
        Object core_r = ((JSONArray) ((JSONArray) data.get(7)).get(1)).get(0);
        Object compr_h = ((JSONArray) ((JSONArray) data.get(8)).get(1)).get(0);
        Object soul_cond = ((JSONArray) ((JSONArray) data.get(9)).get(1)).get(0);
        Object compr_emo = ((JSONArray) ((JSONArray) data.get(10)).get(1)).get(0);
        Object severing = ((JSONArray) ((JSONArray) data.get(11)).get(1)).get(0);
        Object cult_soul = ((JSONArray) ((JSONArray) data.get(12)).get(1)).get(0);
        Object emerg = ((JSONArray) ((JSONArray) data.get(13)).get(1)).get(0);
        Object return_e = ((JSONArray) ((JSONArray) data.get(14)).get(1)).get(0);
        Object merging = ((JSONArray) ((JSONArray) data.get(15)).get(1)).get(0);
        Object shed = ((JSONArray) ((JSONArray) data.get(16)).get(1)).get(0);
        Object return_s = ((JSONArray) ((JSONArray) data.get(17)).get(1)).get(0);

        switch (ctech_cbox.getValue()) {
            case "breathing_qi":
                cbox_data.setText(df2.format(breathing));
                break;
            case "sensing_qi":
                cbox_data.setText(df2.format(sensing));
                break;
            case "comprehending_qi":
                cbox_data.setText(df2.format(compr_qi));
                break;
            case "cleansing_meridians":
                cbox_data.setText(df2.format(cleansing));
                break;
            case "building_foundation":
                cbox_data.setText(df2.format(building));
                break;
            case "core_form":
                cbox_data.setText(df2.format(core_f));
                break;
            case "core_revolution":
                cbox_data.setText(df2.format(core_r));
                break;
            case "comprehending_heavens":
                cbox_data.setText(df2.format(compr_h));
                break;
            case "soul_condensation":
                cbox_data.setText(df2.format(soul_cond));
                break;
            case "comprehending_emotions":
                cbox_data.setText(df2.format(compr_emo));
                break;
            case "severing_emotions":
                cbox_data.setText(df2.format(severing));
                break;
            case "cultivating_soul":
                cbox_data.setText(df2.format(cult_soul));
                break;
            case "emergence":
                cbox_data.setText(df2.format(emerg));
                break;
            case "returning_to_emptiness":
                cbox_data.setText(df2.format(return_e));
                break;
            case "merging_with_dao":
                cbox_data.setText(df2.format(merging));
                break;
            case "shedding":
                cbox_data.setText(df2.format(shed));
                break;
            case "returning_to_simplicity":
                cbox_data.setText(df2.format(return_s));
                break;
            default:
                cbox_data.setText("no value");
        }
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
    void show_all_techs() {

        setGuiData();
        all_ctech.setVisible(true);
        data_pane.setEffect(new GaussianBlur(18.25));
    }

    @FXML
    void hide_all_techs() {

        onCboxChoice();
        all_ctech.setVisible(false);
        data_pane.setEffect(null);
    }

    @FXML
    void open_folder() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open xsave.dat");
        try {
            playerData = fileChooser.showOpenDialog(App.getStage());

            if (playerData != null) {
                App.setFilePath(playerData.getPath());
                initScreen();
            } else {
                throw new Exception("no file was picked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void save_data() {

        saveBaseAttrs();

        //region cultivation
        ((JSONArray) ((JSONArray) data.get(1)).get(1)).set(0, Double.valueOf(breathing_qi.getText()));
        ((JSONArray) ((JSONArray) data.get(2)).get(1)).set(0, Double.valueOf(sensing_qi.getText()));
        ((JSONArray) ((JSONArray) data.get(3)).get(1)).set(0, Double.valueOf(comprehending_qi.getText()));
        ((JSONArray) ((JSONArray) data.get(4)).get(1)).set(0, Double.valueOf(cleansing_meridians.getText()));
        ((JSONArray) ((JSONArray) data.get(5)).get(1)).set(0, Double.valueOf(building_foundation.getText()));
        ((JSONArray) ((JSONArray) data.get(6)).get(1)).set(0, Double.valueOf(core_form.getText()));
        ((JSONArray) ((JSONArray) data.get(7)).get(1)).set(0, Double.valueOf(core_revolution.getText()));
        ((JSONArray) ((JSONArray) data.get(8)).get(1)).set(0, Double.valueOf(comprehending_heavens.getText()));
        ((JSONArray) ((JSONArray) data.get(9)).get(1)).set(0, Double.valueOf(soul_condensation.getText()));
        ((JSONArray) ((JSONArray) data.get(10)).get(1)).set(0, Double.valueOf(comprehending_emotions.getText()));
        ((JSONArray) ((JSONArray) data.get(11)).get(1)).set(0, Double.valueOf(severing_emotions.getText()));
        ((JSONArray) ((JSONArray) data.get(12)).get(1)).set(0, Double.valueOf(cultivating_soul.getText()));
        ((JSONArray) ((JSONArray) data.get(13)).get(1)).set(0, Double.valueOf(emergence.getText()));
        ((JSONArray) ((JSONArray) data.get(14)).get(1)).set(0, Double.valueOf(returning_to_emptiness.getText()));
        ((JSONArray) ((JSONArray) data.get(15)).get(1)).set(0, Double.valueOf(merging_with_dao.getText()));
        ((JSONArray) ((JSONArray) data.get(16)).get(1)).set(0, Double.valueOf(shedding.getText()));
        ((JSONArray) ((JSONArray) data.get(17)).get(1)).set(0, Double.valueOf(returning_to_simplicity.getText()));
        //endregion

        FileManipulation.writeFile(data, false, success_msg, success_msg2);
    }

    @FXML
    void save_specific_data() {

        saveBaseAttrs();

        if (ctech_cbox.getValue() != null) {

            switch (ctech_cbox.getValue()) {
                case "breathing_qi":
                    ((JSONArray) ((JSONArray) data.get(1)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "sensing_qi":
                    ((JSONArray) ((JSONArray) data.get(2)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "comprehending_qi":
                    ((JSONArray) ((JSONArray) data.get(3)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "cleansing_meridians":
                    ((JSONArray) ((JSONArray) data.get(4)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "building_foundation":
                    ((JSONArray) ((JSONArray) data.get(5)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "core_form":
                    ((JSONArray) ((JSONArray) data.get(6)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "core_revolution":
                    ((JSONArray) ((JSONArray) data.get(7)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "comprehending_heavens":
                    ((JSONArray) ((JSONArray) data.get(8)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "soul_condensation":
                    ((JSONArray) ((JSONArray) data.get(9)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "comprehending_emotions":
                    ((JSONArray) ((JSONArray) data.get(10)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "severing_emotions":
                    ((JSONArray) ((JSONArray) data.get(11)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "cultivating_soul":
                    ((JSONArray) ((JSONArray) data.get(12)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "emergence":
                    ((JSONArray) ((JSONArray) data.get(13)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "returning_to_emptiness":
                    ((JSONArray) ((JSONArray) data.get(14)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "merging_with_dao":
                    ((JSONArray) ((JSONArray) data.get(15)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "shedding":
                    ((JSONArray) ((JSONArray) data.get(16)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                case "returning_to_simplicity":
                    ((JSONArray) ((JSONArray) data.get(17)).get(1)).set(0, Double.valueOf(cbox_data.getText()));
                    break;
                default:
                    System.out.println("there is no such cultivation tech");
                    return;
            }
        }

        FileManipulation.writeFile(data, true, success_msg, success_msg2);
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
}

package saveDataEditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import saveDataEditor.App;
import saveDataEditor.Data.FileManipulation;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
*@description Controller class for Player Data scene
* fxml methods and variables are named using lower_snake_case
* class methods and variables are named using camelCase
*/
public class PlayerDataController {
    JSONArray data = null;
    File playerData;
    final DecimalFormat df = new DecimalFormat("0.000");
    final DecimalFormat df2 = new DecimalFormat("0");
    final NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);

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

    /**
    * Init method to fetch the filepath, and set the screen data if the path exists
    * The method is used when the user already visited this scene, but went back to the main scene for some reason
    * The method will run <b>after the fxml variables are created, hence better to use than a class constructor, as a constructor would not see the fxml fields</b>
    */
    @FXML
    void initialize() {

        String filePath = App.getFilePath();
        if (filePath != null) {
            playerData = new File(filePath);
            initScreen();
        }
    }

     /**
    * The method will read the saveFile that the user has picked, and if the read was successful it will set the data for the screen components
    */
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

    /**
    * Will set the value of current_label to be the filePath given as parameter
    *@param filePath the path to the saveFile
    */
    private void setPathLabel(String filePath) {

        current_label.setText("current: " + filePath);
        current_label.setVisible(true);
        setDataPaneVisibility();
    }

    /**
    * The method will check if the saveFile is successfully read, and if so it will enable the screen components, otherwise it will disable them
    */
    private void setDataPaneVisibility() {

        if (playerData != null) {
            data_pane.setDisable(false);
            return;
        }
        data_pane.setDisable(true);
    }

    /**
    * Method to initialize the choicebox and set a default value to be the first item
    * will also set a listener to run the onCboxChoice method when a value is selected
    */
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

    /**
    * The method will fetch the data from the saveFile and fill the screen components with the corresponding values
    */
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

    /**
    * The method will fetch the data from the screen components and overwrite the corresponding array field in the data variable (used as an intermediary to edit the save file)
    */
    private void saveBaseAttrs() {

        if(App.getUseComma()){
            try {
                //region attributes
                ((JSONArray) ((JSONArray) data.get(5)).get(22)).set(0, nf.parse(player_qi_sense.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(6)).get(22)).set(0, nf.parse(player_attunement.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(7)).get(22)).set(0, nf.parse(player_root.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(8)).get(22)).set(0, nf.parse(player_talent.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(9)).get(22)).set(0, nf.parse(player_chance.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(17)).get(22)).set(0, nf.parse(spirit_stones.getText()).doubleValue());
                //endregion

                //region skills
                ((JSONArray) ((JSONArray) data.get(23)).get(22)).set(0, nf.parse(gathering_level.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(25)).get(22)).set(0, nf.parse(mining_level.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(29)).get(22)).set(0, nf.parse(alchemy_level.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(27)).get(22)).set(0, nf.parse(forging_level.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(73)).get(22)).set(0, nf.parse(planting_level.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(31)).get(22)).set(0, nf.parse(taming_level.getText()).doubleValue());
                //endregion
            }catch(Exception e){
                e.printStackTrace();
            }

        }else {
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
    }

    /**
    * Method that will run when the user selects an item from the choicebox
    * It will initialize the cbox_data textField with the value from the data variable
    */
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

    /**
    * The method will display an example path to the user when they hover over the file opening button
    */
    @FXML
    void show_help() {
        help_text_file_open.setVisible(true);
    }

    /**
    * The method will hide the example path when the user is no longer hovering over the file opening button
    */
    @FXML
    void disable_help() {
        help_text_file_open.setVisible(false);
    }

    /**
    * The method will hide the choicebox and instead show every cultivationTechnique screen component so the user can modify multiple values at the same time
    */
    @FXML
    void show_all_techs() {

        setGuiData();
        all_ctech.setVisible(true);
        data_pane.setEffect(new GaussianBlur(18.25));
    }

    /**
    * The method will hide all cultivation technique screen components and instead show the choicebox for more simplicity
    */
    @FXML
    void hide_all_techs() {

        onCboxChoice();
        all_ctech.setVisible(false);
        data_pane.setEffect(null);
    }

    /**
    * The method will open a fileChooser window for the user, where they can select a saveFile
    * if the file is successfully selected, we init the screen, otherwise we thrown an exception
    */
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

    /**
    * Method to fetch all data from the screen and save it into the corresponding field in the data variable
    */
    @FXML
    void save_data() {

        saveBaseAttrs();

        if (App.getUseComma()){
            try{
                //region cultivation
                ((JSONArray) ((JSONArray) data.get(1)).get(1)).set(0, nf.parse(breathing_qi.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(2)).get(1)).set(0, nf.parse(sensing_qi.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(3)).get(1)).set(0, nf.parse(comprehending_qi.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(4)).get(1)).set(0, nf.parse(cleansing_meridians.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(5)).get(1)).set(0, nf.parse(building_foundation.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(6)).get(1)).set(0, nf.parse(core_form.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(7)).get(1)).set(0, nf.parse(core_revolution.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(8)).get(1)).set(0, nf.parse(comprehending_heavens.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(9)).get(1)).set(0, nf.parse(soul_condensation.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(10)).get(1)).set(0, nf.parse(comprehending_emotions.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(11)).get(1)).set(0, nf.parse(severing_emotions.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(12)).get(1)).set(0, nf.parse(cultivating_soul.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(13)).get(1)).set(0, nf.parse(emergence.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(14)).get(1)).set(0, nf.parse(returning_to_emptiness.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(15)).get(1)).set(0, nf.parse(merging_with_dao.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(16)).get(1)).set(0, nf.parse(shedding.getText()).doubleValue());
                ((JSONArray) ((JSONArray) data.get(17)).get(1)).set(0, nf.parse(returning_to_simplicity.getText()).doubleValue());
                //endregion
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
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
        }
        FileManipulation.writeFile(data, false, success_msg, success_msg2);
    }

    /**
    * Method to fetch all base attributes and the selected value from the choicebox and save the new values into the data variable
    */
    @FXML
    void save_specific_data() {

        saveBaseAttrs();

        if (ctech_cbox.getValue() != null) {

            if (App.getUseComma()) {
                try {
                    switch (ctech_cbox.getValue()) {
                        case "breathing_qi":
                            ((JSONArray) ((JSONArray) data.get(1)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "sensing_qi":
                            ((JSONArray) ((JSONArray) data.get(2)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "comprehending_qi":
                            ((JSONArray) ((JSONArray) data.get(3)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "cleansing_meridians":
                            ((JSONArray) ((JSONArray) data.get(4)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "building_foundation":
                            ((JSONArray) ((JSONArray) data.get(5)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "core_form":
                            ((JSONArray) ((JSONArray) data.get(6)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "core_revolution":
                            ((JSONArray) ((JSONArray) data.get(7)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "comprehending_heavens":
                            ((JSONArray) ((JSONArray) data.get(8)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "soul_condensation":
                            ((JSONArray) ((JSONArray) data.get(9)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "comprehending_emotions":
                            ((JSONArray) ((JSONArray) data.get(10)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "severing_emotions":
                            ((JSONArray) ((JSONArray) data.get(11)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "cultivating_soul":
                            ((JSONArray) ((JSONArray) data.get(12)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "emergence":
                            ((JSONArray) ((JSONArray) data.get(13)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "returning_to_emptiness":
                            ((JSONArray) ((JSONArray) data.get(14)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "merging_with_dao":
                            ((JSONArray) ((JSONArray) data.get(15)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "shedding":
                            ((JSONArray) ((JSONArray) data.get(16)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        case "returning_to_simplicity":
                            ((JSONArray) ((JSONArray) data.get(17)).get(1)).set(0, nf.parse(cbox_data.getText()).doubleValue());
                            break;
                        default:
                            System.out.println("there is no such cultivation tech");
                            return;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
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
        }

        FileManipulation.writeFile(data, true, success_msg, success_msg2);
    }

    /**
    * Method to stop the program when the user clicks the exit button
    */
    @FXML
    public void exit_button_processing() {
        System.exit(0);
    }

    /**
    * Method to close the current scene and open the main menu scene
    */
    @FXML
    public void back_button_processing() {

        Stage stage = App.getStage();
        stage.setScene(BaseController.getScene());
        stage.show();
    }
}

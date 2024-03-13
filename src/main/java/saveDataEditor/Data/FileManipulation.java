package saveDataEditor.Data;

import javafx.scene.control.Label;
import jsonParser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import saveDataEditor.App;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class FileManipulation {

    public static JSONArray readFile(File playerData) {

        JSONArray data = null;
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(playerData.getPath())) {

            JSONObject SaveFile = (JSONObject) parser.parse(reader);
            System.out.println("save file loaded");
            data = (JSONArray) SaveFile.get("data");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  data;
    }

    /**
     * the method will update the saveFile with the new data
     */
    public static void writeFile(long invSize,JSONArray data,Label success_msg) {

        JSONObject saveFile = new JSONObject();
        saveFile.put("c2array", true);

        JSONArray jsonPrefix = new JSONArray();
        jsonPrefix.add(invSize);//amount of items in the inventory,needs to be properly calculated
        jsonPrefix.add(21);//amount of properties per item, always 21
        jsonPrefix.add(1);//number of rows in the json file, always 1
        saveFile.put("size", jsonPrefix);
        saveFile.put("data", data);

        try (FileWriter file = new FileWriter(App.getInventoryFilePath())) {
            file.write(saveFile.toJSONString());
            success_msg.setVisible(true);
            successMsgTimer(success_msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(JSONArray data, boolean isSingular, Label success_msg,Label success_msg2) {

        JSONObject obj = new JSONObject();
        obj.put("c2array", true);
        JSONArray arr = new JSONArray();
        arr.add(180);
        arr.add(50);
        arr.add(1);
        obj.put("size", arr);
        obj.put("data", data);

        try (FileWriter file = new FileWriter(App.getFilePath())) {
            file.write(obj.toJSONString());
            if (isSingular) {
                success_msg.setVisible(true);
            } else {
                success_msg2.setVisible(true);
            }
            successMsgTimer(success_msg,success_msg2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void successMsgTimer(Label success_msg) {

        TimerTask task = new TimerTask() {
            public void run() {
                success_msg.setVisible(false);
            }
        };
        Timer timer = new Timer("Timer");
        timer.schedule(task, 1000L);
    }

    //it will hide the success message after 1 sec
    private static void successMsgTimer(Label success_msg,Label success_msg2) {

        TimerTask task = new TimerTask() {
            public void run() {
                success_msg.setVisible(false);
                success_msg2.setVisible(false);
            }
        };
        Timer timer = new Timer("Timer");
        timer.schedule(task, 1000L);
    }
}

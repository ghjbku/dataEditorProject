package saveDataEditor.Data;

import saveDataEditor.ItemEntities.TreasureInformation;
import java.util.HashMap;
import java.util.Map;

//forged items
public class Treasures {
    private Map<Integer, TreasureInformation> rings = new HashMap<>();
    private Map<Integer, TreasureInformation> swords = new HashMap<>();
    private Map<Integer, TreasureInformation> armors = new HashMap<>();

    public Treasures() {
        rings.put(46,new TreasureInformation(46,   "Xuan Iron ring", 0d , 1L,100L));
        rings.put(47,new TreasureInformation(47,   "Frost Jade ring", 0d , 1L,619L));
        rings.put(48,new TreasureInformation(48,   "Jade ring", 0d , 1L,3833L));
        rings.put(49,new TreasureInformation(49,   "Fire Copper ring", 0d , 1L,23737L));
        rings.put(50,new TreasureInformation(50,   "Azurite ring", 0d , 1L,146977L));
        rings.put(51,new TreasureInformation(51,   "Obsidian ring", 0d , 1L,910043L));
        rings.put(52,new TreasureInformation(52,   "Bi Lan ring", 0d , 1L,5634751L));
        rings.put(53,new TreasureInformation(53,   "Langya ring", 0d , 1L,34888895L));
        rings.put(54,new TreasureInformation(54,   "Fire Crystal ring", 0d , 1L,216022846L));
        rings.put(55,new TreasureInformation(55,   "Mizhen ring", 0d , 1L,1337556524L));
        rings.put(56,new TreasureInformation(56,   "Jiutian ring", 0d , 1L,8281797452L));
        rings.put(57,new TreasureInformation(57,   "Tianshen ring", 0d , 1L,(long)5.12787E+10));
        rings.put(58,new TreasureInformation(58,   "Cloud Silver ring", 0d , 1L,(long)3.17504E+11));
        rings.put(59,new TreasureInformation(59,   "Blood Stone ring", 0d , 1L,(long)1.9659E+12));
        rings.put(60,new TreasureInformation(60,   "Star Stone ring", 0d , 1L,(long)1.21724E+13));

        swords.put(61,new TreasureInformation(61,   "Xuan Iron sword", 0d , 1L,100L));
        swords.put(62,new TreasureInformation(62,   "Frost Jade sword", 0d , 1L,619L));
        swords.put(63,new TreasureInformation(63,   "Jade sword", 0d , 1L,3833L));
        swords.put(64,new TreasureInformation(64,   "Fire Copper sword", 0d , 1L,23737L));
        swords.put(65,new TreasureInformation(65,   "Azurite sword", 0d , 1L,146977L));
        swords.put(66,new TreasureInformation(66,   "Obsidian sword", 0d , 1L,910043L));
        swords.put(67,new TreasureInformation(67,   "Bi Lan sword", 0d , 1L,5634751L));
        swords.put(68,new TreasureInformation(68,   "Langya Glass sword", 0d , 1L,34888895L));
        swords.put(69,new TreasureInformation(69,   "Fire Crystal sword", 0d , 1L,216022846L));
        swords.put(70,new TreasureInformation(70,   "Mizhen sword", 0d , 1L,1337556524L));
        swords.put(71,new TreasureInformation(71,   "Jiutian sword", 0d , 1L,8281797452L));
        swords.put(72,new TreasureInformation(72,   "Tianshen sword", 0d , 1L,(long)5.12787E+10));
        swords.put(73,new TreasureInformation(73,   "Cloud Silver sword", 0d , 1L,(long)3.17504E+11));
        swords.put(74,new TreasureInformation(74,   "Blood Stone sword", 0d , 1L,(long)1.9659E+12));
        swords.put(75,new TreasureInformation(75,   "Star Stone sword", 0d , 1L,(long)1.21724E+13));

        armors.put(76,new TreasureInformation(76,   "Xuan Iron armor", 0d , 1L,100L));
        armors.put(77,new TreasureInformation(77,   "Frost Jade armor", 0d , 1L,619L));
        armors.put(78,new TreasureInformation(88,   "Jade armor", 0d , 1L, 3833L));
        armors.put(79,new TreasureInformation(79,   "Fire Copper armor", 0d , 1L,23737L));
        armors.put(80,new TreasureInformation(80,   "Azurite armor", 0d , 1L,146977L));
        armors.put(81,new TreasureInformation(81,   "Obsidian armor", 0d , 1L,910043L));
        armors.put(82,new TreasureInformation(82,   "Bi Lan armor", 0d , 1L,5634751L));
        armors.put(83,new TreasureInformation(83,   "Langya Glass armor", 0d , 1L,34888895L));
        armors.put(84,new TreasureInformation(84,   "Fire Crystal armor", 0d , 1L,216022846L));
        armors.put(85,new TreasureInformation(85,   "Mizhen armor", 0d , 1L,1337556524L));
        armors.put(86,new TreasureInformation(86,   "Jiutian armor", 0d , 1L,8281797452L));
        armors.put(87,new TreasureInformation(87,   "Tianshen armor", 0d , 1L,(long)5.12787E+10));
        armors.put(88,new TreasureInformation(88,   "Cloud Silver armor", 0d , 1L,(long)3.17504E+11));
        armors.put(89,new TreasureInformation(89,   "Blood Stone armor", 0d , 1L,(long)1.9659E+12));
        armors.put(90,new TreasureInformation(90,   "Star Stone armor", 0d , 1L,(long)1.21724E+13));
    }

    public TreasureInformation findResource(int id){
        if(id > 90){
            return null;
        }

        if (id < 61){
           return rings.get(id);
        }else if (id < 75){
            return  swords.get(id);
        }
        return armors.get(id);
    }
}

package saveDataEditor.Data;

import saveDataEditor.ItemEntities.SpiritFruitInformation;
import saveDataEditor.ItemEntities.SpiritFruitInformation;

import java.util.HashMap;
import java.util.Map;

//forged items
public class SpiritFruits {
    private Map<Integer, SpiritFruitInformation> fruits = new HashMap<>();

    public SpiritFruits() {
        fruits.put(549,new SpiritFruitInformation(549,"Life Fruit", 0L , 0d,0L));
        fruits.put(550,new SpiritFruitInformation(550,"Ginseng", 0L , 0d,0L));
        fruits.put(551,new SpiritFruitInformation(551,"Soul Fruit", 0L , 0d,0L));
        fruits.put(552,new SpiritFruitInformation(552,"Evil Fruit", 0L , 0d,0L));
        fruits.put(553,new SpiritFruitInformation(553,"Gobi Fruit", 0L , 0d,0L));
        fruits.put(554,new SpiritFruitInformation(554,"Aura fruit", 0L , 0d,0L));
        fruits.put(555,new SpiritFruitInformation(555,"Focus Fruit", 0L , 0d,0L));
        fruits.put(556,new SpiritFruitInformation(556,"Bone Fruit", 0L , 0d,0L));
        fruits.put(557,new SpiritFruitInformation(557,"Learn Fruit", 0L , 0d,0L));
        fruits.put(558,new SpiritFruitInformation(558,"Chance Fruit", 0L , 0d,0L));
        fruits.put(559,new SpiritFruitInformation(559,"Fire Fruit", 0L , 0d,0L));
        fruits.put(560,new SpiritFruitInformation(560,"Luang Fruit", 0L , 0d,0L));
        fruits.put(561,new SpiritFruitInformation(561,"Xuan Fruit", 0L , 0d,0L));
    }

    public SpiritFruitInformation findResource(int id){
        if(id > 561 || id < 549){
            return null;
        }
        return fruits.get(id);
    }
}

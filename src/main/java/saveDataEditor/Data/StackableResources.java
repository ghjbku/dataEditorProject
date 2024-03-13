package saveDataEditor.Data;

import saveDataEditor.ItemEntities.ResourceInformation;

import java.util.HashMap;
import java.util.Map;

//resources that you can stack
public class StackableResources {
    private Map<Integer, ResourceInformation> gatheringResources = new HashMap<>();
    private Map<Integer, ResourceInformation> miningResources = new HashMap<>();
    private Map<Integer, ResourceInformation> beastCoreResources = new HashMap<>();

    public StackableResources() {
        gatheringResources.put(1, new ResourceInformation(1,"Sun Grass", 0d,1L));
        gatheringResources.put(2,new ResourceInformation(2,"Lily", 0d,6L));
        gatheringResources.put(3,new ResourceInformation(3, "Wind Leaf",0d,38L));
        gatheringResources.put(4,new ResourceInformation(4,  "Thorny Daisy", 0d,237L));
        gatheringResources.put(5,new ResourceInformation(5,  "Grass Roots", 0d,1469L));
        gatheringResources.put(6,new ResourceInformation(6,  "Jinbei Fruit", 0d,9100L));
        gatheringResources.put(7,new ResourceInformation(7,  "Ice Lotus", 0d,56347L));
        gatheringResources.put(8,new ResourceInformation(8,  "Fir Wood", 0d,348888L));
        gatheringResources.put(9,new ResourceInformation(9,  "Guan Shi Guo", 0d,2160228L));
        gatheringResources.put(10,new ResourceInformation(10,  "Longevity Seed", 0d,13375565L));
        gatheringResources.put(11,new ResourceInformation(11,  "Rose Grass", 0d,82817974L));
        gatheringResources.put(12,new ResourceInformation(12,  "Bingbing Fruit", 0d,512787069L));
        gatheringResources.put(13,new ResourceInformation(13,  "Dead Wine", 0d,3175042373L));
        gatheringResources.put(14,new ResourceInformation(14,  "Red Lotus", 0d,(long)1.9659E+10));
        gatheringResources.put(15,new ResourceInformation(15,  "Grove Root", 0d,(long)1.21724E+11));

        miningResources.put(16,new ResourceInformation(16,   "Xuan Iron", 0d,1L));
        miningResources.put(17,new ResourceInformation(17,   "Frost Jade", 0d,6L));
        miningResources.put(18,new ResourceInformation(18,   "Jadette", 0d,38L));
        miningResources.put(19,new ResourceInformation(19,   "Fire Copper", 0d,237L));
        miningResources.put(20,new ResourceInformation(20,   "Azurite", 0d,1469L));
        miningResources.put(21,new ResourceInformation(21,   "Obsidian", 0d,9100L));
        miningResources.put(22,new ResourceInformation(22,   "Lan Crystal", 0d,56347L));
        miningResources.put(23,new ResourceInformation(23,   "Langya Glass", 0d,348888L));
        miningResources.put(24,new ResourceInformation(24,   "Fire Crystal", 0d,2160228L));
        miningResources.put(25,new ResourceInformation(25,   "Mizhen Ore", 0d,13375565L));
        miningResources.put(26,new ResourceInformation(26,   "Jiutian Iron", 0d,82817974L));
        miningResources.put(27,new ResourceInformation(27,   "Tianshen Ore", 0d,512787069L));
        miningResources.put(28,new ResourceInformation(28,   "Cloud Silver", 0d,3175042373L));
        miningResources.put(29,new ResourceInformation(29,   "Blood Stone", 0d,(long)1.9659E+10));
        miningResources.put(30,new ResourceInformation(30,   "Star Stone", 0d,(long)1.21724E+11));

        beastCoreResources.put(31,new ResourceInformation(31,   "Lower Mortal Core", 0d,1L));
        beastCoreResources.put(32,new ResourceInformation(32,   "Middle Mortal Core", 0d,6L));
        beastCoreResources.put(33,new ResourceInformation(33,   "Upper Mortal Core", 0d, 38L));
        beastCoreResources.put(34,new ResourceInformation(34,   "Lower Spirit Core", 0d,237L));
        beastCoreResources.put(35,new ResourceInformation(35,   "Middle Spirit Core", 0d,1469L));
        beastCoreResources.put(36,new ResourceInformation(36,   "Upper Spirit Core", 0d,9100L));
        beastCoreResources.put(37,new ResourceInformation(37,   "Lower Xuan Core", 0d,56347L));
        beastCoreResources.put(38,new ResourceInformation(38,   "Middle Xuan Core", 0d,348888L));
        beastCoreResources.put(39,new ResourceInformation(39,   "Upper Xuan Core", 0d,2160228L));
        beastCoreResources.put(40,new ResourceInformation(40,   "Lower Immortal Core", 0d,13375565L));
        beastCoreResources.put(41,new ResourceInformation(41,   "Middle Immortal Core", 0d,82817974L));
        beastCoreResources.put(42,new ResourceInformation(42,   "Upper Immortal Core", 0d,512787069L));
        beastCoreResources.put(43,new ResourceInformation(43,   "Lower Divine Core", 0d,3175042373L));
        beastCoreResources.put(44,new ResourceInformation(44,   "Middle Divine Core", 0d,(long)1.9659E+10));
        beastCoreResources.put(45,new ResourceInformation(45,   "Upper Divine Core", 0d, (long) 1.21724E+11));
    }

    public ResourceInformation findResource(int id){
        if(id > 45){
            return null;
        }

        if (id < 16){
           return gatheringResources.get(id);
        }else if (id < 31){
            return  miningResources.get(id);
        }
        return beastCoreResources.get(id);
    }
}

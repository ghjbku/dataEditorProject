package saveDataEditor.ItemEntities;

import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Map;

//resources that you can stack
public class StackableResources {
    private Map<Integer, String> gatheringResources = new HashMap<>();
    private Map<Integer, String> miningResources = new HashMap<>();
    private Map<Integer, String> beastCoreResources = new HashMap<>();

    public StackableResources() {
        gatheringResources.put(1, "Sun Grass");
        gatheringResources.put(2, "Lily");
        gatheringResources.put(3, "Wind Leaf");
        gatheringResources.put(4, "Thorny Daisy");
        gatheringResources.put(5, "Grass Roots");
        gatheringResources.put(6, "Jinbei Fruit");
        gatheringResources.put(7, "Ice Lotus");
        gatheringResources.put(8, "Fir Wood");
        gatheringResources.put(9, "Guan Shi Guo");
        gatheringResources.put(10, "Longevity Seed");
        gatheringResources.put(11, "Rose Grass");
        gatheringResources.put(12, "Bingbing Fruit");
        gatheringResources.put(13, "Dead Wine");
        gatheringResources.put(14, "Red Lotus");
        gatheringResources.put(15, "Grove Root");

        miningResources.put(16, "Xuan Iron");
        miningResources.put(17, "Frost Jade");
        miningResources.put(18, "Jadette");
        miningResources.put(19, "Fire Copper");
        miningResources.put(20, "Azurite");
        miningResources.put(21, "Obsidian");
        miningResources.put(22, "Lan Crystal");
        miningResources.put(23, "Langya Glass");
        miningResources.put(24, "Fire Crystal");
        miningResources.put(25, "Mizhen Ore");
        miningResources.put(26, "Jiutian Iron");
        miningResources.put(27, "Tianshen Ore");
        miningResources.put(28, "Cloud Silver");
        miningResources.put(29, "Blood Stone");
        miningResources.put(30, "Star Stone");

        beastCoreResources.put(31, "Lower Mortal Core");
        beastCoreResources.put(32, "Middle Mortal Core");
        beastCoreResources.put(33, "Upper Mortal Core");
        beastCoreResources.put(34, "Lower Spirit Core");
        beastCoreResources.put(35, "Middle Spirit Core");
        beastCoreResources.put(36, "Upper Spirit Core");
        beastCoreResources.put(37, "Lower Xuan Core");
        beastCoreResources.put(38, "Middle Xuan Core");
        beastCoreResources.put(39, "Upper Xuan Core");
        beastCoreResources.put(40, "Lower Immortal Core");
        beastCoreResources.put(41, "Middle Immortal Core");
        beastCoreResources.put(42, "Upper Immortal Core");
        beastCoreResources.put(43, "Lower Divine Core");
        beastCoreResources.put(44, "Middle Divine Core");
        beastCoreResources.put(45, "Upper Divine Core");
    }

    public Map<Integer, String> getBeastCoreResources() {
        return beastCoreResources;
    }

    public Map<Integer, String> getGatheringResources() {
        return gatheringResources;
    }

    public Map<Integer, String> getMiningResources() {
        return miningResources;
    }
}

package mechanizedmadness.mechanizedcore.util;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.google.common.collect.HashBiMap;

/**
 * Used to get items and blocks based on unlocalized names.
 * 
 * @author Darkevilmac
 *
 */
public class RegistryUtils {

    public static HashBiMap<String, Block> unlocalizedBlocks = HashBiMap.create();
    public static HashBiMap<String, Item> unlocalizedItems = HashBiMap.create();

    public static void init() {
        initItems();
        initBlocks();
    }

    private static void initBlocks() {
        Iterator<?> blockIterator = Block.blockRegistry.iterator();

        while (blockIterator.hasNext()) {
            Block block = (Block) blockIterator.next();
            unlocalizedBlocks.put(block.getUnlocalizedName(), block);
        }
    }

    public static Block getBlock(String unlocalizedName) {
        return unlocalizedBlocks.get(unlocalizedName);
    }

    private static void initItems() {
        Iterator<?> itemIterator = Item.itemRegistry.iterator();

        while (itemIterator.hasNext()) {
            Item item = (Item) itemIterator.next();
            unlocalizedItems.put(item.getUnlocalizedName(), item);
        }
    }

    public static Item getItem(String unlocalizedName) {
        return unlocalizedItems.get(unlocalizedName);
    }
}

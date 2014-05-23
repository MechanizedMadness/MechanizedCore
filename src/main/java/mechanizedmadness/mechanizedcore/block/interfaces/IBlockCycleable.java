package mechanizedmadness.mechanizedcore.block.interfaces;

import net.minecraft.entity.player.EntityPlayer;

public interface IBlockCycleable {
    
    /**
     * Returns true if the item the player is holding will work to cycle the metadata.
     * 
     * @param player
     * @return
     */
    public boolean playerHasCycleItem(EntityPlayer player);
    
    /**
     * The max integer the block can cycle to.
     */
    public int[] validCycles();

}

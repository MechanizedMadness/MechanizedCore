package mechanizedmadness.mechanizedcore.tile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityMechanized extends TileEntity {

    /**
     * Owner of the block
     */
    public String ownerName;

    public TileEntityMechanized() {
    }

    @Override
    public void validate() {

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setString("ownerName", ownerName);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        ownerName = nbt.getString("ownerName");
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
            float par8, float par9) {
        return false;
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world,
     * x, y, z, entity
     */
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {

    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z,
     * side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        return meta;
    }

    /**
     * Called when a player hits the block. Args: world, x, y, z, player
     */
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (entity != null && entity instanceof EntityPlayer) {
            ownerName = ((EntityPlayer) entity).getDisplayName();
        } else {
            ownerName = "NULLPLAYER";
        }
    }

    /**
     * Called after a block is placed
     */
    public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
    }

    public void setPacketPipeline() {
    }

}

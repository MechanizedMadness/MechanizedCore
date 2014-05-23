package mechanizedmadness.mechanizedcore.block;

import java.util.ArrayList;

import mechanizedmadness.mechanizedcore.block.interfaces.IBlockCycleable;
import mechanizedmadness.mechanizedcore.block.interfaces.ISidedTexture;
import mechanizedmadness.mechanizedcore.block.interfaces.ISidedTextureNoVertical;
import mechanizedmadness.mechanizedcore.lib.Reference;
import mechanizedmadness.mechanizedcore.tile.TileEntityMechanized;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockMechanized extends BlockContainer {

    @SideOnly(Side.CLIENT)
    public String[] iconsToRegister = new String[12];
    @SideOnly(Side.CLIENT)
    public IIcon[] blockIcons = new IIcon[12];

    protected BlockMechanized() {
        super(Material.iron);
        setBlockTextureName(Reference.MOD_ID + ":stubBlock");
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
            float par8, float par9) {
        boolean returnVal = false;

        if (!world.isRemote) {
            if (world.getBlock(x, y, z) != null && world.getBlock(x, y, z) instanceof IBlockCycleable) {
                IBlockCycleable block = (IBlockCycleable) world.getBlock(x, y, z);

                if (block.playerHasCycleItem(player)) {
                    String validCyclesString = "" + block.validCycles()[0];

                    for (int i = 1; i < block.validCycles().length; i++) {
                        validCyclesString = validCyclesString + ", " + block.validCycles()[i];
                    }

                    if (!validCyclesString.contains("" + world.getBlockMetadata(x, y, z))) {
                        world.setBlockMetadataWithNotify(x, y, z, block.validCycles()[0], 2);
                    } else {
                        if (world.getBlockMetadata(x, y, z) == block.validCycles()[block.validCycles().length - 1]) {
                            world.setBlockMetadataWithNotify(x, y, z, block.validCycles()[0], 2);
                        }
                    }

                    returnVal = true;
                }
            }

        }

        if (!world.isRemote) {
            if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityMechanized) {
                if (returnVal == false) {
                    returnVal = ((TileEntityMechanized) world.getTileEntity(x, y, z)).onBlockActivated(world, x, y, z,
                            player, par6, par7, par8, par9);
                } else {
                    ((TileEntityMechanized) world.getTileEntity(x, y, z)).onBlockActivated(world, x, y, z, player,
                            par6, par7, par8, par9);
                }
            }
        }
        return returnVal;
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world,
     * x, y, z, entity
     */
    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityMechanized) {
            ((TileEntityMechanized) world.getTileEntity(x, y, z)).onEntityWalking(world, x, y, z, entity);
        }
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z,
     * side, hitX, hitY, hitZ, block metadata
     */
    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        int returnMeta = meta;
        if (!world.isRemote) {
            switch (side) {
                case 0: {
                    // Down
                    returnMeta = 0;
                }
                case 1: {
                    // Up
                    returnMeta = 2;
                }
                case 2: {
                    // North
                    returnMeta = 4;
                }
                case 3: {
                    // South
                    returnMeta = 6;
                }
                case 4: {
                    // West
                    returnMeta = 8;
                }
                case 5: {
                    // East
                    returnMeta = 10;
                }
            }

            world.setBlockMetadataWithNotify(x, y, z, returnMeta, 2);
        }

        if (!world.isRemote) {
            if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityMechanized) {
                ((TileEntityMechanized) world.getTileEntity(x, y, z)).onBlockPlaced(world, x, y, z, side, hitX, hitY,
                        hitZ, meta);
            }
        }
        return returnMeta;
    }

    /**
     * Called when a player hits the block. Args: world, x, y, z, player
     */
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isRemote) {
            if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityMechanized) {
                ((TileEntityMechanized) world.getTileEntity(x, y, z)).onBlockClicked(world, x, y, z, player);
            }
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (!world.isRemote) {
            if (world.getBlock(x, y, z) != null && world.getBlock(x, y, z) instanceof ISidedTextureNoVertical) {
                int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

                if (l == 0) {
                    world.setBlockMetadataWithNotify(x, y, z, 2, 2);
                }

                if (l == 1) {
                    world.setBlockMetadataWithNotify(x, y, z, 5, 2);
                }

                if (l == 2) {
                    world.setBlockMetadataWithNotify(x, y, z, 3, 2);
                }

                if (l == 3) {
                    world.setBlockMetadataWithNotify(x, y, z, 4, 2);
                }
            }

            if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityMechanized) {
                ((TileEntityMechanized) world.getTileEntity(x, y, z)).onBlockPlacedBy(world, x, y, z, entity, stack);
            }
        }
    }

    /**
     * Called after a block is placed
     */
    @Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int meta) {
        if (!world.isRemote) {
            if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityMechanized) {
                ((TileEntityMechanized) world.getTileEntity(x, y, z)).onPostBlockPlaced(world, x, y, z, meta);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        if (blockAccess.getBlock(x, y, z) != null && blockAccess.getBlock(x, y, z) instanceof ISidedTexture) {
            int[] returnSidesMeta0 = { 2, 0, 6, 4, 10, 8 };
            int[] returnSidesMeta1 = { 3, 1, 7, 5, 11, 9 };
            int[] returnSidesMeta2 = { 0, 2, 4, 6, 8, 10 };
            int[] returnSidesMeta3 = { 1, 3, 5, 7, 9, 11 };
            int[] returnSidesMeta4 = { 10, 8, 2, 0, 6, 4 };
            int[] returnSidesMeta5 = { 11, 9, 3, 1, 7, 5 };
            int[] returnSidesMeta6 = { 8, 10, 0, 2, 4, 6 };
            int[] returnSidesMeta7 = { 9, 11, 1, 3, 5, 7 };
            int[] returnSidesMeta8 = { 6, 4, 10, 8, 2, 0 };
            int[] returnSidesMeta9 = { 7, 5, 11, 9, 3, 1 };
            int[] returnSidesMeta10 = { 4, 6, 8, 10, 0, 2 };
            int[] returnSidesMeta11 = { 5, 7, 9, 11, 1, 3 };

            ArrayList<int[]> metaArray = new ArrayList<int[]>();

            metaArray.add(returnSidesMeta0);
            metaArray.add(returnSidesMeta1);
            metaArray.add(returnSidesMeta2);
            metaArray.add(returnSidesMeta3);
            metaArray.add(returnSidesMeta4);
            metaArray.add(returnSidesMeta5);
            metaArray.add(returnSidesMeta6);
            metaArray.add(returnSidesMeta7);
            metaArray.add(returnSidesMeta8);
            metaArray.add(returnSidesMeta9);
            metaArray.add(returnSidesMeta10);
            metaArray.add(returnSidesMeta11);

            int meta = blockAccess.getBlockMetadata(x, y, z);
            int[] returnVal = (int[]) metaArray.get(meta);

            return blockIcons[returnVal[side]];
        }

        return this.getIcon(side, blockAccess.getBlockMetadata(x, y, z));
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityMechanized();
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        super.breakBlock(world, x, y, z, block, par6);
    }

    /**
     * Use if your block implements ISidedTexture.
     * 
     * @param texturePrefix
     * @param textureNames
     * @return
     */
    @SideOnly(Side.CLIENT)
    public BlockMechanized setBlockTextures(String texturePrefix, String[] textureNames) {
        if (textureNames.length > 12) {
            System.out.println("TEXTURE ARRAY TOO LARGE!");
        } else {
            for (int i = 0; i < 12; i++) {
                iconsToRegister[i] = texturePrefix + textureNames[i];
            }
        }
        return this;
    }

}

package mechanizedmadness.mechanizedcore.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import mechanizedmadness.mechanizedcore.tile.TileProgress;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class PacketProgressTile extends AbstractPacket {

    public PacketProgressTile() {

    }

    public PacketProgressTile(int x, int y, int z, int tick, int multiple) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.tick = tick;
        this.multiple = multiple;
    }

    int x, y, z, tick, multiple;

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(x);
        buffer.writeInt(y);
        buffer.writeInt(z);
        buffer.writeInt(tick);
        buffer.writeInt(multiple);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        x = buffer.readInt();
        y = buffer.readInt();
        z = buffer.readInt();
        tick = buffer.readInt();
        multiple = buffer.readInt();
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        World world = player.worldObj;

        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileProgress) {
            ((TileProgress) world.getTileEntity(x, y, z)).clientDisplayConversionTicks = tick / multiple;
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
    }

}

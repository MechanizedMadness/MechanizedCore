package mechanizedmadness.mechanizedcore.tile;

import mechanizedmadness.mechanizedcore.network.PacketPipeline;
import mechanizedmadness.mechanizedcore.network.PacketProgressTile;

/**
 * Extend or add to your tile for furnace like progress.
 * 
 * @author Darkevilmac
 *
 */
public abstract class TileProgress extends TileEntityMechanized {

    /**
     * PacketPipeline of your mod.
     */
    public PacketPipeline packetPipeline;

    /**
     * How many ticks it takes for the progress bar to fill.
     */
    public int cookTime;
    /**
     * What your cooktime is a multiple of. Used to send packets more
     * efficiently.
     * 
     * Example: 20 * 3(<--the multiple) = 60 (3 seconds)
     */
    public int cookTimeMultiple;
    /**
     * Use this to identify if you are cooking. When set to true progress will
     * begin otherwise progress will be kept at 0.
     */
    public boolean isCooking;
    /**
     * Current conversion tick.
     */
    public int conversionTick;
    /**
     * Last conversion tick sent to the client.
     */
    public int lastConversionTickSent;
    /**
     * Use this in your gui to display the current cooking status.
     */
    public int clientDisplayConversionTicks;

    @Override
    public void updateEntity() {
        if (isCooking) {
            if (conversionTick == 0) {
                cook();
                conversionTick = cookTime;
            }
            conversionTick--;
        } else {
            conversionTick = cookTime;
        }

        if (conversionTick % cookTimeMultiple == 0) {
            if (lastConversionTickSent != conversionTick) {
                lastConversionTickSent = conversionTick;
                packetPipeline.sendToDimension(new PacketProgressTile(this.xCoord, this.yCoord, this.zCoord,
                        conversionTick, cookTimeMultiple), this.worldObj.provider.dimensionId);
            }
        }
    }

    /**
     * Do whatever it is you need to do when the progress is complete. Create
     * your product use resources etc.
     */
    public void cook() {

    }

    public abstract void setPacketPipeline();

}

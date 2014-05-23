package mechanizedmadness.mechanizedcore.client.gui.part;

import mechanizedmadness.mechanizedcore.client.gui.GuiBase;
import mechanizedmadness.mechanizedcore.lib.Reference;
import mechanizedmadness.mechanizedcore.tile.TileProgress;

public class PartProgress extends PartBase {

    /**
     * Mode of the progress bar 0 = horizontal anything else is assumed to be
     * vertical.
     */
    public int mode;
    public TileProgress tileProgress;

    public PartProgress(int posX, int posY, int mode, TileProgress tileProgress, GuiBase parentGui) {
        this.posX = posX;
        this.posY = posY;
        this.texH = 32;
        this.texW = 32;
        this.sizeX = 24;
        this.sizeY = 24;
        this.parentGui = parentGui;
        this.mc = parentGui.mc;

        this.mode = mode;
        this.tileProgress = tileProgress;

        setTexture(Reference.MOD_ID + ":textures/gui/container/part/PartProgress.png");
    }

    public PartProgress() {
    }

    @Override
    public void drawGuiBackgroundLayer(float var1, int x, int y) {
        int drawX = parentGui.textureCornerTopLeftX + posX;
        int drawY = parentGui.textureCornerTopLeftY + posY;

        int u = 0;
        int v = 0;
        int drawSizeX = 0;
        int drawSizeY = 0;

        if (mode == 0) {
            u = 8;
            drawSizeX = sizeX / tileProgress.clientDisplayConversionTicks;
            drawSizeY = 17;
        } else {
            drawSizeX = 9;
            drawSizeY = sizeY / tileProgress.clientDisplayConversionTicks;
        }

        mc.renderEngine.bindTexture(texture);
        parentGui.drawTexturedModalRect(drawX, drawY, u, v, drawSizeX, drawSizeY);
    }

    @Override
    public void drawGuiForegroundLayer(int x, int y) {

    }

    @Override
    public void drawActiveArea(int x, int y) {

    }

}

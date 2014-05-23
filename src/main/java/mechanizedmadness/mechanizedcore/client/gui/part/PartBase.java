package mechanizedmadness.mechanizedcore.client.gui.part;

import mechanizedmadness.mechanizedcore.client.gui.GuiBase;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

/**
 * 
 * @author Darkevilmac
 *
 */
public abstract class PartBase {

    public Minecraft mc;

    public int posX;
    public int posY;
    public int sizeX;
    public int sizeY;
    public int texW;
    public int texH;
    public String partName;

    public ResourceLocation texture;
    public GuiBase parentGui;
    public boolean visible;

    public PartBase(int posX, int posY, GuiBase parentGui) {
        this.posX = posX;
        this.posY = posY;
        this.texH = 256;
        this.texW = 256;
        this.parentGui = parentGui;
        this.mc = parentGui.mc;
    }

    public PartBase() {

    }

    public void drawScreen(int x, int y, float par3) {
        drawGuiBackgroundLayer(par3, x, y);
        drawGuiForegroundLayer(x, y);
        if (shouldDrawActiveArea()) {
            drawActiveArea(x, y);
        }
    }

    /**
     * Called when the mouse is clicked.
     * 
     * @param x
     * @param y
     * @param clickType
     *            0 = Left, 1 = Right
     */
    public void mouseClicked(int x, int y, int clickType) {
    }

    public abstract void drawGuiBackgroundLayer(float var1, int x, int y);

    public abstract void drawGuiForegroundLayer(int x, int y);

    public abstract void drawActiveArea(int x, int y);

    public boolean shouldDrawActiveArea() {
        int x = parentGui.mousePosX + parentGui.textureCornerTopLeftX;
        int y = parentGui.mousePosY + parentGui.textureCornerTopLeftY;

        if (x >= posX && x <= posX + sizeX) {
            if (y >= posY && y <= posY + sizeY) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the x position of the part.
     * 
     * @param posX
     * @return
     */
    public PartBase setPosX(int posX) {
        this.posX = posX;

        return this;
    }

    /**
     * Sets the y position of the part.
     * 
     * @param posY
     * @return
     */
    public PartBase setPosY(int posY) {
        this.posY = posY;

        return this;
    }

    /**
     * Sets the position of the part.
     */
    public PartBase setPos(int posX, int posY) {
        setPosX(posX);
        setPosY(posY);

        return this;
    }

    /**
     * Sets the x size of the part.
     * 
     * @param sizeX
     * @return
     */
    public PartBase setSizeX(int sizeX) {
        this.sizeX = sizeX;

        return this;
    }

    /**
     * Sets the y size of the part.
     * 
     * @param sizeY
     * @return
     */
    public PartBase setSizeY(int sizeY) {
        this.sizeY = sizeY;

        return this;
    }

    /**
     * Sets the size of the part.
     */
    public PartBase setSize(int sizeX, int sizeY) {
        setSizeX(sizeX);
        setSizeY(sizeY);

        return this;
    }

    /**
     * Set the texture of the part.
     * 
     * @param textureName
     */
    public PartBase setTexture(String textureName) {
        this.texture = new ResourceLocation(textureName);

        return this;
    }

    /**
     * Set the parent gui of the part.
     * 
     * @param parentGui
     * @return
     */
    public PartBase setParentGui(GuiBase parentGui) {
        this.parentGui = parentGui;

        return this;
    }

    /**
     * Set whether or not the part is visible
     * 
     * @param visible
     * @return
     */
    public PartBase setVisible(boolean visible) {
        this.visible = visible;

        return this;
    }

    /**
     * Whether or not the part is visible.
     */
    public boolean isVisible() {
        return visible;
    }

}

package mechanizedmadness.mechanizedcore.client.gui.part;

import mechanizedmadness.mechanizedcore.client.gui.GuiBase;
import mechanizedmadness.mechanizedcore.lib.Reference;
import mechanizedmadness.mechanizedcore.util.IconRegistry;

import java.util.ArrayList;

/**
 * A list of parts with a scroll bar.
 */
public class PartList extends PartBase {

    public ArrayList<PartBase> parts = new ArrayList<PartBase>();

    public int elementsPerRow;

    public PartList(){

    }

    public PartList(int posX, int posY, int sizeX, int sizeY, ArrayList<PartBase> parts, GuiBase parentGui) {
        this.posX = posX;
        this.posY = posY;
        this.texW = 16;
        this.texH = 16;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.parentGui = parentGui;

        this.parts = parts;

        if(parts.get(0).sizeX + 2 < sizeX|| parts.get(0).sizeY + 2 < sizeY){
            throw new IllegalArgumentException("Size of parts in list are bigger than the list.");
        }

        setTexture(Reference.MOD_ID + ":textures/gui/container/Slot.png");
    }

	@Override
	public void drawGuiBackgroundLayer(float var1, int x, int y) {
        parentGui.drawScaledTexturedModelRectFromIcon(posX,posY, IconRegistry.getIcon(texture.getResourcePath()),sizeX,sizeY);


	}

	@Override
	public void drawGuiForegroundLayer(int x, int y) {

	}

	@Override
	public void drawActiveArea(int x, int y) {

	}

}

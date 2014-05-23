package mechanizedmadness.mechanizedcore.client.gui.part;

import java.util.ArrayList;

import mechanizedmadness.mechanizedcore.client.gui.GuiBase;
import mechanizedmadness.mechanizedcore.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.fluids.IFluidTank;

public class PartFluidTank extends PartBase {

    public IFluidTank tank;

    public PartFluidTank(int posX, int posY, IFluidTank tank, GuiBase parentGui) {
        this.posX = posX;
        this.posY = posY;
        this.texW = 64;
        this.texH = 64;
        this.sizeX = 18;
        this.sizeY = 62;
        this.parentGui = parentGui;

        this.tank = tank;

        setTexture(Reference.MOD_ID + ":textures/gui/container/part/FluidTank.png");
    }

    public PartFluidTank() {

    }

    @Override
    public void drawGuiBackgroundLayer(float par3, int x, int y) {
        int drawX = parentGui.textureCornerTopLeftX + posX;
        int drawY = parentGui.textureCornerTopLeftY + posY;

        mc.renderEngine.bindTexture(texture);
        parentGui.drawTexturedModalRect(drawX, drawY, 0, 0, sizeX, sizeY);

        int amount = tank.getFluidAmount() * sizeY / tank.getCapacity();

        mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        parentGui.drawFluid(posX, posY + sizeY - amount, tank.getFluid(), sizeX, amount);
    }

    @Override
    public void drawGuiForegroundLayer(int x, int y) {
        int drawX = parentGui.textureCornerTopLeftX + posX;
        int drawY = parentGui.textureCornerTopLeftY + posY;

        mc.renderEngine.bindTexture(texture);
        parentGui.drawTexturedModalRect(drawX, drawY, 18, 1, sizeX - 2, sizeY - 2);
    }

    @Override
    public void drawActiveArea(int x, int y) {
        x = parentGui.textureCornerTopLeftX + x;
        y = parentGui.textureCornerTopLeftY + y;

        ArrayList<String> string = new ArrayList<String>();

        string.add(tank.getFluid().getFluid().getLocalizedName() + ":" + tank.getFluidAmount() + "mB");

        parentGui.drawHoveringText(string, x, y, Minecraft.getMinecraft().fontRenderer);
    }
}

package mechanizedmadness.mechanizedcore.client.gui;

import java.util.Iterator;
import java.util.List;

import mechanizedmadness.mechanizedcore.client.gui.part.PartBase;
import mechanizedmadness.mechanizedcore.lib.Reference;
import mechanizedmadness.mechanizedcore.util.RenderUtils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.google.common.collect.HashBiMap;

/**
 * Gui class that allows use of Parts
 * 
 * @author Darkevilmac
 *
 */
public abstract class GuiBase extends GuiContainer {

    public int textureCornerTopLeftX;
    public int textureCornerTopLeftY;
    public ResourceLocation texture;

    public int mousePosX;
    public int mousePosY;

    public HashBiMap<String, PartBase> parts = HashBiMap.create();

    public GuiBase(Container container) {
        super(container);
    }

    public GuiBase(Container container, String textureName) {
        super(container);
        this.texture = new ResourceLocation(textureName);
    }

    @Override
    public void drawScreen(int x, int y, float par3) {
        super.drawScreen(x, y, par3);
        textureCornerTopLeftX = (width - xSize) / 2;
        textureCornerTopLeftY = (height - ySize) / 2;
        if (!parts.isEmpty()) {
            Iterator<PartBase> partIterator = parts.values().iterator();
            while (partIterator.hasNext()) {
                ((PartBase) partIterator.next()).drawScreen(x, y, par3);
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int x, int y) {
        mousePosX = x;
        mousePosY = y;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        ResourceLocation slotTex = new ResourceLocation(Reference.MOD_ID + ":textures/gui/container/slot.png");
        mc.renderEngine.bindTexture(slotTex);
        for (int i = 0; i < inventorySlots.inventorySlots.size(); i++) {
            drawTexturedModalRect(((Slot) inventorySlots.inventorySlots.get(i)).xDisplayPosition,
                    ((Slot) inventorySlots.inventorySlots.get(i)).yDisplayPosition, 0, 0, 18, 18);
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int clickType) {
        super.mouseClicked(x, y, clickType);
        if (!parts.isEmpty()) {
            Iterator<PartBase> partIterator = parts.values().iterator();
            while (partIterator.hasNext()) {
                ((PartBase) partIterator.next()).mouseClicked(x, y, clickType);
            }
        }
    }

    /**
     * Draw Horizontal line.
     */
    @Override
    public void drawHorizontalLine(int posX, int posY, int length, int colour) {
        super.drawHorizontalLine(posX, posY, length, colour);
    }

    /**
     * Draw Vertical line.
     */
    @Override
    public void drawVerticalLine(int posX, int posY, int length, int colour) {
        super.drawVerticalLine(posX, posY, length, colour);
    }

    @Override
    public void drawGradientRect(int posX, int posY, int sizeX, int sizeY, int color1, int color2) {
        super.drawGradientRect(posX, posY, sizeX, sizeY, color1, color2);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void drawHoveringText(List p_146283_1_, int p_146283_2_, int p_146283_3_, FontRenderer font) {
        super.drawHoveringText(p_146283_1_, p_146283_2_, p_146283_3_, font);
    }

    @Override
    public void initGui() {
        super.initGui();
        parts.clear();
        addParts();
    }

    /**
     * Draws a fluid from a fluid object.
     * 
     * @param fluid
     * @param posX
     * @param posY
     * @param sizeX
     * @param sizeY
     */
    public void drawFluid(Fluid fluid, int posX, int posY, int sizeX, int sizeY) {
        drawTexturedModelRectFromIcon(posX, posY, fluid.getIcon(), sizeX, sizeY);
    }

    /**
     * Draws an overlay on a slot like in chests and furnaces.
     * 
     * @param posX
     * @param posY
     * @param sizeX
     * @param sizeY
     */
    public void drawSlotOverlay(int posX, int posY, int sizeX, int sizeY) {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glColorMask(true, true, true, false);
        drawGradientRect(posX, posY, posX + sizeX, posY + sizeY, -2130706433, -2130706433);
        GL11.glColorMask(true, true, true, true);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public GuiBase setSizeX(int sizeX) {
        this.width = sizeX;

        return this;
    }

    public GuiBase setSizeY(int sizeY) {
        this.height = sizeY;

        return this;
    }

    // Part related code

    /**
     * Override and use to add parts to a gui with addPart.
     */
    public abstract void addParts();

    /**
     * Add part to the gui.
     * 
     * @param key
     *            Recommended partName + a numerical identifier. ex: tank0
     * @param part
     */
    public void addPart(String key, PartBase part) {
        parts.put(key, part);
    }

    /**
     * Get part from key.
     * 
     * @param key
     * @return
     */
    public PartBase getPart(String key) {
        return parts.get(key);
    }

    /*
     * HELPERS Sourced from CofhLib because I'm lazy.
     * https://github.com/skyboy/CoFHLib/blob/master/src/cofh/gui/GuiBase.java
     */

    /**
     * Abstract method to retrieve icons by name from a registry You must
     * override this if you use any of the String methods below
     */
    public IIcon getIcon(String name) {

        return null;
    }

    /**
     * Essentially a placeholder method for tabs to use should they need to draw
     * a button.
     */
    public void drawButton(IIcon icon, int x, int y, int spriteSheet, int mode) {

        drawIcon(icon, x, y, spriteSheet);
    }

    public void drawButton(String iconName, int x, int y, int spriteSheet, int mode) {

        drawButton(getIcon(iconName), x, y, spriteSheet, mode);
    }

    /**
     * Simple method used to draw a fluid of arbitrary size.
     */
    public void drawFluid(int x, int y, FluidStack fluid, int width, int height) {

        if (fluid == null || fluid.getFluid() == null) {
            return;
        }
        mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        RenderUtils.setColor3ub(fluid.getFluid().getColor(fluid));

        drawTiledTexture(x, y, fluid.getFluid().getIcon(fluid), width, height);
    }

    public void drawTiledTexture(int x, int y, IIcon icon, int width, int height) {

        int i = 0;
        int j = 0;

        int drawHeight = 0;
        int drawWidth = 0;

        for (i = 0; i < width; i += 16) {
            for (j = 0; j < height; j += 16) {
                drawWidth = Math.min(width - i, 16);
                drawHeight = Math.min(height - j, 16);
                drawScaledTexturedModelRectFromIcon(x + i, y + j, icon, drawWidth, drawHeight);
            }
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
    }

    public void drawIcon(IIcon icon, int x, int y, int spriteSheet) {

        if (spriteSheet == 0) {
            mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
        } else {
            mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
        drawTexturedModelRectFromIcon(x, y, icon, 16, 16);
    }

    public void drawIcon(String iconName, int x, int y, int spriteSheet) {

        drawIcon(getIcon(iconName), x, y, spriteSheet);
    }

    public void drawSizedTexturedModalRect(int x, int y, int u, int v, int width, int height, float texW, float texH) {

        float texU = 1 / texW;
        float texV = 1 / texH;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + height, this.zLevel, (u + 0) * texU, (v + height) * texV);
        tessellator.addVertexWithUV(x + width, y + height, this.zLevel, (u + width) * texU, (v + height) * texV);
        tessellator.addVertexWithUV(x + width, y + 0, this.zLevel, (u + width) * texU, (v + 0) * texV);
        tessellator.addVertexWithUV(x + 0, y + 0, this.zLevel, (u + 0) * texU, (v + 0) * texV);
        tessellator.draw();
    }

    public void drawScaledTexturedModelRectFromIcon(int x, int y, IIcon icon, int width, int height) {

        if (icon == null) {
            return;
        }
        double minU = icon.getMinU();
        double maxU = icon.getMaxU();
        double minV = icon.getMinV();
        double maxV = icon.getMaxV();

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + height, this.zLevel, minU, minV + (maxV - minV) * height / 16F);
        tessellator.addVertexWithUV(x + width, y + height, this.zLevel, minU + (maxU - minU) * width / 16F, minV
                + (maxV - minV) * height / 16F);
        tessellator.addVertexWithUV(x + width, y + 0, this.zLevel, minU + (maxU - minU) * width / 16F, minV);
        tessellator.addVertexWithUV(x + 0, y + 0, this.zLevel, minU, minV);
        tessellator.draw();
    }

    @SuppressWarnings("rawtypes")
    protected void drawTooltipHoveringText(List list, int x, int y, FontRenderer font) {

        if (list == null || list.isEmpty()) {
            return;
        }
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        int k = 0;
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            int l = font.getStringWidth(s);

            if (l > k) {
                k = l;
            }
        }
        int i1 = x + 12;
        int j1 = y - 12;
        int k1 = 8;

        if (list.size() > 1) {
            k1 += 2 + (list.size() - 1) * 10;
        }
        if (i1 + k > this.width) {
            i1 -= 28 + k;
        }
        if (j1 + k1 + 6 > this.height) {
            j1 = this.height - k1 - 6;
        }
        this.zLevel = 300.0F;
        itemRender.zLevel = 300.0F;
        int l1 = -267386864;
        this.drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
        this.drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
        this.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
        this.drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
        this.drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
        int i2 = 1347420415;
        int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
        this.drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
        this.drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
        this.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
        this.drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);

        for (int k2 = 0; k2 < list.size(); ++k2) {
            String s1 = (String) list.get(k2);
            font.drawStringWithShadow(s1, i1, j1, -1);

            if (k2 == 0) {
                j1 += 2;
            }
            j1 += 10;
        }
        this.zLevel = 0.0F;
        itemRender.zLevel = 0.0F;
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
    }

}

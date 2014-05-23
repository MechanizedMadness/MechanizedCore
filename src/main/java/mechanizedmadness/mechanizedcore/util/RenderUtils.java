package mechanizedmadness.mechanizedcore.util;

import org.lwjgl.opengl.GL11;

public class RenderUtils {

    public static void setColor3ub(int color) {

        GL11.glColor3ub((byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF), (byte) (color & 0xFF));
    }

    public static void setColor4ub(int color) {

        GL11.glColor4ub((byte) (color >> 24 & 0xFF), (byte) (color >> 16 & 0xFF), (byte) (color >> 8 & 0xFF),
                (byte) (color & 0xFF));
    }

    public static void resetColor() {

        GL11.glColor4f(1F, 1F, 1F, 1F);
    }

}

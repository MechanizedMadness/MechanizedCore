package mechanizedmadness.mechanizedcore.util;

import com.google.common.collect.HashBiMap;
import net.minecraft.util.IIcon;

import java.util.ArrayList;

/**
 * Use this class to register icons that you can not register in your blocks/items
 *
 * Created by Darkevilmac on 5/16/2014.
 */
public class IconRegistry {

    public static ArrayList<String> iconsToRegister = new ArrayList<String>();
    public static HashBiMap<String,IIcon> icons = HashBiMap.create();

    public static void registerIcon(String icon){
        if(!iconsToRegister.contains(icon)) iconsToRegister.add(icon);
        else throw new IllegalArgumentException(icon + " has already been registered.");
    }

    public static IIcon getIcon(String iconName){
        return icons.get(iconName);
    }

}

package mechanizedmadness.mechanizedcore;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mechanizedmadness.mechanizedcore.lib.Reference;
import mechanizedmadness.mechanizedcore.proxy.CommonProxy;
import mechanizedmadness.mechanizedcore.util.IconRegistry;
import mechanizedmadness.mechanizedcore.util.RegistryUtils;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.version)
public class MechanizedCore {

    @Instance(value = Reference.MOD_ID)
    public static MechanizedCore instance;

    @SidedProxy(clientSide = "mechanizedmadness.mechanizedcore.proxy.ClientProxy", serverSide = "mechanizedmadness.mechanizedcore.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        IconRegistry.registerIcon("mechanizedcore:textures/gui/container/Slot.png");
    }

    @EventHandler
    public static void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        RegistryUtils.init();
    }

}

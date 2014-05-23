package mechanizedmadness.mechanizedcore.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mechanizedmadness.mechanizedcore.util.IconRegistry;
import net.minecraftforge.client.event.TextureStitchEvent;

/**
 * Created by Darkevilmac on 5/16/2014.
 */
public class EventHandler {

    @SubscribeEvent
    public void onPostTextureStitchEvent(TextureStitchEvent.Post e){
        if(!IconRegistry.iconsToRegister.isEmpty()){
            for(int i = 0; i < IconRegistry.iconsToRegister.size();i++){
                IconRegistry.icons.put(IconRegistry.iconsToRegister.get(i),e.map.registerIcon(IconRegistry.iconsToRegister.get(i)));
            }
        }
    }

}

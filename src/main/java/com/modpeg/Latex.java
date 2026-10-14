package com.modpeg;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Latex implements ModInitializer {
    public static final String MOD_ID = "latex";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        ModWoodTypes.register();
        ModFluids.register();
        ModBlocks.register();
        ModBoatTypes.register();
        ModEntityTypes.register();
        ModItems.register();
        ModBlockEntityTypes.register();
        LOGGER.info("Latex mod initialized!");
    }
}

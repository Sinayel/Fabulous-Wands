package tech.sinayel.fabulouswands;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import tech.sinayel.fabulouswands.entity.ModEntities;

public class FabulousWandsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SPELL, FlyingItemEntityRenderer::new);
    }
}

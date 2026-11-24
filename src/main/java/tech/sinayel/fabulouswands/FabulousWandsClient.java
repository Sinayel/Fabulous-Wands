package tech.sinayel.fabulouswands;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import tech.sinayel.fabulouswands.entity.ModEntities;
import tech.sinayel.fabulouswands.screen.SpellWheelScreen;

public class FabulousWandsClient implements ClientModInitializer {

    public static KeyBinding openSpellWheel;

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SPELL, FlyingItemEntityRenderer::new);

        openSpellWheel = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.fabulouswands.spell_wheel",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.fabulouswands"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openSpellWheel.wasPressed()) {
                if (client.currentScreen == null && client.player != null) {
                    client.setScreen(new SpellWheelScreen());
                }
            }
        });
    }
}
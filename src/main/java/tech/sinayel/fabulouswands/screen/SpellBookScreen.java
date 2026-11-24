package tech.sinayel.fabulouswands.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class SpellBookScreen extends Screen {

    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 20;
    private static final int SPACING = 4;

    public SpellBookScreen() {
        super(Text.literal("Livre de sorts"));
    }

    @Override
    protected void init() {
        String[] SPELLS = {"Expelliarmus", "Lumos", "Avada Kedavra"};

        int totalHeight = SPELLS.length * (BUTTON_HEIGHT + SPACING) - SPACING;
        int startY = (this.height - totalHeight) / 2;
        int x = (this.width - BUTTON_WIDTH) / 2;

        for (int i = 0; i < SPELLS.length; i++) {
            String spell = SPELLS[i];
            int y = startY + i * (BUTTON_HEIGHT + SPACING);

            this.addDrawableChild(ButtonWidget.builder(Text.literal(spell), button -> {
                this.client.player.sendMessage(Text.literal("Sort choisi : " + spell), false);
                this.close();
            }).dimensions(x, y, BUTTON_WIDTH, BUTTON_HEIGHT).build());
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
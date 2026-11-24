package tech.sinayel.fabulouswands.screen;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import tech.sinayel.fabulouswands.FabulousWandsClient;

public class SpellWheelScreen extends Screen {

    private static final String[] SPELLS = {
            "Expelliarmus", "Lumos", "Avada Kedavra",
            "Wingardium Leviosa", "Stupefy", "Protego"
    };

    private static final int RADIUS = 80;
    private static final int DEAD_ZONE = 20;
    private static final int PADDING = 4;

    public SpellWheelScreen() {
        super(Text.literal("Roue des sorts"));
    }

    // ---------- Logique ----------

    /** Renvoie l'index du sort pointé par la souris, ou -1 si on est dans la zone morte. */
    private int getHovered(double mouseX, double mouseY) {
        double dx = mouseX - this.width / 2.0;
        double dy = mouseY - this.height / 2.0;
        if (dx * dx + dy * dy < DEAD_ZONE * DEAD_ZONE)
            return -1;

        double step = 2 * Math.PI / SPELLS.length;
        double angle = Math.atan2(dy, dx) + Math.PI / 2 + step / 2;
        angle = (angle % (2 * Math.PI) + 2 * Math.PI) % (2 * Math.PI);
        return (int) (angle / step) % SPELLS.length;
    }

    private void castSpell(int index) {
        if (this.client != null && this.client.player != null) {
            this.client.player.sendMessage(Text.literal("Sort choisi : " + SPELLS[index]), true);
        }
    }

    // ---------- Rendu ----------

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, this.width, this.height, 0x40000000);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        int cx = this.width / 2;
        int cy = this.height / 2;
        int hovered = getHovered(mouseX, mouseY);
        double step = 2 * Math.PI / SPELLS.length;
        int fontH = this.textRenderer.fontHeight;

        for (int i = 0; i < SPELLS.length; i++) {
            double a = i * step - Math.PI / 2;
            int x = cx + (int) Math.round(Math.cos(a) * RADIUS);
            int y = cy + (int) Math.round(Math.sin(a) * RADIUS);

            boolean selected = (i == hovered);
            int w = this.textRenderer.getWidth(SPELLS[i]);

            context.fill(
                    x - w / 2 - PADDING, y - fontH / 2 - PADDING,
                    x + w / 2 + PADDING, y + fontH / 2 + PADDING,
                    selected ? 0xC0806020 : 0x90000000
            );

            context.drawCenteredTextWithShadow(
                    this.textRenderer, SPELLS[i],
                    x, y - fontH / 2,
                    selected ? 0xFFFF55 : 0xFFFFFF
            );
        }

        String center = hovered == -1 ? "Choisis un sort" : SPELLS[hovered];
        context.drawCenteredTextWithShadow(this.textRenderer, center, cx, cy - fontH / 2, 0xAAAAAA);
    }

    // ---------- Entrees ----------
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            int hovered = getHovered(mouseX, mouseY);
            if (hovered != -1) {
                castSpell(hovered);
                this.close();
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void tick() {
        if (this.client == null) return;

        InputUtil.Key key = KeyBindingHelper.getBoundKeyOf(FabulousWandsClient.openSpellWheel);
        if (key.getCategory() != InputUtil.Type.KEYSYM || key.getCode() == -1)
            return;

        long window = this.client.getWindow().getHandle();
        if (!InputUtil.isKeyPressed(window, key.getCode())) {
            double mouseX = this.client.mouse.getX() * this.width / this.client.getWindow().getWidth();
            double mouseY = this.client.mouse.getY() * this.height / this.client.getWindow().getHeight();

            int hovered = getHovered(mouseX, mouseY);
            if (hovered != -1)
                castSpell(hovered);
            this.close();
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
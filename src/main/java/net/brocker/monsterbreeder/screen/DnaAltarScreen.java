package net.brocker.monsterbreeder.screen;

import net.brocker.monsterbreeder.MonsterBreeder;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DnaAltarScreen extends HandledScreen<DnaAltarScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(MonsterBreeder.MOD_ID, "textures/gui/dna_altar_gui.png");

    public DnaAltarScreen(DnaAltarScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        // Draw the background
        // You'll need to create a GUI texture at assets/monsterbreeder/textures/gui/dna_altar_gui.png
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}

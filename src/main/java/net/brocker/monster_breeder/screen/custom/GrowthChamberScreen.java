package net.brocker.monster_breeder.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.brocker.monster_breeder.MonsterBreeder;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class GrowthChamberScreen extends HandledScreen<GrowthChamberScreenHandler> {
    private static final Identifier GUI_TEXTURE =
            Identifier.of(MonsterBreeder.MOD_ID, "textures/gui/growth_chamber/growth_chamber_gui.png");
    private static final Identifier ARROW_TEXTURE =
            Identifier.of(MonsterBreeder.MOD_ID, "textures/gui/growth_chamber/dna_arrow.png");

    public GrowthChamberScreen(GrowthChamberScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }


    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, 176, 166);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(ARROW_TEXTURE, x + 38, y + 34, 0, 0,
                    handler.getScaledArrowProgress(), 16, 22, 16);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}

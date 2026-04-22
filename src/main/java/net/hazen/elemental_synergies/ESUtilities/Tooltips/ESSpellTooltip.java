package net.hazen.elemental_synergies.ESUtilities.Tooltips;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import org.joml.Matrix4f;

public class ESSpellTooltip implements ClientTooltipComponent {

    public record ESSpellTooltipData(Component text) implements TooltipComponent {}

    private final Component text;

    public ESSpellTooltip(ESSpellTooltipData data) {
        this.text = data.text();
    }

    @Override
    public int getHeight() { return 12; }

    @Override
    public int getWidth(Font font) { return font.width(text); }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {

        long seed = System.currentTimeMillis() / 100;
        RandomSource random = RandomSource.create(seed);

        if (random.nextFloat() < 0.4f) {
            guiGraphics.pose().pushPose();
            // Confirmed working Z-index
            guiGraphics.pose().translate(0, 0, 500f);

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShader(GameRenderer::getPositionColorShader);

            guiGraphics.flush();
            RenderSystem.disableBlend();
            guiGraphics.pose().popPose();
        }
    }

    @Override
    public void renderText(Font font, int x, int y, Matrix4f matrix, MultiBufferSource.BufferSource buffer) {
        String rawString = text.getString();

        long timeMs = System.currentTimeMillis();
        float xOffset = x;

        // Full-spectrum cycle controls (one full cycle every 4 seconds)
        float cycleMs = 4000f;
        float baseHue = (timeMs % (long)cycleMs) / cycleMs; // 0..1

        // Wave controls
        double waveSpeed = 7.0;
        double wavePhase = 0.65;
        float amplitude = 1.8f;

        for (int i = 0; i < rawString.length(); i++) {
            String letter = String.valueOf(rawString.charAt(i));

            // Slight per-letter hue offset for a flowing rainbow across the string
            float hue = (baseHue + (i * 0.005f)) % 1f;
            int rgb = java.awt.Color.HSBtoRGB(hue, 1f, 1f);

            float pulse = (float) Math.max(0, Math.sin((timeMs * 0.01) + (i * 0.4f)));
            int finalColor = applyGlint(rgb, pulse * 0.15f);

            double phase = (i * wavePhase) - ((timeMs / 1000.0) * waveSpeed);
            float yWave = (float) (Math.sin(phase) * amplitude);

            font.drawInBatch(
                    letter,
                    xOffset,
                    y + yWave + 2,
                    finalColor,
                    true,
                    matrix,
                    buffer,
                    Font.DisplayMode.NORMAL,
                    0,
                    15728880
            );

            xOffset += font.width(letter);
        }
    }

    private int applyGlint(int color, float glint) {
        int r = Math.min(255, (int) (((color >> 16) & 0xFF) + (255 * glint)));
        int g = Math.min(255, (int) (((color >> 8) & 0xFF) + (255 * glint)));
        int b = Math.min(255, (int) ((color & 0xFF) + (255 * glint)));
        return (255 << 24) | (r << 16) | (g << 8) | b;
    }
}

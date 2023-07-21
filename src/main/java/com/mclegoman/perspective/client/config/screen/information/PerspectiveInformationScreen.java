/*
    Perspective
    Author: MCLegoMan
    Github: https://github.com/MCLegoMan/Perspective
    License: CC-BY 4.0
*/

package com.mclegoman.perspective.client.config.screen.information;

import com.mclegoman.perspective.client.config.PerspectiveConfigHelper;
import com.mclegoman.perspective.client.config.screen.PerspectiveConfigScreenHelper;
import com.mclegoman.perspective.client.config.screen.experimental.PerspectiveExperimentalConfigScreen;
import com.mclegoman.perspective.client.config.screen.textured_entity.PerspectiveTexturedEntityConfigScreen;
import com.mclegoman.perspective.client.translation.PerspectiveTranslation;
import com.mclegoman.perspective.common.data.PerspectiveData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.*;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class PerspectiveInformationScreen extends Screen {
    private final Screen PARENT_SCREEN;
    private final GridWidget GRID;
    private boolean SHOULD_CLOSE;
    public PerspectiveInformationScreen(Screen PARENT) {
        super(Text.literal(""));
        this.GRID = new GridWidget();
        this.PARENT_SCREEN = PARENT;
    }
    public void init() {
        try {
            GRID.getMainPositioner().alignHorizontalCenter().margin(0);
            GridWidget.Adder GRID_ADDER = GRID.createAdder(1);
            GRID_ADDER.add(PerspectiveConfigScreenHelper.createTitle(client, new PerspectiveInformationScreen(PARENT_SCREEN)));
            GRID_ADDER.add(createInformation());
            GRID_ADDER.add(createFooter());
            GRID.refreshPositions();
            GRID.forEachChild(this::addDrawableChild);
            initTabNavigation();
        } catch (Exception error) {
            PerspectiveData.LOGGER.warn(PerspectiveData.PREFIX + "Failed to initialize information screen: {}", (Object)error);
        }
    }

    public void tick() {
        if (this.SHOULD_CLOSE) {
            client.setScreen(PARENT_SCREEN);
        }
    }
    private GridWidget createInformation() {
        GridWidget GRID = new GridWidget();
        GRID.getMainPositioner().alignHorizontalCenter().margin(2);
        GridWidget.Adder GRID_ADDER = GRID.createAdder(2);
        GRID_ADDER.add(ButtonWidget.builder(PerspectiveTranslation.getConfigTranslation("information.documentation"), ConfirmLinkScreen.opening("https://mclegoman.github.io/Perspective", this, true)).build(), 2).setTooltip(Tooltip.of(PerspectiveTranslation.getConfigTranslation("information.documentation", true)));
        GRID_ADDER.add(ButtonWidget.builder(PerspectiveTranslation.getConfigTranslation("information.contribute"), ConfirmLinkScreen.opening("https://github.com/MCLegoMan/Perspective", this, true)).build()).setTooltip(Tooltip.of(PerspectiveTranslation.getConfigTranslation("information.contribute", true)));
        GRID_ADDER.add(ButtonWidget.builder(PerspectiveTranslation.getConfigTranslation("information.report"), ConfirmLinkScreen.opening("https://github.com/MCLegoMan/Perspective/issues", this, true)).build()).setTooltip(Tooltip.of(PerspectiveTranslation.getConfigTranslation("information.report", true)));
        return GRID;
    }
    private GridWidget createFooter() {
        GridWidget GRID = new GridWidget();
        GRID.getMainPositioner().alignHorizontalCenter().margin(2);
        GridWidget.Adder GRID_ADDER = GRID.createAdder(1);
        GRID_ADDER.add(ButtonWidget.builder(PerspectiveTranslation.getConfigTranslation("back"), (button) -> this.SHOULD_CLOSE = true).width(304).build()).setTooltip(Tooltip.of(PerspectiveTranslation.getConfigTranslation("back", true)));
        return GRID;
    }

    public void initTabNavigation() {
        SimplePositioningWidget.setPos(GRID, getNavigationFocus());
    }
    public Text getNarratedTitle() {
        return ScreenTexts.joinSentences();
    }
    public boolean shouldCloseOnEsc() {
        return false;
    }
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) this.SHOULD_CLOSE = true;
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }
}
/*
    Perspective
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Perspective
    Licence: GNU LGPLv3
*/

package com.mclegoman.perspective.client.screen.config.shaders;

import com.mclegoman.perspective.client.data.ClientData;
import com.mclegoman.perspective.client.screen.config.toasts.UpdateCheckerScreen;
import com.mclegoman.perspective.client.shaders.Shader;
import com.mclegoman.perspective.client.shaders.ShaderDataLoader;
import com.mclegoman.perspective.client.translation.Translation;
import com.mclegoman.perspective.client.util.Keybindings;
import com.mclegoman.perspective.common.data.Data;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class ShaderSelectionConfigScreen extends Screen {
	public Text title;
	public Screen parent;
	private final Formatting randomColor;
	ShadersListWidget<ShaderListEntry> widget;
	private boolean SHOULD_CLOSE;
	public ShaderSelectionConfigScreen(Screen PARENT) {
		super(Text.literal(""));
		this.parent = PARENT;
		randomColor = Shader.getRandomColor();
	}
	protected void init() {
		this.widget = new ShadersListWidget<>(ClientData.CLIENT.getWindow().getScaledWidth(), ClientData.CLIENT.getWindow().getScaledHeight(), 32, ClientData.CLIENT.getWindow().getScaledHeight() - 32, 27);
		for (int i = 0; i <= ShaderDataLoader.getShaderAmount(); i++) {
			this.widget.addEntry(new ShaderListEntry(i, widget));
		}
		widget.setFocused(true);
		addDrawableChild(widget);
		addDrawableChild(ButtonWidget.builder(Translation.getConfigTranslation("back"), (button) -> this.SHOULD_CLOSE = true).dimensions(ClientData.CLIENT.getWindow().getScaledWidth() / 2 - 75, ClientData.CLIENT.getWindow().getScaledHeight() - 26, 150, 20).build());
	}

	public void tick() {
		try {
			if (this.SHOULD_CLOSE) {
				ClientData.CLIENT.setScreen(parent);
			}
		} catch (Exception error) {
			Data.PERSPECTIVE_VERSION.getLogger().warn("{} Failed to tick perspective$config$shaders$select screen: {}", Data.PERSPECTIVE_VERSION.getID(), error);
		}
	}
	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		if (ClientData.CLIENT.world == null) {
			context.setShaderColor(0.125F, 0.125F, 0.125F, 1.0F);
			context.drawTexture(new Identifier("textures/gui/options_background.png"), 0, 0, 0, 0.0F, 0.0F, ClientData.CLIENT.getWindow().getScaledWidth(), ClientData.CLIENT.getWindow().getScaledHeight(), 32, 32);
			context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		}
		context.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
		context.drawTexture(new Identifier("textures/gui/options_background.png"), 0, 0, 0, 0.0F, 0.0F, ClientData.CLIENT.getWindow().getScaledWidth(), 32, 32, 32);
		context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		context.setShaderColor(0.25F, 0.25F, 0.25F, 1.0F);
		context.drawTexture(new Identifier("textures/gui/options_background.png"), 0, ClientData.CLIENT.getWindow().getScaledHeight() - 32, 0, 0.0F, 0.0F, ClientData.CLIENT.getWindow().getScaledWidth(), 32, 32, 32);
		context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		context.drawCenteredTextWithShadow(ClientData.CLIENT.textRenderer, Translation.getConfigTranslation("shaders.shader.select", new Formatting[]{randomColor}), ClientData.CLIENT.getWindow().getScaledWidth() / 2, 12, 0xFFFFFF);

		for (Drawable drawable : this.drawables) {
			drawable.render(context, mouseX, mouseY, delta);
		}
	}

	@Override
	public boolean shouldCloseOnEsc() {
		return false;
	}
	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode == GLFW.GLFW_KEY_ESCAPE || keyCode == KeyBindingHelper.getBoundKeyOf(Keybindings.OPEN_CONFIG).getCode()) this.SHOULD_CLOSE = true;
		return super.keyPressed(keyCode, scanCode, modifiers);
	}
	@Override
	public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
		if (keyCode == GLFW.GLFW_KEY_F5) {
			ClientData.CLIENT.setScreen(new UpdateCheckerScreen(this));
		}
		return super.keyReleased(keyCode, scanCode, modifiers);
	}
}
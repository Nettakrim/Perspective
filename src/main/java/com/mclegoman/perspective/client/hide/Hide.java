/*
    Perspective
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Perspective
    Licence: GNU LGPLv3
*/

package com.mclegoman.perspective.client.hide;

import com.mclegoman.perspective.client.config.ConfigHelper;
import com.mclegoman.perspective.client.data.ClientData;
import com.mclegoman.perspective.client.util.Keybindings;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resource.ResourceType;

import java.util.UUID;

public class Hide {
	public static void init() {
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new HideArmorDataLoader());
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new HideNameTagsDataLoader());
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new HidePlayerDataLoader());
	}

	public static void tick(MinecraftClient client) {
		if (Keybindings.TOGGLE_ARMOR.wasPressed())
			ConfigHelper.setConfig("hide_armor", !(boolean) ConfigHelper.getConfig("hide_armor"));
		if (Keybindings.TOGGLE_BLOCK_OUTLINE.wasPressed())
			ConfigHelper.setConfig("hide_block_outline", !(boolean) ConfigHelper.getConfig("hide_block_outline"));
		if (Keybindings.TOGGLE_CROSSHAIR.wasPressed())
			ConfigHelper.setConfig("hide_crosshair", !(boolean) ConfigHelper.getConfig("hide_crosshair"));
		if (Keybindings.TOGGLE_NAMETAGS.wasPressed())
			ConfigHelper.setConfig("hide_nametags", !(boolean) ConfigHelper.getConfig("hide_nametags"));
		if (Keybindings.TOGGLE_PLAYERS.wasPressed())
			ConfigHelper.setExperimentalConfig("hide_players", !(boolean) ConfigHelper.getConfig("hide_players"));
	}

	public static boolean shouldHidePlayer(PlayerEntity player) {
		if (ClientData.CLIENT.player != null) {
			if ((boolean) ConfigHelper.getExperimentalConfig("allow_hide_players")) {
				UUID uuid = player.getGameProfile().getId();
				if (!uuid.equals(ClientData.CLIENT.player.getGameProfile().getId()))
					return (boolean) ConfigHelper.getExperimentalConfig("hide_players") || HidePlayerDataLoader.REGISTRY.contains(String.valueOf(player.getGameProfile().getId()));
			}
		}
		return false;
	}
}

/*
    Perspective
    Author: MCLegoMan
    Github: https://github.com/MCLegoMan/Perspective
    License: CC-BY 4.0
*/

package com.mclegoman.perspective.client.panorama;

import com.mclegoman.perspective.client.data.PerspectiveClientData;
import com.mclegoman.perspective.client.translation.PerspectiveTranslation;
import com.mclegoman.perspective.client.util.PerspectiveKeybindings;
import com.mclegoman.perspective.common.data.PerspectiveData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class PerspectivePanorama {
    private static final String[] INCOMPATIBLE = new String[]{"sodium"};
    private static final List<String> INCOMPATIBLE_MODS_FOUND = new ArrayList<>();
    public static void init() {
        for (String mod : INCOMPATIBLE) if (FabricLoader.getInstance().isModLoaded(mod)) INCOMPATIBLE_MODS_FOUND.add(mod);
    }
    public static void tick(MinecraftClient client) {
        if (PerspectiveKeybindings.TAKE_PANORAMA_SCREENSHOT.wasPressed()) takePanorama(1024);
    }
    private static String getTime() {
        return String.valueOf(LocalDateTime.now()).replace(":", "");
    }
    private static void takePanorama(int resolution) {
        if (PerspectiveClientData.CLIENT.player != null) {
            try {
                if (INCOMPATIBLE_MODS_FOUND.size() == 0) {
                    String panoramaName = getTime();
                    String rpDirLoc = PerspectiveClientData.CLIENT.runDirectory.getPath() + "/panorama/" + panoramaName;
                    String assetsDirLoc = rpDirLoc + "/assets/minecraft/textures/gui/title/background";
                    if (new File(assetsDirLoc).mkdirs()) {
                        int framebufferWidth = PerspectiveClientData.CLIENT.getWindow().getFramebufferWidth();
                        int framebufferHeight = PerspectiveClientData.CLIENT.getWindow().getFramebufferHeight();
                        Framebuffer framebuffer = new SimpleFramebuffer(resolution, resolution, true, MinecraftClient.IS_SYSTEM_MAC);
                        float pitch = PerspectiveClientData.CLIENT.player.getPitch();
                        float yaw = PerspectiveClientData.CLIENT.player.getYaw();
                        PerspectiveClientData.CLIENT.gameRenderer.setBlockOutlineEnabled(false);
                        PerspectiveClientData.CLIENT.gameRenderer.setRenderingPanorama(true);
                        PerspectiveClientData.CLIENT.getWindow().setFramebufferWidth(resolution);
                        PerspectiveClientData.CLIENT.getWindow().setFramebufferHeight(resolution);
                        for(int l = 0; l < 6; ++l) {
                            switch (l) {
                                case 0 -> {
                                    PerspectiveClientData.CLIENT.player.setYaw(0.0F);
                                    PerspectiveClientData.CLIENT.player.setPitch(0.0F);
                                }
                                case 1 -> {
                                    PerspectiveClientData.CLIENT.player.setYaw(90.0F);
                                    PerspectiveClientData.CLIENT.player.setPitch(0.0F);
                                }
                                case 2 -> {
                                    PerspectiveClientData.CLIENT.player.setYaw(180.0F);
                                    PerspectiveClientData.CLIENT.player.setPitch(0.0F);
                                }
                                case 3 -> {
                                    PerspectiveClientData.CLIENT.player.setYaw(270.0F);
                                    PerspectiveClientData.CLIENT.player.setPitch(0.0F);
                                }
                                case 4 -> {
                                    PerspectiveClientData.CLIENT.player.setYaw(0.0F);
                                    PerspectiveClientData.CLIENT.player.setPitch(-90.0F);
                                }
                                default -> {
                                    PerspectiveClientData.CLIENT.player.setYaw(0.0F);
                                    PerspectiveClientData.CLIENT.player.setPitch(90.0F);
                                }
                            }
                            framebuffer.beginWrite(true);
                            PerspectiveClientData.CLIENT.gameRenderer.renderWorld(1.0F, 0L, new MatrixStack());
                            try {Thread.sleep(10L);} catch (InterruptedException ignored) {}
                            PerspectiveScreenshotRecorder.saveScreenshot(new File(assetsDirLoc), "panorama_" + l + ".png", framebuffer, (message) -> {
                            });
                        }
                        File pack_file = new File(rpDirLoc + "/pack.mcmeta");
                        if (pack_file.createNewFile()) {
                            FileWriter pack_writer = new FileWriter(pack_file);
                            pack_writer.write("{\"pack\": {\"pack_format\": 15, \"description\": \"" + panoramaName + "\"}}");
                            pack_writer.close();
                        }
                        PerspectiveClientData.CLIENT.player.sendMessage(PerspectiveTranslation.getTranslation("message.take_panorama_screenshot.success", new Object[]{Text.literal(panoramaName).formatted(Formatting.UNDERLINE).styled((style) -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, new File(rpDirLoc).getAbsolutePath())))}));
                        PerspectiveClientData.CLIENT.player.setPitch(pitch);
                        PerspectiveClientData.CLIENT.player.setYaw(yaw);
                        PerspectiveClientData.CLIENT.gameRenderer.setBlockOutlineEnabled(true);
                        PerspectiveClientData.CLIENT.getWindow().setFramebufferWidth(framebufferWidth);
                        PerspectiveClientData.CLIENT.getWindow().setFramebufferHeight(framebufferHeight);
                        framebuffer.delete();
                        PerspectiveClientData.CLIENT.gameRenderer.setRenderingPanorama(false);
                        PerspectiveClientData.CLIENT.getFramebuffer().beginWrite(true);
                    }
                } else {
                    PerspectiveData.LOGGER.error(PerspectiveData.PREFIX + "An error occurred whilst trying to take a panorama: Incompatible Mods: " + INCOMPATIBLE_MODS_FOUND.toString().replace("[", "").replace("]", ""));
                    PerspectiveClientData.CLIENT.player.sendMessage(PerspectiveTranslation.getTranslation("message.take_panorama_screenshot.failure", new Object[]{PerspectiveTranslation.getTranslation("message.take_panorama_screenshot.failure.incompatible_mods", new Object[]{INCOMPATIBLE_MODS_FOUND.toString().replace("[", "").replace("]", "")})}));
                }
            } catch (Exception error) {
                PerspectiveData.LOGGER.error(PerspectiveData.PREFIX + "An error occurred whilst trying to take a panorama: " + error);
                PerspectiveClientData.CLIENT.player.sendMessage(PerspectiveTranslation.getTranslation("message.take_panorama_screenshot.failure", new Object[]{error}));
            }
        }
    }
}
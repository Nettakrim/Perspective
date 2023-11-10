/*
    Perspective
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Perspective
    License: GNU LGPLv3
*/

package com.mclegoman.perspective.client.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mclegoman.perspective.client.util.JsonHelper;
import com.mclegoman.perspective.common.data.Data;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.util.Map;

public class ConfigDataLoader extends JsonDataLoader implements IdentifiableResourceReloadListener {
    public static int ZOOM_LEVEL;
    public static int ZOOM_INCREMENT_SIZE;
    public static String ZOOM_MODE;
    public static boolean ZOOM_HIDE_HUD;
    public static boolean ZOOM_OVERLAY_MESSAGE;
    public static boolean HOLD_PERSPECTIVE_HIDE_HUD;
    public static int SUPER_SECRET_SETTINGS;
    public static String SUPER_SECRET_SETTINGS_MODE;
    public static boolean SUPER_SECRET_SETTINGS_ENABLED;
    public static boolean SUPER_SECRET_SETTINGS_SOUND;
    public static boolean SUPER_SECRET_SETTINGS_OPTIONS_SCREEN;
    public static boolean SUPER_SECRET_SETTINGS_OVERLAY_MESSAGE;
    public static boolean NAMED_TEXTURED_ENTITY;
    public static boolean RANDOM_TEXTURED_ENTITY;
    public static boolean ALLOW_APRIL_FOOLS;
    public static boolean FORCE_APRIL_FOOLS;
    public static boolean FORCE_PRIDE;
    public static boolean FORCE_PRIDE_TYPE;
    public static int FORCE_PRIDE_TYPE_INDEX;
    public static boolean VERSION_OVERLAY;
    public static boolean HIDE_ARMOR;
    public static boolean HIDE_NAMETAGS;
    public static String DETECT_UPDATE_CHANNEL;
    public static boolean TUTORIALS;
    public static boolean HIDE_BLOCK_OUTLINE;
    public static boolean HIDE_CROSSHAIR;
    public static boolean SHOW_DEATH_COORDINATES;
    public static final String ID = "config";
    public ConfigDataLoader() {
        super(new Gson(), ID);
    }

    @Override
    public void apply(Map<Identifier, JsonElement> prepared, ResourceManager manager, Profiler profiler) {
        try {
            for (Resource resource : manager.getAllResources(new Identifier(Data.PERSPECTIVE_VERSION.getID(), ID + ".json"))) {
                ZOOM_LEVEL = net.minecraft.util.JsonHelper.getInt(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "zoom_level", 80);
                ZOOM_INCREMENT_SIZE = net.minecraft.util.JsonHelper.getInt(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "zoom_increment_size", 1);
                ZOOM_MODE = net.minecraft.util.JsonHelper.getString(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "zoom_mode", "smooth");
                ZOOM_HIDE_HUD = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "zoom_hide_hud", false);
                ZOOM_OVERLAY_MESSAGE = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "zoom_overlay_message", false);
                HOLD_PERSPECTIVE_HIDE_HUD = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "hold_perspective_hide_hud", true);
                SUPER_SECRET_SETTINGS = net.minecraft.util.JsonHelper.getInt(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "super_secret_settings", 0);
                SUPER_SECRET_SETTINGS_MODE = JsonHelper.getShaderMode(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "super_secret_settings_mode", "game");
                SUPER_SECRET_SETTINGS_ENABLED = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "super_secret_settings_enabled", false);
                SUPER_SECRET_SETTINGS_SOUND = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "super_secret_settings_sound", true);
                SUPER_SECRET_SETTINGS_OPTIONS_SCREEN = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "super_secret_settings_options_screen", false);
                SUPER_SECRET_SETTINGS_OVERLAY_MESSAGE = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "super_secret_settings_overlay_message", true);
                NAMED_TEXTURED_ENTITY = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "named_textured_entity", true);
                RANDOM_TEXTURED_ENTITY = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "random_textured_entity", false);
                ALLOW_APRIL_FOOLS = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "allow_april_fools", true);
                FORCE_APRIL_FOOLS = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "force_april_fools", false);
                FORCE_PRIDE = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "force_pride", false);
                FORCE_PRIDE_TYPE = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "force_pride_type", false);
                FORCE_PRIDE_TYPE_INDEX = net.minecraft.util.JsonHelper.getInt(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "force_pride_type_index", 0);
                VERSION_OVERLAY = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "version_overlay", false);
                HIDE_ARMOR = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "hide_armor", false);
                HIDE_NAMETAGS = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "hide_nametags", false);
                DETECT_UPDATE_CHANNEL = JsonHelper.getDetectUpdateChannel(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "detect_update_channel", "release");
                TUTORIALS = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "tutorials", true);
                HIDE_BLOCK_OUTLINE = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "hide_block_outline", false);
                HIDE_CROSSHAIR = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "hide_crosshair", false);
                SHOW_DEATH_COORDINATES = net.minecraft.util.JsonHelper.getBoolean(net.minecraft.util.JsonHelper.deserialize(resource.getReader()), "show_death_coordinates", false);
            }
            ConfigHelper.loadConfig();
        } catch (Exception error) {
            Data.PERSPECTIVE_VERSION.getLogger().warn("{} Failed to load default config values: {}", Data.PERSPECTIVE_VERSION.getLoggerPrefix(), error);
        }
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier(Data.PERSPECTIVE_VERSION.getID(), ID);
    }
}
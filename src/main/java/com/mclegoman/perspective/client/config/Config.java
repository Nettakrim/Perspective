/*
    Perspective
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Perspective
    License: GNU LGPLv3
*/

package com.mclegoman.perspective.client.config;

import com.mclegoman.perspective.common.data.Data;
import com.mclegoman.simplefabriclibs.simple_config.SimpleConfig;
import com.mojang.datafixers.util.Pair;

public class Config {
    protected static final String ID = Data.PERSPECTIVE_VERSION.getID();
    protected static SimpleConfig CONFIG;
    protected static ConfigProvider CONFIG_PROVIDER;
    protected static int ZOOM_LEVEL;
    protected static int ZOOM_INCREMENT_SIZE;
    protected static String ZOOM_MODE;
    protected static boolean ZOOM_HIDE_HUD;
    protected static boolean ZOOM_OVERLAY_MESSAGE;
    protected static boolean HOLD_PERSPECTIVE_HIDE_HUD;
    protected static int SUPER_SECRET_SETTINGS;
    protected static String SUPER_SECRET_SETTINGS_MODE;
    protected static boolean SUPER_SECRET_SETTINGS_ENABLED;
    protected static boolean SUPER_SECRET_SETTINGS_SOUND;
    protected static boolean SUPER_SECRET_SETTINGS_OPTIONS_SCREEN;
    protected static boolean SUPER_SECRET_SETTINGS_OVERLAY_MESSAGE;
    protected static boolean NAMED_TEXTURED_ENTITY;
    protected static boolean RANDOM_TEXTURED_ENTITY;
    protected static boolean ALLOW_APRIL_FOOLS;
    protected static boolean FORCE_APRIL_FOOLS;
    protected static boolean FORCE_PRIDE;
    protected static boolean FORCE_PRIDE_TYPE;
    protected static int FORCE_PRIDE_TYPE_INDEX;
    protected static boolean VERSION_OVERLAY;
    protected static boolean HIDE_ARMOR;
    protected static boolean HIDE_NAMETAGS;
    protected static String DETECT_UPDATE_CHANNEL;
    protected static boolean TUTORIALS;
    protected static boolean HIDE_BLOCK_OUTLINE;
    protected static boolean HIDE_CROSSHAIR;
    protected static boolean SHOW_DEATH_COORDINATES;
    protected static int CONFIG_VERSION;
    protected static void init() {
        try {
            CONFIG_PROVIDER = new ConfigProvider();
            create();
            CONFIG = SimpleConfig.of(ID).provider(CONFIG_PROVIDER).request();
            assign();
        } catch (Exception error) {
            Data.PERSPECTIVE_VERSION.getLogger().warn("{} Failed to initialize {} config: {}", Data.PERSPECTIVE_VERSION.getLoggerPrefix(), ID, error);
        }
    }
    protected static void create() {
        CONFIG_PROVIDER.add(new Pair<>("zoom_level", ConfigDataLoader.ZOOM_LEVEL));
        CONFIG_PROVIDER.add(new Pair<>("zoom_increment_size", ConfigDataLoader.ZOOM_INCREMENT_SIZE));
        CONFIG_PROVIDER.add(new Pair<>("zoom_mode", ConfigDataLoader.ZOOM_MODE));
        CONFIG_PROVIDER.add(new Pair<>("zoom_hide_hud", ConfigDataLoader.ZOOM_HIDE_HUD));
        CONFIG_PROVIDER.add(new Pair<>("zoom_overlay_message", ConfigDataLoader.ZOOM_OVERLAY_MESSAGE));
        CONFIG_PROVIDER.add(new Pair<>("hold_perspective_hide_hud", ConfigDataLoader.HOLD_PERSPECTIVE_HIDE_HUD));
        CONFIG_PROVIDER.add(new Pair<>("super_secret_settings", ConfigDataLoader.SUPER_SECRET_SETTINGS));
        CONFIG_PROVIDER.add(new Pair<>("super_secret_settings_mode", ConfigDataLoader.SUPER_SECRET_SETTINGS_MODE));
        CONFIG_PROVIDER.add(new Pair<>("super_secret_settings_enabled", ConfigDataLoader.SUPER_SECRET_SETTINGS_ENABLED));
        CONFIG_PROVIDER.add(new Pair<>("super_secret_settings_sound", ConfigDataLoader.SUPER_SECRET_SETTINGS_SOUND));
        CONFIG_PROVIDER.add(new Pair<>("super_secret_settings_options_screen", ConfigDataLoader.SUPER_SECRET_SETTINGS_OPTIONS_SCREEN));
        CONFIG_PROVIDER.add(new Pair<>("super_secret_settings_overlay_message", ConfigDataLoader.SUPER_SECRET_SETTINGS_OVERLAY_MESSAGE));
        CONFIG_PROVIDER.add(new Pair<>("textured_named_entity", ConfigDataLoader.NAMED_TEXTURED_ENTITY));
        CONFIG_PROVIDER.add(new Pair<>("textured_random_entity", ConfigDataLoader.RANDOM_TEXTURED_ENTITY));
        CONFIG_PROVIDER.add(new Pair<>("allow_april_fools", ConfigDataLoader.ALLOW_APRIL_FOOLS));
        CONFIG_PROVIDER.add(new Pair<>("force_april_fools", ConfigDataLoader.FORCE_APRIL_FOOLS));
        CONFIG_PROVIDER.add(new Pair<>("force_pride", ConfigDataLoader.FORCE_PRIDE));
        CONFIG_PROVIDER.add(new Pair<>("force_pride_type", ConfigDataLoader.FORCE_PRIDE_TYPE));
        CONFIG_PROVIDER.add(new Pair<>("force_pride_type_index", ConfigDataLoader.FORCE_PRIDE_TYPE_INDEX));
        CONFIG_PROVIDER.add(new Pair<>("version_overlay", ConfigDataLoader.VERSION_OVERLAY));
        CONFIG_PROVIDER.add(new Pair<>("hide_armor", ConfigDataLoader.HIDE_ARMOR));
        CONFIG_PROVIDER.add(new Pair<>("hide_nametags", ConfigDataLoader.HIDE_NAMETAGS));
        CONFIG_PROVIDER.add(new Pair<>("detect_update_channel", ConfigDataLoader.DETECT_UPDATE_CHANNEL));
        CONFIG_PROVIDER.add(new Pair<>("tutorials", ConfigDataLoader.TUTORIALS));
        CONFIG_PROVIDER.add(new Pair<>("hide_block_outline", ConfigDataLoader.HIDE_BLOCK_OUTLINE));
        CONFIG_PROVIDER.add(new Pair<>("hide_crosshair", ConfigDataLoader.HIDE_CROSSHAIR));
        CONFIG_PROVIDER.add(new Pair<>("show_death_coordinates", ConfigDataLoader.SHOW_DEATH_COORDINATES));
        CONFIG_PROVIDER.add(new Pair<>("config_version", ConfigHelper.DEFAULT_CONFIG_VERSION));
    }
    protected static void assign() {
        ZOOM_LEVEL = CONFIG.getOrDefault("zoom_level", ConfigDataLoader.ZOOM_LEVEL);
        ZOOM_INCREMENT_SIZE = CONFIG.getOrDefault("zoom_increment_size", ConfigDataLoader.ZOOM_INCREMENT_SIZE);
        ZOOM_MODE = CONFIG.getOrDefault("zoom_mode", ConfigDataLoader.ZOOM_MODE);
        ZOOM_HIDE_HUD = CONFIG.getOrDefault("zoom_hide_hud", ConfigDataLoader.ZOOM_HIDE_HUD);
        ZOOM_OVERLAY_MESSAGE = CONFIG.getOrDefault("zoom_overlay_message", ConfigDataLoader.ZOOM_OVERLAY_MESSAGE);
        HOLD_PERSPECTIVE_HIDE_HUD = CONFIG.getOrDefault("hold_perspective_hide_hud", ConfigDataLoader.HOLD_PERSPECTIVE_HIDE_HUD);
        SUPER_SECRET_SETTINGS = CONFIG.getOrDefault("super_secret_settings", ConfigDataLoader.SUPER_SECRET_SETTINGS);
        SUPER_SECRET_SETTINGS_MODE = CONFIG.getOrDefault("super_secret_settings_mode", ConfigDataLoader.SUPER_SECRET_SETTINGS_MODE);
        SUPER_SECRET_SETTINGS_ENABLED = CONFIG.getOrDefault("super_secret_settings_enabled", ConfigDataLoader.SUPER_SECRET_SETTINGS_ENABLED);
        SUPER_SECRET_SETTINGS_SOUND = CONFIG.getOrDefault("super_secret_settings_sound", ConfigDataLoader.SUPER_SECRET_SETTINGS_SOUND);
        SUPER_SECRET_SETTINGS_OPTIONS_SCREEN = CONFIG.getOrDefault("super_secret_settings_options_screen", ConfigDataLoader.SUPER_SECRET_SETTINGS_OPTIONS_SCREEN);
        SUPER_SECRET_SETTINGS_OVERLAY_MESSAGE = CONFIG.getOrDefault("super_secret_settings_overlay_message", ConfigDataLoader.SUPER_SECRET_SETTINGS_OVERLAY_MESSAGE);
        NAMED_TEXTURED_ENTITY = CONFIG.getOrDefault("textured_named_entity", ConfigDataLoader.NAMED_TEXTURED_ENTITY);
        RANDOM_TEXTURED_ENTITY = CONFIG.getOrDefault("textured_random_entity", ConfigDataLoader.RANDOM_TEXTURED_ENTITY);
        ALLOW_APRIL_FOOLS = CONFIG.getOrDefault("allow_april_fools", ConfigDataLoader.ALLOW_APRIL_FOOLS);
        FORCE_APRIL_FOOLS = CONFIG.getOrDefault("force_april_fools", ConfigDataLoader.FORCE_APRIL_FOOLS);
        FORCE_PRIDE = CONFIG.getOrDefault("force_pride", ConfigDataLoader.FORCE_PRIDE);
        FORCE_PRIDE_TYPE = CONFIG.getOrDefault("force_pride_type", ConfigDataLoader.FORCE_PRIDE_TYPE);
        FORCE_PRIDE_TYPE_INDEX = CONFIG.getOrDefault("force_pride_type_index", ConfigDataLoader.FORCE_PRIDE_TYPE_INDEX);
        VERSION_OVERLAY = CONFIG.getOrDefault("version_overlay", ConfigDataLoader.VERSION_OVERLAY);
        HIDE_ARMOR = CONFIG.getOrDefault("hide_armor", ConfigDataLoader.HIDE_ARMOR);
        HIDE_NAMETAGS = CONFIG.getOrDefault("hide_nametags", ConfigDataLoader.HIDE_NAMETAGS);
        DETECT_UPDATE_CHANNEL = CONFIG.getOrDefault("detect_update_channel", ConfigDataLoader.DETECT_UPDATE_CHANNEL);
        TUTORIALS = CONFIG.getOrDefault("tutorials", ConfigDataLoader.TUTORIALS);
        HIDE_BLOCK_OUTLINE = CONFIG.getOrDefault("hide_block_outline", ConfigDataLoader.HIDE_BLOCK_OUTLINE);
        HIDE_CROSSHAIR = CONFIG.getOrDefault("hide_crosshair", ConfigDataLoader.HIDE_CROSSHAIR);
        SHOW_DEATH_COORDINATES = CONFIG.getOrDefault("show_death_coordinates", ConfigDataLoader.SHOW_DEATH_COORDINATES);
        CONFIG_VERSION = CONFIG.getOrDefault("config_version", ConfigHelper.DEFAULT_CONFIG_VERSION);
    }
    protected static void save() {
        Data.PERSPECTIVE_VERSION.getLogger().info("{} Writing config to file.", Data.PERSPECTIVE_VERSION.getLoggerPrefix());
        CONFIG_PROVIDER.setConfig("zoom_level", ZOOM_LEVEL);
        CONFIG_PROVIDER.setConfig("zoom_increment_size", ZOOM_INCREMENT_SIZE);
        CONFIG_PROVIDER.setConfig("zoom_mode", ZOOM_MODE);
        CONFIG_PROVIDER.setConfig("zoom_hide_hud", ZOOM_HIDE_HUD);
        CONFIG_PROVIDER.setConfig("zoom_overlay_message", ZOOM_OVERLAY_MESSAGE);
        CONFIG_PROVIDER.setConfig("hold_perspective_hide_hud", HOLD_PERSPECTIVE_HIDE_HUD);
        CONFIG_PROVIDER.setConfig("super_secret_settings", SUPER_SECRET_SETTINGS);
        CONFIG_PROVIDER.setConfig("super_secret_settings_mode", SUPER_SECRET_SETTINGS_MODE);
        CONFIG_PROVIDER.setConfig("super_secret_settings_enabled", SUPER_SECRET_SETTINGS_ENABLED);
        CONFIG_PROVIDER.setConfig("super_secret_settings_sound", SUPER_SECRET_SETTINGS_SOUND);
        CONFIG_PROVIDER.setConfig("super_secret_settings_options_screen", SUPER_SECRET_SETTINGS_OPTIONS_SCREEN);
        CONFIG_PROVIDER.setConfig("super_secret_settings_overlay_message", SUPER_SECRET_SETTINGS_OVERLAY_MESSAGE);
        CONFIG_PROVIDER.setConfig("named_textured_entity", NAMED_TEXTURED_ENTITY);
        CONFIG_PROVIDER.setConfig("random_textured_entity", RANDOM_TEXTURED_ENTITY);
        CONFIG_PROVIDER.setConfig("allow_april_fools", ALLOW_APRIL_FOOLS);
        CONFIG_PROVIDER.setConfig("force_april_fools", FORCE_APRIL_FOOLS);
        CONFIG_PROVIDER.setConfig("force_pride", FORCE_PRIDE);
        CONFIG_PROVIDER.setConfig("force_pride_type", FORCE_PRIDE_TYPE);
        CONFIG_PROVIDER.setConfig("force_pride_type_index", FORCE_PRIDE_TYPE_INDEX);
        CONFIG_PROVIDER.setConfig("version_overlay", VERSION_OVERLAY);
        CONFIG_PROVIDER.setConfig("hide_armor", HIDE_ARMOR);
        CONFIG_PROVIDER.setConfig("hide_nametags", HIDE_NAMETAGS);
        CONFIG_PROVIDER.setConfig("detect_update_channel", DETECT_UPDATE_CHANNEL);
        CONFIG_PROVIDER.setConfig("tutorials", TUTORIALS);
        CONFIG_PROVIDER.setConfig("hide_block_outline", HIDE_BLOCK_OUTLINE);
        CONFIG_PROVIDER.setConfig("hide_crosshair", HIDE_CROSSHAIR);
        CONFIG_PROVIDER.setConfig("show_death_coordinates", SHOW_DEATH_COORDINATES);
        CONFIG_PROVIDER.setConfig("config_version", ConfigHelper.DEFAULT_CONFIG_VERSION);
        CONFIG_PROVIDER.saveConfig(ID);
    }
}
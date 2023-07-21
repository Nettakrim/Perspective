package com.mclegoman.perspective.client.translation;

import net.minecraft.util.StringIdentifiable;

public enum PerspectiveTranslationType implements StringIdentifiable {
    ENDISABLE("endisable"),
    ONFF("onff"),
    SHADER_MODE("shader_mode");
    private final String name;
    PerspectiveTranslationType(String name) {
        this.name = name;
    }
    @Override
    public String asString() {
        return this.name;
    }
}
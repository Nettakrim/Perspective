/*
    Perspective
    Author: MCLegoMan
    Github: https://github.com/MCLegoMan/Perspective
    License: CC-BY 4.0
*/

package com.mclegoman.perspective.mixin.client.entity_renderer;

import com.mclegoman.perspective.client.textured_entity.PerspectiveTexturedEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.DonkeyEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.DonkeyEntity;
import net.minecraft.entity.passive.MuleEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(priority = 10000, value = DonkeyEntityRenderer.class)
public class PerspectiveDonkeyEntityRenderer {
    @Inject(at = @At("RETURN"), method = "getTexture(Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/Identifier;", cancellable = true)
    private void perspective$getTexture(Entity entity, CallbackInfoReturnable<Identifier> cir) {
        if (entity instanceof MuleEntity) cir.setReturnValue(PerspectiveTexturedEntity.getTexture(entity, "minecraft:mule", "", cir.getReturnValue()));
        else if (entity instanceof DonkeyEntity) cir.setReturnValue(PerspectiveTexturedEntity.getTexture(entity, "minecraft:donkey", "", cir.getReturnValue()));
    }
}
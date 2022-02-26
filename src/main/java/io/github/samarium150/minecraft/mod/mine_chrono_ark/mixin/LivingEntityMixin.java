/*
 * Copyright (c) 2022 Samarium
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the  GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package io.github.samarium150.minecraft.mod.mine_chrono_ark.mixin;

import io.github.samarium150.minecraft.mod.mine_chrono_ark.init.WeaponRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    
    @Shadow
    public abstract boolean isHolding(Item pItem);
    
    @Shadow
    public abstract ModifiableAttributeInstance getAttribute(Attribute pAttribute);
    
    public LivingEntityMixin(EntityType<?> pType, World pLevel) {
        super(pType, pLevel);
    }
    
    @Inject(at=@At("RETURN"), method="detectEquipmentUpdates")
    private void detectEquipmentUpdates(CallbackInfo ci) {
        if (this.isHolding(WeaponRegistry.INSTANCE.getWOODEN_SWORD_PLUS_THIRTEEN())) {
            System.out.println("Attack Damage: " + this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
        }
    }
}

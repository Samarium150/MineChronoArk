/*
 * Copyright (c) 2022 Samarium
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/gpl-3.0.html>
 */
package io.github.samarium150.minecraft.mod.mine_chrono_ark.item.weapon

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import io.github.samarium150.minecraft.mod.mine_chrono_ark.entity.ai.attributes.WeaponAttributeModifiersProvider
import io.github.samarium150.minecraft.mod.mine_chrono_ark.item.ModItemGroup
import io.github.samarium150.minecraft.mod.mine_chrono_ark.item.ModRarity
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.clone
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.getDefaultModifiers
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.toImmutable
import net.minecraft.entity.ai.attributes.Attribute
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.ai.attributes.Attributes
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.ItemTier
import net.minecraft.item.SwordItem

/**
 * Rarity Legendary
 * Attack +2
 * Armor +10%
 * TODO: Evade -15%
 * TODO: Aggro slightly increased
 * TODO: If got attacked twice, counterattack (66% attack power)
 */
class Revenger : SwordItem(
    ItemTier.NETHERITE, 3, -2.4f, PROPERTIES
), WeaponAttributeModifiersProvider {

    companion object {
        const val NAME = "revenger"
        private val PROPERTIES = Properties().tab(ModItemGroup).rarity(ModRarity.LEGENDARY)
        private val mainHandModifiers = HashMultimap.create<Attribute, AttributeModifier>()
        private val offhandModifiers = HashMultimap.create<Attribute, AttributeModifier>()
    }

    init {
        Companion.offhandModifiers.put(
            Attributes.ATTACK_DAMAGE,
            AttributeModifier(
                "${NAME}_attack_booster",
                2.0,
                AttributeModifier.Operation.ADDITION
            )
        )
        Companion.offhandModifiers.put(
            Attributes.ARMOR,
            AttributeModifier(
                "${NAME}_armor_booster",
                0.1,
                AttributeModifier.Operation.MULTIPLY_BASE
            )
        )
        Companion.mainHandModifiers.putAll(this.getDefaultModifiers())
        Companion.mainHandModifiers.putAll(Companion.offhandModifiers.clone())
    }

    override val mainHandModifiers: Multimap<Attribute, AttributeModifier>
        get() = Companion.mainHandModifiers.toImmutable()
    override val offhandModifiers: Multimap<Attribute, AttributeModifier>
        get() = Companion.offhandModifiers.toImmutable()

    override fun getDefaultAttributeModifiers(slot: EquipmentSlotType): Multimap<Attribute, AttributeModifier> {
        return super<WeaponAttributeModifiersProvider>.getDefaultAttributeModifiers(slot)
    }
}

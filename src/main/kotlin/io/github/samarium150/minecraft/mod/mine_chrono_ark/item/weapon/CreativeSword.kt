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
 * along with this program. If not, see <https://www.gnu.org/licenses/gpl-3.0.html>.
 */
package io.github.samarium150.minecraft.mod.mine_chrono_ark.item.weapon

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import io.github.samarium150.minecraft.mod.mine_chrono_ark.entity.ai.attributes.ArmorPenetration
import io.github.samarium150.minecraft.mod.mine_chrono_ark.entity.ai.attributes.CriticalChance
import io.github.samarium150.minecraft.mod.mine_chrono_ark.entity.ai.attributes.CriticalDamage
import io.github.samarium150.minecraft.mod.mine_chrono_ark.entity.ai.attributes.WeaponAttributeModifiersProvider
import io.github.samarium150.minecraft.mod.mine_chrono_ark.item.ModItemGroup
import io.github.samarium150.minecraft.mod.mine_chrono_ark.item.ModRarity
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.clone
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.getDefaultModifiers
import net.minecraft.entity.ai.attributes.Attribute
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.ai.attributes.Attributes
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemTier
import net.minecraft.item.SwordItem

class CreativeSword : SwordItem(
    ItemTier.NETHERITE, 3,-2.4f, PROPERTIES
), WeaponAttributeModifiersProvider {

    companion object {
        const val NAME = "creative_sword"
        val PROPERTIES: Properties = Properties()
            .tab(ModItemGroup)
            .rarity(ModRarity.CREATIVE)
            .fireResistant()
        val mainHandModifiers: Multimap<Attribute, AttributeModifier> = HashMultimap.create()
        val offhandModifiers: Multimap<Attribute, AttributeModifier> = HashMultimap.create()
    }

    init {
        mainHandModifiers.putAll(
            Attributes.ATTACK_DAMAGE,
            listOf(AttributeModifier(
                "${NAME}_attack_booster",
                2.0,
                AttributeModifier.Operation.ADDITION
            ), AttributeModifier(
                "${NAME}_attack_multiplier",
                0.10,
                AttributeModifier.Operation.MULTIPLY_BASE
            ))
        )
        mainHandModifiers.put(
            ArmorPenetration,
            AttributeModifier(
                "${NAME}_armor_penetration",
                50.0,
                AttributeModifier.Operation.ADDITION
            )
        )
        mainHandModifiers.put(
            CriticalChance,
            AttributeModifier(
                "${NAME}_critical_chance",
                100.0,
                AttributeModifier.Operation.ADDITION
            )
        )
        mainHandModifiers.put(
            CriticalDamage,
            AttributeModifier(
                "${NAME}_critical_damage",
                100.0,
                AttributeModifier.Operation.ADDITION
            )
        )
        offhandModifiers.putAll(mainHandModifiers.clone())
        mainHandModifiers.putAll(this.getDefaultModifiers())
    }

    override val mainHandModifiers: Multimap<Attribute, AttributeModifier>
        get() = Companion.mainHandModifiers
    override val offhandModifiers: Multimap<Attribute, AttributeModifier>
        get() = Companion.offhandModifiers

    override fun isDamageable(stack: ItemStack?): Boolean {
        val tag = stack?.orCreateTag ?: return false
        tag.putBoolean("Unbreakable", true)
        return false
    }

    override fun getDefaultAttributeModifiers(slot: EquipmentSlotType):
        Multimap<Attribute, AttributeModifier> {
        return super<WeaponAttributeModifiersProvider>.getDefaultAttributeModifiers(slot)
    }
}

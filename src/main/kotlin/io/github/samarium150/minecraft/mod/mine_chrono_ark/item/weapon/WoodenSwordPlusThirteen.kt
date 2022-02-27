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
package io.github.samarium150.minecraft.mod.mine_chrono_ark.item.weapon

import com.google.common.collect.HashMultimap
import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import io.github.samarium150.minecraft.mod.mine_chrono_ark.item.ModItemGroup
import io.github.samarium150.minecraft.mod.mine_chrono_ark.item.ModRarity
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.tooltipPrefix
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.entity.ai.attributes.Attribute
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.ai.attributes.Attributes
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemTier
import net.minecraft.item.SwordItem
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.StringTextComponent
import net.minecraft.world.World

/**
 * Rarity Legendary
 * Armor Penetration +50%
 * Attack +13%
 * Attack +2
 */
class WoodenSwordPlusThirteen : SwordItem(
    ItemTier.WOOD, 3, -2.4f, PROPERTY
) {

    companion object {
        const val NAME = "wooden_sword_plus_thirteen"
        val PROPERTY: Properties = Properties().tab(ModItemGroup).rarity(ModRarity.LEGENDARY)
        val modifiers: HashMultimap<Attribute, AttributeModifier> = HashMultimap.create()
    }

    init {
        modifiers.putAll(
            Attributes.ATTACK_DAMAGE,
            listOf(AttributeModifier(
                "${NAME}_attack_booster",
                2.0,
                AttributeModifier.Operation.ADDITION
            ), AttributeModifier(
                "${NAME}_attack_multiplier",
                0.13,
                AttributeModifier.Operation.MULTIPLY_BASE
            ))
        )
        SwordItem::class.java.getDeclaredField("defaultModifiers").let {
            it.isAccessible = true
            @Suppress("UNCHECKED_CAST")
            val defaultModifiers = it.get(this) as Multimap<Attribute, AttributeModifier>
            modifiers.putAll(defaultModifiers)
            it.set(this, modifiers)
        }
    }

    override fun getDefaultAttributeModifiers(pEquipmentSlot: EquipmentSlotType):
        Multimap<Attribute, AttributeModifier> {
        return when (pEquipmentSlot) {
            EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND -> modifiers
            else -> ImmutableMultimap.of()
        }
    }

    override fun appendHoverText(
        stack: ItemStack,
        world: World?,
        components: MutableList<ITextComponent>,
        flag: ITooltipFlag
    ) {
        I18n.get(tooltipPrefix + NAME)
            .replace("$", "%")
            .split("\n")
            .map { StringTextComponent(it) }
            .forEach(components::add)
    }
}

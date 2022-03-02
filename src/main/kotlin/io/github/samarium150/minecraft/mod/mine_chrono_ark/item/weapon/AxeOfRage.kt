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
import com.google.common.collect.Multimap
import io.github.samarium150.minecraft.mod.mine_chrono_ark.item.ModItemGroup
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.clone
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.getDefaultModifiers
import net.minecraft.entity.ai.attributes.Attribute
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.ai.attributes.Attributes
import net.minecraft.item.AxeItem
import net.minecraft.item.ItemTier
import net.minecraft.item.Rarity

/**
 * Rarity Rare
 * Attack +28%
 * Speed -2
 */
class AxeOfRage : AxeItem(
    ItemTier.IRON, 6.0f, -3.1f, PROPERTIES
) {

    companion object {
        const val NAME = "axe_of_rage"
        val PROPERTIES: Properties = Properties().tab(ModItemGroup).rarity(Rarity.RARE)
        val mainHandModifiers: Multimap<Attribute, AttributeModifier> = HashMultimap.create()
        val offhandModifiers: Multimap<Attribute, AttributeModifier> = HashMultimap.create()
    }

    init {
        mainHandModifiers.put(
            Attributes.ATTACK_DAMAGE,
            AttributeModifier(
                "${NAME}_attack_multiplier",
                0.28,
                AttributeModifier.Operation.MULTIPLY_BASE
            )
        )
        mainHandModifiers.put(
            Attributes.MOVEMENT_SPEED,
            AttributeModifier(
                "${NAME}_speed_slowdown",
                -2.0,
                AttributeModifier.Operation.MULTIPLY_BASE
            )
        )
        offhandModifiers.putAll(WoodenSwordPlusThirteen.mainHandModifiers.clone())
        mainHandModifiers.putAll(this.getDefaultModifiers())
    }
}

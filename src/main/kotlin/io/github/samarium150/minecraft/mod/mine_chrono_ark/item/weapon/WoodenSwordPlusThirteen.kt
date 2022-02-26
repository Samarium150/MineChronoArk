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

import io.github.samarium150.minecraft.mod.mine_chrono_ark.item.ModItemGroup
import io.github.samarium150.minecraft.mod.mine_chrono_ark.item.ModRarity
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.tooltipPrefix
import net.minecraft.client.resources.I18n
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemTier
import net.minecraft.item.SwordItem
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.StringTextComponent
import net.minecraft.world.World
import java.util.*

class WoodenSwordPlusThirteen : SwordItem(
    ItemTier.WOOD, 3, -2.4f, PROPERTY
) {

    companion object {
        const val NAME = "wooden_sword_plus_thirteen"
        val PROPERTY: Properties = Properties().tab(ModItemGroup).rarity(ModRarity.LEGENDARY)
    }

    override fun appendHoverText(
        stack: ItemStack,
        world: World?,
        components: MutableList<ITextComponent>,
        flag: ITooltipFlag
    ) {
        Arrays.stream(
            I18n.get(tooltipPrefix + NAME)
                .replace("$", "%")
                .split("\n")
                .toTypedArray()
        ).map { str: String -> StringTextComponent(str) }
            .forEach { component: StringTextComponent -> components.add(component) }
    }
}

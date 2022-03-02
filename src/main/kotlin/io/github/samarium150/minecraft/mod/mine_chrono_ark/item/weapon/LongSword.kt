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
import net.minecraft.item.Item
import net.minecraft.item.ItemTier
import net.minecraft.item.Rarity
import net.minecraft.item.SwordItem

/**
 * Rarity Uncommon
 * Attack +10%
 * Critical Chance +3%
 * Accuracy +2%
 */
class LongSword : SwordItem(
    ItemTier.IRON, 3, -2.4F, PROPERTIES
) {

    companion object {
        const val NAME = "long_sword"
        val PROPERTIES: Properties = Properties().tab(ModItemGroup).rarity(Rarity.UNCOMMON)
    }
}

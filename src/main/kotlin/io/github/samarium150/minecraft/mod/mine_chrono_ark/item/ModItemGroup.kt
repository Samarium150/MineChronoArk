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
package io.github.samarium150.minecraft.mod.mine_chrono_ark.item

import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.MOD_ID
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

object ModItemGroup : ItemGroup(MOD_ID) {
    override fun makeIcon(): ItemStack {
        return ItemStack(Items.WOODEN_SWORD)
    }
}

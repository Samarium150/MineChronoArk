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
package io.github.samarium150.minecraft.mod.mine_chrono_ark.init

import io.github.samarium150.minecraft.mod.mine_chrono_ark.entity.ai.attributes.ArmorPenetration
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.MOD_ID
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object AttributeRegistry {

    val ATTRIBUTES = KDeferredRegister(ForgeRegistries.ATTRIBUTES, MOD_ID)

    val ARMOR_PENETRATION by ATTRIBUTES.registerObject(ArmorPenetration.NAME) {
        ArmorPenetration()
    }
}

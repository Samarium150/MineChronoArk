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
package io.github.samarium150.minecraft.mod.mine_chrono_ark.util

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import net.minecraft.entity.ai.attributes.Attribute
import net.minecraft.entity.ai.attributes.AttributeModifier

fun AttributeModifier.clone(): AttributeModifier {
    return AttributeModifier(this.name, this.amount, this.operation)
}

fun Multimap<Attribute, AttributeModifier>.clone(): Multimap<Attribute, AttributeModifier> {
    val res = HashMultimap.create<Attribute, AttributeModifier>()
    this.entries().forEach {
        res.put(it.key, it.value.clone())
    }
    return res
}

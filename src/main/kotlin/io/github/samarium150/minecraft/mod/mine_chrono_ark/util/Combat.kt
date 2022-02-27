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

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.attributes.Attributes
import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.util.CombatRules
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

private val logger: Logger = LogManager.getLogger("$MOD_ID.combat_utils")

fun LivingEntity.getArmorPenetratedDamage(damage: Float, percentage: Float): Float {
    logger.info("${this.name.string}(Armor: ${this.armorValue}, " +
        "Toughness: ${this.getAttributeValue(Attributes.ARMOR_TOUGHNESS)})")
    return CombatRules.getDamageAfterAbsorb(
        damage,
        this.armorValue * percentage,
        this.getAttributeValue(Attributes.ARMOR_TOUGHNESS).toFloat()
    )
}

fun LivingEntity.hurtArmor(damage: Float) {
    if (damage > 0) {
        var averagedDamage = damage * 0.25f
        if (averagedDamage < 1.0f) averagedDamage = 1.0f
        val armorSlots: MutableList<ItemStack> = ArrayList()
        this.armorSlots.forEach(armorSlots::add)
        logger.info(armorSlots)
        for (i in armorSlots.indices) {
            val slot = armorSlots[i]
            if (slot.item is ArmorItem) {
                slot.hurtAndBreak(
                    averagedDamage.toInt(), this
                ) { playerEntity: LivingEntity ->
                    playerEntity.broadcastBreakEvent(
                        EquipmentSlotType.byTypeAndIndex(EquipmentSlotType.Group.ARMOR, i)
                    )
                }
            }
        }
    }
}

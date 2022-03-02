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
package io.github.samarium150.minecraft.mod.mine_chrono_ark.event

import io.github.samarium150.minecraft.mod.mine_chrono_ark.entity.ai.attributes.ArmorPenetration
import io.github.samarium150.minecraft.mod.mine_chrono_ark.entity.ai.attributes.CriticalChance
import io.github.samarium150.minecraft.mod.mine_chrono_ark.entity.ai.attributes.CriticalDamage
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.MOD_ID
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.getArmorPenetratedDamage
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.hurtArmor
import net.minecraft.entity.LivingEntity
import net.minecraft.util.EntityDamageSource
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.eventbus.api.EventPriority
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@EventBusSubscriber
object WeaponEvents {

    private val logger: Logger = LogManager.getLogger("$MOD_ID.weapon_events")

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun onLivingHurtEvent(event: LivingHurtEvent) {
        val damageSource = event.source
        val sourceEntity = damageSource.entity
        val target = event.entityLiving
        var damage = event.amount
        logger.info(
            String.format(
                "%s -(%.2f)-> %s",
                if (sourceEntity != null) "${sourceEntity.name.string}/${damageSource.msgId}"
                else damageSource.msgId,
                damage,
                target.name.string
            )
        )
        if (damageSource is EntityDamageSource && sourceEntity is LivingEntity) {
            val criticalChanceAttribute = sourceEntity.getAttribute(CriticalChance)
            val criticalDamageAttribute = sourceEntity.getAttribute(CriticalDamage)
            val armorPenetrationAttribute = sourceEntity.getAttribute(ArmorPenetration)
            logger.info(sourceEntity.attributes.save())
            val weapon = sourceEntity.mainHandItem.item
            if (damageSource.msgId != weapon.descriptionId) {
                event.isCanceled = true
                val newDamageSource = EntityDamageSource(weapon.descriptionId, sourceEntity)
                logger.info("Original Damage: $damage")
                if (criticalChanceAttribute != null) {
                    val criticalChance = criticalChanceAttribute.value / 100
                    if (Math.random() <= criticalChance && criticalDamageAttribute != null) {
                        val criticalDamage = (criticalDamageAttribute.value / 100).toFloat()
                        logger.info(
                            "Critical Chance:${criticalChance}/" +
                            "Critical Damage:${criticalDamage}"
                        )
                        damage *= criticalDamage
                    }
                }
                logger.info("Critical Applied Damage: $damage")
                if (armorPenetrationAttribute != null) {
                    val armorPenetration = armorPenetrationAttribute.value / 100
                    if (armorPenetration > 0) {
                        newDamageSource.bypassArmor()
                        target.hurtArmor(damage)
                        damage = target.getArmorPenetratedDamage(damage, armorPenetration.toFloat())
                    }
                    logger.info("Armor Penetration: $armorPenetration")
                }
                logger.info("Final Damage: $damage")
                target.invulnerableTime = 0
                target.hurt(newDamageSource, damage)
            }
        }
        logger.info(String.format("target HP: %f/%f", target.health, target.maxHealth))
    }
}

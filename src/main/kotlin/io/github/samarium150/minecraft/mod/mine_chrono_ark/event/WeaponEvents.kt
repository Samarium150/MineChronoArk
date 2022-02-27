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
package io.github.samarium150.minecraft.mod.mine_chrono_ark.event

import io.github.samarium150.minecraft.mod.mine_chrono_ark.init.WeaponRegistry
import io.github.samarium150.minecraft.mod.mine_chrono_ark.item.weapon.WoodenSwordPlusThirteen
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.MOD_ID
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.getArmorPenetratedDamage
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.hurtArmor
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.attributes.Attributes
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
        val damage = event.amount
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
            logger.info("${sourceEntity.getAttribute(Attributes.ATTACK_DAMAGE)?.modifiers}")
            val weapon = sourceEntity.mainHandItem.item
            if (weapon === WeaponRegistry.WOODEN_SWORD_PLUS_THIRTEEN.item &&
                damageSource.msgId != WoodenSwordPlusThirteen.NAME
            ) {
                event.isCanceled = true
                val finalDamage: Float = target.getArmorPenetratedDamage(damage, 0.5f)
                logger.info("\tFinal damage: $finalDamage")
                target.hurtArmor(damage)
                val bypassedDamage = EntityDamageSource(
                    WoodenSwordPlusThirteen.NAME, sourceEntity
                ).bypassArmor()
                target.invulnerableTime = 0
                target.hurt(bypassedDamage, finalDamage)
            }
        }
        logger.info(String.format("target HP: %f/%f", target.health, target.maxHealth))
    }
}

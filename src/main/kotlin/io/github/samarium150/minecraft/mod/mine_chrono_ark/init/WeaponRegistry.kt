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
package io.github.samarium150.minecraft.mod.mine_chrono_ark.init

import io.github.samarium150.minecraft.mod.mine_chrono_ark.item.weapon.*
import io.github.samarium150.minecraft.mod.mine_chrono_ark.util.MOD_ID
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

@Suppress("unused")
object WeaponRegistry {

    val WEAPONS = KDeferredRegister(ForgeRegistries.ITEMS, MOD_ID)

    val CREATIVE_SWORD by WEAPONS.registerObject(CreativeSword.NAME) {
        CreativeSword()
    }

    // Uncommon
    val LONG_SWORD by WEAPONS.registerObject(LongSword.NAME) {
        LongSword()
    }
    val RUSTY_DAGGER by WEAPONS.registerObject(RustyDagger.NAME) {
        RustyDagger()
    }
    val BASTARD_SWORD by WEAPONS.registerObject(BastardSword.NAME) {
        BastardSword()
    }

    // Rare
    val RAPIER by WEAPONS.registerObject(Rapier.NAME) {
        Rapier()
    }
    val AXE_OF_RAGE by WEAPONS.registerObject(AxeOfRage.NAME) {
        AxeOfRage()
    }
    val RUSTY_HAMMER by WEAPONS.registerObject(RustyHammer.NAME) {
        RustyHammer()
    }

    // EPic

    // Legendary
    val WOODEN_SWORD_PLUS_THIRTEEN by WEAPONS.registerObject(WoodenSwordPlusThirteen.NAME) {
        WoodenSwordPlusThirteen()
    }
    val BLACK_CRESCENT_SWORD by WEAPONS.registerObject(BlackCrescent.NAME) {
        BlackCrescent()
    }
    val FORGOTTEN_KINGS_BLADE by WEAPONS.registerObject(ForgottenKingsBlade.NAME) {
        ForgottenKingsBlade()
    }
    val MESSIAH_BLADE_PROTOTYPE by WEAPONS.registerObject(MessiahBladePrototype.NAME) {
        MessiahBladePrototype()
    }
    val KARSAGA by WEAPONS.registerObject(Karsaga.NAME) {
        Karsaga()
    }
    val REVENGER by WEAPONS.registerObject(Revenger.NAME) {
        Revenger()
    }
    val DARK_FLAME_SWORD by WEAPONS.registerObject(DarkFlameSword.NAME) {
        DarkFlameSword()
    }
}

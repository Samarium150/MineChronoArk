package io.github.samarium150.minecraft.mod.mine_chrono_ark.entity.ai.attributes

import com.google.common.collect.Multimap
import net.minecraft.entity.ai.attributes.Attribute
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.inventory.EquipmentSlotType

interface AttributeModifiersProvider {
    val mainHandModifiers: Multimap<Attribute, AttributeModifier>
    val offhandModifiers: Multimap<Attribute, AttributeModifier>
    fun getDefaultAttributeModifiers(slot: EquipmentSlotType): Multimap<Attribute, AttributeModifier>
}

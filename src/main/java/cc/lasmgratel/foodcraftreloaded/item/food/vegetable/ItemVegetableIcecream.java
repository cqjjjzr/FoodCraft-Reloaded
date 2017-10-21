/*
 * FoodCraft Mod - Add more food to your Minecraft.
 * Copyright (C) 2017 Lasm Gratel
 *
 * This file is part of FoodCraft Mod.
 *
 * FoodCraft Mod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FoodCraft Mod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FoodCraft Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package cc.lasmgratel.foodcraftreloaded.item.food.vegetable;

import cc.lasmgratel.foodcraftreloaded.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.init.FCRCreativeTabs;
import cc.lasmgratel.foodcraftreloaded.item.food.FCRItemFood;
import cc.lasmgratel.foodcraftreloaded.util.Translator;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

public class ItemVegetableIcecream extends FCRItemFood implements VegetableTyped {
    private VegetableType vegetableType;

    public ItemVegetableIcecream(VegetableType vegetableType) {
        super(5, 1.0f, false);
        setRegistryName(FoodCraftReloaded.MODID, vegetableType.toString() + "_icecream");
        setCreativeTab(FCRCreativeTabs.SNACK);
        this.vegetableType = vegetableType;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return Translator.format("item.iceCream", Translator.format("item.vegetable" + StringUtils.capitalize(vegetableType.toString())));
    }

    @Override
    public VegetableType getVegetableType() {
        return vegetableType;
    }
}
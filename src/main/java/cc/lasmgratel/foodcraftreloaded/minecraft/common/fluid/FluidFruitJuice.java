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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.fluid;

import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.fruit.FruitType;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.Translator;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.enumeration.FruitTyped;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.StringUtils;

public class FluidFruitJuice extends Fluid implements FruitTyped {
    private FruitType fruitType;

    public FluidFruitJuice(FruitType fruitType) {
        super(NameBuilder.buildRegistryName(fruitType.toString(), "juice"), new ResourceLocation(FoodCraftReloadedMod.MODID, "fluids/juice_still"),  new ResourceLocation(FoodCraftReloadedMod.MODID, "fluids/juice_flow"));
        this.fruitType = fruitType;
        setViscosity(2000);

    }

    @Override
    public int getColor() {
        return fruitType.getColor().getRGB();
    }

    @Override
    public String getLocalizedName(FluidStack stack) {
        return Translator.format("fluid.juice", Translator.format("item.fruit" + StringUtils.capitalize(fruitType.toString())));
    }

    public FruitType getType() {
        return fruitType;
    }
}

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

package cc.lasmgratel.foodcraftreloaded.common.loader;

import cc.lasmgratel.foodcraftreloaded.common.block.BlockFluidVegetableJuice;
import cc.lasmgratel.foodcraftreloaded.common.block.BlockVegetableCake;
import cc.lasmgratel.foodcraftreloaded.common.fluid.FluidVegetableJuice;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.*;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.Load;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Arrays;
import java.util.EnumMap;

public class VegetableEnumLoader extends EnumLoader<VegetableType> {
    private EnumMap<VegetableType, FluidVegetableJuice> fluidJuiceEnumMap = new EnumMap<>(VegetableType.class);

    public EnumMap<VegetableType, FluidVegetableJuice> getFluidJuiceEnumMap() {
        return fluidJuiceEnumMap;
    }

    @Load
    public void loadVegetables() {
        for (VegetableType vegetableType : VegetableType.values()) {
            FluidVegetableJuice fluid = new FluidVegetableJuice(vegetableType);
            FluidRegistry.registerFluid(fluid);
            FluidRegistry.addBucketForFluid(fluid);
            fluidJuiceEnumMap.put(vegetableType, fluid);
        }
        Class[] values = new Class[] {
            BlockFluidVegetableJuice.class,
            BlockVegetableCake.class, ItemVegetable.class, ItemVegetableYogurt.class, ItemVegetableCake.class,
            ItemVegetableIcecream.class, ItemVegetableJuice.class, ItemVegetableLiqueur.class, ItemVegetableSoda.class
        };
        Arrays.stream(values).forEach(this::putValue);
        register();
    }

    @Load(side = Side.CLIENT)
    public void loadRenders() {
        registerRenders();
    }

    @Load(side = Side.CLIENT, value = LoaderState.POSTINITIALIZATION)
    public void loadColors() {
        registerColors();
    }
}

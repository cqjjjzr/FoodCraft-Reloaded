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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.vegetable;

import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.api.init.FCRCreativeTabs;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.Translator;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.enumeration.VegetableTyped;
import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.masking.CustomModelMasking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemVegetableCake extends Item implements VegetableTyped, CustomModelMasking {
    private VegetableType vegetableType;

    public ItemVegetableCake(VegetableType vegetableType) {
        this.vegetableType = vegetableType;
        setRegistryName(FoodCraftReloadedMod.MODID, NameBuilder.buildRegistryName(vegetableType.toString(), "cake"));
        setCreativeTab(FCRCreativeTabs.SNACK);
    }

    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        Block cake = Block.REGISTRY.getObject(new ResourceLocation(FoodCraftReloadedMod.MODID, NameBuilder.buildRegistryName(vegetableType.toString(), "cake")));

        if (block == Blocks.SNOW_LAYER && iblockstate.getValue(BlockSnow.LAYERS) < 1)
            facing = EnumFacing.UP;
        else if (!block.isReplaceable(worldIn, pos))
            pos = pos.offset(facing);

        ItemStack itemstack = player.getHeldItem(hand);

        if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack) && worldIn.mayPlace(cake, pos, false, facing, null)) {
            IBlockState blockState = cake.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, player, hand);

            if (!worldIn.setBlockState(pos, blockState, 11))
                return EnumActionResult.FAIL;
            else {
                blockState = worldIn.getBlockState(pos);

                if (blockState.getBlock() == cake) {
                    ItemBlock.setTileEntityNBT(worldIn, player, pos, itemstack);
                    blockState.getBlock().onBlockPlacedBy(worldIn, pos, blockState, player, itemstack);
                }

                SoundType soundtype = blockState.getBlock().getSoundType(blockState, worldIn, pos, player);
                worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                itemstack.shrink(1);
                return EnumActionResult.SUCCESS;
            }
        }
        else
            return EnumActionResult.FAIL;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return Translator.format("item.cake", Translator.format("item.vegetable" + StringUtils.capitalize(NameBuilder.buildUnlocalizedName(vegetableType.toString()))));
    }

    @Nullable
    @Override
    public ModelResourceLocation getModelLocation() {
        return new ModelResourceLocation(new ResourceLocation(FoodCraftReloadedMod.MODID, "cake"), "inventory");
    }

    @Override
    public int getTintIndex() {
        return 0;
    }

    @Override
    public VegetableType getType() {
        return vegetableType;
    }
}

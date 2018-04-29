package com.farmsoft.youskyblock;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;

public class BlockInfo {
    
    public static String getFullName (String id) {
        IBlockState bs = getBlockState(id);
        return getBlockName(bs) + (!getVariantName(bs).equals("") ? "/" + getVariantName(bs) : "");
        
    }

    public static String getId (IBlockState blockState) {
        return Block.getIdFromBlock(blockState.getBlock()) + "/" + (Block.getStateId(blockState) >> 12);
    }
    
    public static String getBlockName(int bId) {
        return getBlockName(getBlockState(bId+ "/0"));
    }
    
    public static String getBlockName(IBlockState blockState) {
        if (blockState == null) {return null;}
        return blockState.getBlock().getRegistryName().toString();
    }
    
    public static String getVariantName(IBlockState blockState ) {
        if (blockState == null) {return null;}
        switch (getBlockName(blockState)) {
            case "minecraft:air" : return "";
            case "minecraft:dirt":
                return blockState.getValue(BlockDirt.VARIANT).getName();
            case "minecraft:stone":
                return blockState.getValue(BlockStone.VARIANT).getName();
            case "minecraft:leaves":
                return blockState.getValue(BlockOldLeaf.VARIANT).getName();
            case "minecraft:leaves2":
                return blockState.getValue(BlockNewLeaf.VARIANT).getName();
            case "minecraft:stone_slab":
                return blockState.getValue(BlockStoneSlab.VARIANT).getName();
            case "minecraft:double_plant":
                return blockState.getValue(BlockDoublePlant.VARIANT).getName();
            case "minecraft:log":
                return blockState.getValue(BlockOldLog.VARIANT).getName();
            case "minecraft:log2":
                return blockState.getValue(BlockNewLog.VARIANT).getName();
            case "minecraft:red_flower":
            case "minecraft:yellow_flower":
                return blockState.getValue(((BlockFlower) blockState.getBlock()).getTypeProperty()).getName();
            //case "minecraft:piston":
            //    return ((BlockPistonBase)block.getBlock()).isStickyBlock(block) ? "sticky" : "";
            //return block.getValue(BlockPistonExtension.EnumPistonType).toString();
            //    break;
            case "minecraft:cobblestone_wall":
                return blockState.getValue(BlockWall.VARIANT).getName();
            case "minecraft:prismarine":
                return blockState.getValue(BlockPrismarine.VARIANT).getName();
            case "minecraft:quartz_block":
                return blockState.getValue(BlockQuartz.VARIANT).getName().replace("lines_x", "pillar").replace("lines_y", "pillar").replace("lines_z", "pillar");
            case "minecraft:sand":
                return blockState.getValue(BlockSand.VARIANT).getName();
            case "minecraft:sandstone":
                return blockState.getValue(BlockSandStone.TYPE).getName();
            case "minecraft:red_sandstone":
                return blockState.getValue(BlockRedSandstone.TYPE).getName();
            case "minecraft:sapling":
                return blockState.getValue(BlockSapling.TYPE).getName();
            case "minecraft:double_stone_slab":
                return blockState.getValue(BlockStoneSlab.VARIANT).getName();
            case "minecraft:double_stone_slab2":
                return blockState.getValue(BlockStoneSlabNew.VARIANT).getName();
            case "minecraft:stone_slab2":
                return blockState.getValue(BlockStoneSlabNew.VARIANT).getName();
            case "minecraft:double_wooden_slab":
                return blockState.getValue(BlockDoubleWoodSlab.VARIANT).getName();
            case "minecraft:wooden_slab":
                return blockState.getValue(BlockWoodSlab.VARIANT).getName();
            case "minecraft:stonebrick":
                return blockState.getValue(BlockStoneBrick.VARIANT).getName();
            case "minecraft:planks":
                return blockState.getValue(BlockPlanks.VARIANT).getName();
        }
        return "";
    }
    
    private static IBlockState getBlockState(String id) {
        String Id[] = id.split("/");
        return Block.getStateById(Integer.parseInt(Id[1])*4096 + Integer.parseInt(Id[0]));
    }
}

package com.farmsoft.youskyblock.commands;


import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.common.util.BlockSnapshot;

import java.util.HashMap;
import java.util.Map;

public class ScoreCommand {




    public static void execute(MinecraftServer server, EntityPlayerMP player, String[] args) {

        BlockPos position = player.getPosition();
        Map<String,Integer> chunkBlocks = new HashMap<>();

        for (int cx=-6; cx<6; cx++) {
            for (int cz=-6; cz<6; cz++) {
                Chunk chunk = server.getWorld(0).getChunkFromChunkCoords(cx, cz);

                for (int x = 0; x < 16; x++) {

                    for (int z = 0; z < 16; z++) {

                        for (int y = 0; y < 255; y++) {

                            //chunkBlocks.getValue("BlockID/Var")=chunkBlocks.getValue("BlockID/Var")+1

                            IBlockState block = chunk.getBlockState(x + chunk.x * 16, y, z + chunk.z * 16);
                            String blockFullName = block.getBlock().getRegistryName().toString();
                            String blockVariant = "";
                            switch (blockFullName) {
                                case "minecraft:air" : break;
                                case "minecraft:dirt":
                                    blockVariant = block.getValue(BlockDirt.VARIANT).getName();
                                    break;
                                case "minecraft:stone":
                                    blockVariant = block.getValue(BlockStone.VARIANT).getName();
                                    break;
                                case "minecraft:leaves":
                                    blockVariant = block.getValue(BlockOldLeaf.VARIANT).getName();
                                    break;
                                case "minecraft:leaves2":
                                    blockVariant = block.getValue(BlockNewLeaf.VARIANT).getName();
                                    break;
                                case "minecraft:stone_slab":
                                    blockVariant = block.getValue(BlockStoneSlab.VARIANT).getName();
                                    break;
                                case "minecraft:double_plant":
                                    blockVariant = block.getValue(BlockDoublePlant.VARIANT).getName();
                                    break;
                                case "minecraft:log":
                                    blockVariant = block.getValue(BlockOldLog.VARIANT).getName();
                                    break;
                                case "minecraft:log2":
                                    blockVariant = block.getValue(BlockNewLog.VARIANT).getName();
                                    break;
                                case "minecraft:red_flower":
                                case "minecraft:yellow_flower":
                                    blockVariant = block.getValue(((BlockFlower) block.getBlock()).getTypeProperty()).getName();
                                    break;
                                //case "minecraft:piston":
                                //    blockVariant = ((BlockPistonBase)block.getBlock()).isStickyBlock(block) ? "sticky" : "";
                                //blockVariant = block.getValue(BlockPistonExtension.EnumPistonType).toString();
                                //    break;
                                case "minecraft:cobblestone_wall":
                                    blockVariant = block.getValue(BlockWall.VARIANT).getName();
                                    break;
                                case "minecraft:prismarine":
                                    blockVariant = block.getValue(BlockPrismarine.VARIANT).getName();
                                    break;
                                case "minecraft:quartz_block":
                                    blockVariant = block.getValue(BlockQuartz.VARIANT).getName().replace("lines_x", "pillar").replace("lines_y", "pillar").replace("lines_z", "pillar");
                                    break;
                                case "minecraft:sand":
                                    blockVariant = block.getValue(BlockSand.VARIANT).getName();
                                    break;
                                case "minecraft:sandstone":
                                    blockVariant = block.getValue(BlockSandStone.TYPE).getName();
                                    break;
                                case "minecraft:red_sandstone":
                                    blockVariant = block.getValue(BlockRedSandstone.TYPE).getName();
                                    break;
                                case "minecraft:sapling":
                                    blockVariant = block.getValue(BlockSapling.TYPE).getName();
                                    break;
                                case "minecraft:double_stone_slab":
                                    blockVariant = block.getValue(BlockStoneSlab.VARIANT).getName();
                                    break;
                                case "minecraft:double_stone_slab2":
                                    blockVariant = block.getValue(BlockStoneSlabNew.VARIANT).getName();
                                    break;
                                case "minecraft:stone_slab2":
                                    blockVariant = block.getValue(BlockStoneSlabNew.VARIANT).getName();
                                    break;
                                case "minecraft:double_wooden_slab":
                                    blockVariant = block.getValue(BlockDoubleWoodSlab.VARIANT).getName();
                                    break;
                                case "minecraft:wooden_slab":
                                    blockVariant = block.getValue(BlockWoodSlab.VARIANT).getName();
                                    break;
                                case "minecraft:stonebrick":
                                    blockVariant = block.getValue(BlockStoneBrick.VARIANT).getName();
                                    break;
                                case "minecraft:planks":
                                    blockVariant = block.getValue(BlockPlanks.VARIANT).getName();
                                    break;

                            }
                            blockFullName = blockFullName + (!blockVariant.equals("") ? "/" + blockVariant : "");

                            ItemStack itemStack = new ItemStack(block.getBlock());
                            //itemStack.getItem().getCreatorModId(itemStack);
                            //StatCollector.translateToLocal(block.getUnlocalizedName())
                            //TextComponentTranslation tct = new TextComponentTranslation("minecraft:stonebrick");
                            String blockId = Block.getIdFromBlock(block.getBlock()) + "/" + (Block.getStateId(block) >> 12);
                            //new TextComponentTranslation(block.getBlock().getUnlocalizedName() + (blockVariant.equals("") || blockVariant.equals(block.getBlock().getLocalizedName().toLowerCase()) ? ""  : "." + blockVariant) + ".name").getUnformattedText();

                            chunkBlocks.put(blockFullName, 1 + chunkBlocks.getOrDefault(blockFullName, 0));
                        }
                    }
                }

            }
        }





        player.sendMessage(new TextComponentString("retrieved tile entities"));

    }



}

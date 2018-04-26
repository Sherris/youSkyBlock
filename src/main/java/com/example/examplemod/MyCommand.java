package com.example.examplemod;

import com.sun.media.jfxmedia.logging.Logger;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class MyCommand extends CommandBase {

    @Override
    public String getName() {
        return "hi";
    }

    @Override
    public String getUsage(ICommandSender sender) {

        sender.sendMessage((new TextComponentString("Need usage info for /hi?")));
        return "To get more info: /mycommand help (?)";
    }

 //   @Override
 //   public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
 //       return true;
 //   }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        World world = sender.getEntityWorld();
        if (!world.isRemote && args.length>=1) {
            EntityPlayerMP player = getCommandSenderAsPlayer(sender);
            if (args[0].equals("love")) {
                sender.sendMessage((new TextComponentString("I love you too! Here's a cookie!")));
                ItemStack itemStack = new ItemStack(Items.COOKIE);
                player.inventory.addItemStackToInventory(itemStack);
            } else if (args[0].equals("hate")) {
                sender.sendMessage((new TextComponentString("That's not very nice!")));
            } else if (args[0].equals("poop")) {
                sender.sendMessage((new TextComponentString("Ha Ha! You said poop!")));
            } else if (args[0].equals("doggy")) {
                sender.sendMessage((new TextComponentString("Ha Ha! That doggy looks funny!  OK.")));
            }
        }

    }
}

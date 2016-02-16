package info.civiliancraft.ChromaSound.Commands.SubCommands;

import info.civiliancraft.ChromaSound.Commands.BaseCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Help extends BaseCommand {


	public Help(){
		registerCommand("help", this);
		registerHelp("/chsound help", "The help command for ChromaSound.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String currentAlias, String[] args){
		
		//sender.sendMessage("To be added later. Will be a help command.");//TODO Write the help.
		
		for (String s : help_desc.keySet()){
			String[] desc = help_desc.get(s);
			for (String s2 : desc){
				sender.sendMessage(s + "  -  " + s2);
			}
		}
		
		return true;
	}
	
}

package info.civiliancraft.ChromaSound.Commands.SubCommands;

import info.civiliancraft.ChromaSound.Commands.BaseCommand;
import info.civiliancraft.ChromaSound.Managers.SoundManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stop extends BaseCommand {

	public Stop(){
		registerCommand("stop", this);
		registerHelp("/chsound stop" , "Stop all current audio from playing.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String currentAlias, String[] args){
		
		SoundManager.playToPlayer((Player) sender, "stop");
		
		return true;
	}
}

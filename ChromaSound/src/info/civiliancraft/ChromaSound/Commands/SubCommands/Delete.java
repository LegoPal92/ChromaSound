package info.civiliancraft.ChromaSound.Commands.SubCommands;

import info.civiliancraft.ChromaSound.Main;
import info.civiliancraft.ChromaSound.Commands.BaseCommand;
import info.civiliancraft.ChromaSound.Utils.Emitter;
import info.civiliancraft.ChromaSound.Utils.EmitterYML;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Delete extends BaseCommand {

	public Delete(){
		registerCommand("delete", this);
		registerHelp("/chsound delete <NAME>", "Remove the emitter with the specified name");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String currentAlias, String[] args){
		if (args.length != 2){
			
			sender.sendMessage(Main.tag_E + "Incorrect syntax. Propper usage is: /chsound delete <NAME>");
			return true;
		}
		
		String eName = args[1];
		
		Emitter e = Emitter.getEmitterByName(eName);
		EmitterYML.removeEmitter(e);
		Emitter.removeEmitter(e);
		
		sender.sendMessage("Emitter deleted.");
		
		return true;
	}
	
}

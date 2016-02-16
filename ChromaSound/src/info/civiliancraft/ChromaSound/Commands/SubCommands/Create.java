package info.civiliancraft.ChromaSound.Commands.SubCommands;

import info.civiliancraft.ChromaSound.Main;
import info.civiliancraft.ChromaSound.Commands.BaseCommand;
import info.civiliancraft.ChromaSound.Utils.Emitter;
import info.civiliancraft.ChromaSound.Utils.EmitterType;
import info.civiliancraft.ChromaSound.Utils.EmitterYML;
import info.civiliancraft.ChromaSound.Utils.Utils;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Create extends BaseCommand {

	public Create(){
		registerCommand("create", this);
		registerHelp("/chsound create", "The command to place an emitter.", "Proper usage is: /chsound create <NAME> [FILE_NAME] {LOOP_BOOLEAN} (RADIUS) 'TYPE'");
		
	}
	
	/*
	 * /chsound create <NAME> [FILE_NAME] {LOOP_BOOLEAN} (RADIUS)
	 */
	
	public boolean onCommand(CommandSender sender, Command cmd, String curretnAlias, String[] args){
		
		if (!(sender instanceof Player)){
			sender.sendMessage(Main.tag_E + "You must be a player to create sounds.");
			return true;
		}
		
		if (args.length != 6){
			sender.sendMessage(Main.tag_E + "Incorrect syntax. Proper usage is: /chsound create <NAME> [FILE_NAME] {LOOP_BOOLEAN} (RADIUS) 'TYPE'");
			return true;
		} 
		for (String s : args){
			sender.sendMessage(s);
		}
		String name = args[1];
		String fName = args[2];
		boolean b = Boolean.getBoolean(args[3]);
		int radius = Integer.valueOf(args[4]);
		EmitterType et;
		if (Utils.isNumeric(args[5])){
			et = EmitterType.getEmitterTypeFromId(Integer.valueOf(args[5]));
		} else {
			et = EmitterType.getEmitterTypeFromName(args[5]);
		}
		Location l = ((Player) sender).getLocation();
		Emitter emi = new Emitter(name, fName, b, radius, l, et);
		EmitterYML.saveEmitter(emi);
		
		sender.sendMessage("Emitter created.");
		
		return true;
	}
	
}

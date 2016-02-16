package info.civiliancraft.ChromaSound.Commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BaseCommand implements CommandExecutor{
	
	public static HashMap<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();
	public static HashMap<String, String[]> help_desc = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String currentAlias,
			String[] args) {
		
		if (args.length == 0){
			commands.get("help".toLowerCase()).onCommand(sender, cmd, currentAlias, args);
		} else {
			CommandExecutor ex = commands.get(args[0].toLowerCase());
			if (ex != null){
				ex.onCommand(sender, cmd, currentAlias, args);
			} else {
				sender.sendMessage("That command does not exist");
			}
		}		
		
		return true;
	}
	
	public void registerCommand(String cmd, CommandExecutor cmdExec){
		commands.put(cmd.toLowerCase(), cmdExec);
	}
	
	public void registerHelp(String cmd, String... desc){
		help_desc.put(cmd, desc);
	}
}

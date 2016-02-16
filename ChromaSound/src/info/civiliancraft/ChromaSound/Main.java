package info.civiliancraft.ChromaSound;

import info.civiliancraft.ChromaSound.Commands.BaseCommand;
import info.civiliancraft.ChromaSound.Commands.SubCommands.Create;
import info.civiliancraft.ChromaSound.Commands.SubCommands.Delete;
import info.civiliancraft.ChromaSound.Commands.SubCommands.Help;
import info.civiliancraft.ChromaSound.Commands.SubCommands.Stop;
import info.civiliancraft.ChromaSound.Listeners.PlayerListener;
import info.civiliancraft.ChromaSound.Utils.EmitterYML;
import info.civiliancraft.ChromaSound.WebServer.WebServer;
import info.civiliancraft.ChromaSound.WebServer.WebsocketServer;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static String tag_E = ChatColor.GOLD + "[CHSound] " + ChatColor.RED;
	
	public void onEnable(){
		try {
			WebServer.runServer();
			WebsocketServer.runServer();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getCommand("chsound").setExecutor(new BaseCommand());
		new Create();
		new Stop();
		new Help();
		new Delete();
		new EmitterYML(this);


	}
}

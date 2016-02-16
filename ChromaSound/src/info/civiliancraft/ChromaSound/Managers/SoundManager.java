package info.civiliancraft.ChromaSound.Managers;

import info.civiliancraft.ChromaSound.WebServer.WebsocketServer;
import info.civiliancraft.ChromaSound.WebServer.WebsocketSessionManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class SoundManager {
	public static void playToPlayer(Player p, String data, BukkitTask bt) {
	    if (WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()) != null) {
	        WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), data);
	        bt.cancel();
	    }
	}
	public static void playToPlayer(Player p, String data) {
	    if (WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()) != null) {
	        WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), data);
	    }
	}
	 
	public static void playToAll(String data) {
	    for (Player p : Bukkit.getOnlinePlayers()) {
	        if (WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()) != null) {
	            WebsocketServer.s.sendData(WebsocketSessionManager.getSessionManager().getSessionByName(p.getName()), data);
	        }
	    }
	}
}

package info.civiliancraft.ChromaSound.Listeners;

import info.civiliancraft.ChromaSound.Main;
import info.civiliancraft.ChromaSound.Managers.SoundManager;
import info.civiliancraft.ChromaSound.Utils.ActionBar;
import info.civiliancraft.ChromaSound.Utils.LocationCheckRunnable;
import info.civiliancraft.ChromaSound.WebServer.WebsocketSessionManager;

import java.util.HashMap;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

public class PlayerListener implements Listener {

	static HashMap<String, BukkitTask> playerRunnables = new HashMap<>();
	
	@EventHandler
	public void onPlayerFirstJoin(final PlayerJoinEvent event) {
		if (event.getPlayer().hasPlayedBefore()) {
			return;
		}
		Main.getPlugin(Main.class).getServer().getScheduler().runTaskTimerAsynchronously(Main.getPlugin(Main.class), new Runnable(){
			
			boolean b = false;
			
			public void run(){
				if (b){
					return;
				}
				if(WebsocketSessionManager.getSessionManager().getSessionByName(event.getPlayer().getName()) == null){
					return;
				} else {
					SoundManager.playToPlayer(event.getPlayer(), "Chspawnintro4");
					b = true;
				}
			}
		}, 1L, 20L);//470244  470244  470244  470244  470244  470244  470244  470244  470244  rmarqstt2@htc.com
		
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(final PlayerJoinEvent event){
		//((CraftPlayer) event.getPlayer()).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(new ChatComponentText("Be sure to enable ChromaSound!"), (byte)2));
		ActionBar.sendAction(event.getPlayer(), "Be sure to enable ChromaSound! Click the link in chat!");
		Main.getPlugin(Main.class).getServer().getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
			public void run(){
				ActionBar.sendAction(event.getPlayer(), "Be sure to enable ChromaSound! Click the link in chat!");
			}
		}, 10L);
		Main.getPlugin(Main.class).getServer().getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable(){
			public void run(){
				ActionBar.sendAction(event.getPlayer(), "Be sure to enable ChromaSound! Click the link in chat!");
			}
		}, 20L);
		TextComponent message = new TextComponent( "Click me to enable ChromaSound!" );
		message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "http://cs.chromahills.co.uk:8000/index.html?name="//cs.chromahills.co.uk
						+ event.getPlayer().getName() + "&sessionId=null") );
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("ChromaSound is an immersive experience").create()));
		event.getPlayer().spigot().sendMessage( message );
//		event.getPlayer().sendMessage(
//				"To enable ChromaSound, click the link: http://civ.bethkefamily.com:8000/index.html?name="
//						+ event.getPlayer().getName() + "&sessionId=null");
		playerRunnables.put(event.getPlayer().getName(), Main.getPlugin(Main.class).getServer().getScheduler().runTaskTimerAsynchronously(Main.getPlugin(Main.class), new LocationCheckRunnable(event.getPlayer()), 40L, 40L));
	}
	
	@EventHandler
	public void onPlayerDisconnect(PlayerQuitEvent event){
		BukkitTask bt = playerRunnables.get(event.getPlayer().getName());
		bt.cancel();
		playerRunnables.remove(event.getPlayer().getName());
		SoundManager.playToPlayer(event.getPlayer(), "close");
		
	}
}

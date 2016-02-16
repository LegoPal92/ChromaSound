package info.civiliancraft.ChromaSound.Utils;

import org.bukkit.entity.Player;

public class LocationCheckRunnable implements Runnable {
	
	private Player player;
	
	private long nextAvailSys;
	
	public LocationCheckRunnable(Player player){
		this.player = player;
		this.nextAvailSys = System.currentTimeMillis();
	}
	
	public void run(){
		if (System.currentTimeMillis() >= nextAvailSys)
		for (Emitter e : Emitter.emitters){
			if (!player.isOnline()){
				continue;
			}
			if (e.getLocationToEmitFrom() == null){
				System.out.println("Location is definitely null.");
			}
			if (e.getLocationToEmitFrom().getWorld() == null){
				System.out.println("World is somehow null, but the location itself it not.");
			}
			
			if (player.getWorld().getName() == null){
				System.out.println("You heard the error, The name of the player's world is null! Which, in all realism, should not, nor cannot happen.");
			}
			if (!player.getLocation().getWorld().getName().equalsIgnoreCase(e.getLocationToEmitFrom().getWorld().getName())){
				continue;
			}
			if (player.getLocation()
					.distanceSquared(e.getLocationToEmitFrom()) <= e.getRadius()
					* e.getRadius()) {
					e.play(player);
					this.nextAvailSys = System.currentTimeMillis() + (long)(e.getEmitterLengthInSeconds() * 1000);
				break;
			}

		}
	}

}

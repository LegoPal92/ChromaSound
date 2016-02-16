package info.civiliancraft.ChromaSound.Utils;

import info.civiliancraft.ChromaSound.Main;
import info.civiliancraft.ChromaSound.Managers.SoundManager;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Emitter {

	private String name, fName;
	private boolean loop;
	private int radius;
	private Location emitFrom;
	private EmitterMemoryYML eyml;
	private EmitterType et;
	private float length; //SECONDS
	
	public static ArrayList<Emitter> emitters = new ArrayList<Emitter>();
	
	public Emitter(String name, String fName, boolean loop, int radius, Location emit, EmitterType et){
		this.name = name;
		this.fName = fName;
		this.loop = loop;
		this.radius = radius;
		this.emitFrom = emit;
		eyml = new EmitterMemoryYML(Main.getPlugin(Main.class), this);
		this.et = et;
		Main.getPlugin(Main.class).getServer().getScheduler().runTaskAsynchronously(Main.getPlugin(Main.class), new LoadAudioRunnable(this));
		
	}
	
	public String getName(){
		return name;
	}
	
	public String getFileName(){
		return fName;
	}
	
	public boolean getDoLoop(){
		return loop;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public float getEmitterLengthInSeconds(){
		return length;
	}
	
	public EmitterType getEmitterType(){
		return et;
	}
	
	public Location getLocationToEmitFrom(){
		return emitFrom;
	}
	
	public EmitterMemoryYML getEmitterMemoryYML(){
		return eyml;
	}
	
	public Emitter setName(String n){
		name = n;
		return this;
	}
	
	public Emitter setFileName(String f){
		fName = f;
		return this;
	}
	
	public Emitter setLoop(boolean l){
		loop = l;
		return this;
	}
	
	public Emitter setRadius(int r){
		radius = r;
		return this;
	}
	
	public void setLengthInSeconds(float f) {
		this.length = f;
	}
	
	public Emitter setEmitFrom(Location l){
		emitFrom = l;
		return this;
	}
	
	public Emitter setEmitterType(EmitterType et){
		this.et = et;
		return this;
	}
	
	public boolean hasHeard(String name){
		return eyml.hasHeard(name);
	}
	
	public void hear(String name){
		eyml.hear(name);
	}
	
	public void play(Player player){
		if (getEmitterType() == EmitterType.ONCE){
			SoundManager.playToPlayer(player, getFileName() + " false");
		} else if (getEmitterType() == EmitterType.UNIQUE) {
			if (!getEmitterMemoryYML().hasHeard(player.getName())){
				SoundManager.playToPlayer(player, getFileName() + " false");
				hear(player.getName());
			}
		} else if (getEmitterType() == EmitterType.LOOP){
			SoundManager.playToPlayer(player, getFileName() + " true");
		}

	}
	
	public static Emitter getEmitterByName(String name){
		for (Emitter e : emitters){
			if (e.getName().equalsIgnoreCase(name)){
				return e;
			}
		}
		return null;
	}
	
	public static void removeEmitter(Emitter e){
		emitters.remove(e);
		e.getEmitterMemoryYML().delete();
	}


	
}

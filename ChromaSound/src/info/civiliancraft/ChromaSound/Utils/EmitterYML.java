package info.civiliancraft.ChromaSound.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class EmitterYML {

	private File dir;
	private static File yml;
	private static FileConfiguration econf;
	
	public EmitterYML(JavaPlugin plugin){
		dir = plugin.getDataFolder();
		dir.mkdir();
		yml = new File(dir, "Emitters.yml");
		if (!yml.exists()){
			try {
				yml.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			econf = YamlConfiguration.loadConfiguration(yml);
			Emitter.emitters = new ArrayList<Emitter>();
			System.err.println("Emitters: " + Emitter.emitters.size());
		} else {
			econf = YamlConfiguration.loadConfiguration(yml);
			loadEmitters();
			System.err.println("Emitters: " + Emitter.emitters.size());

		}
	}

	public static void loadEmitters() {
		Emitter.emitters = new ArrayList<Emitter>();
		if (!econf.contains("Sounds")){
			return;
		}
		for (String s : econf.getConfigurationSection("Sounds").getKeys(false)){

			System.out.println(s);
			String location = econf.getString("Sounds." + s + ".location");
			String fName = econf.getString("Sounds." + s + ".file");
			boolean loop = econf.getBoolean("Sounds." + s + ".loop");
			int radius = econf.getInt("Sounds." + s + ".radius");
			EmitterType et = EmitterType.getEmitterTypeFromId(econf.getInt("Sounds." + s + ".emittertype"));
			
			Location l = parseString(location);
			Emitter emi = new Emitter(s, fName, loop, radius, l, et);
			Emitter.emitters.add(emi);
		}
	}
	
	public static void removeEmitter(Emitter e){
		if (!econf.contains("Sounds." + e.getName())){
			return;
		}
		econf.set("Sounds." + e.getName(), null);
		try {
			econf.save(yml);
		} catch (IOException e1) {
			e1.printStackTrace();
		};
	}
	
	public static void saveEmitter(Emitter e){
		if (econf.contains("Sounds." + e.getName())){
			econf.set("Sounds." + e.getName() + ".location" , parseLocation(e.getLocationToEmitFrom()));
			econf.set("Sounds." + e.getName() + ".file" , e.getFileName());
			econf.set("Sounds." + e.getName() + ".loop" , e.getDoLoop());
			econf.set("Sounds." + e.getName() + ".radius" , e.getRadius());
			econf.set("Sounds." + e.getName() + ".emittertype", e.getEmitterType().getUniqueIndex());
		} else {
			econf.set("Sounds." + e.getName() + ".location" , parseLocation(e.getLocationToEmitFrom()));
			econf.set("Sounds." + e.getName() + ".file" , e.getFileName());
			econf.set("Sounds." + e.getName() + ".loop" , e.getDoLoop());
			econf.set("Sounds." + e.getName() + ".radius" , e.getRadius());
			econf.set("Sounds." + e.getName() + ".emittertype", e.getEmitterType().getUniqueIndex());
		}
		
		Emitter.emitters.add(e);
		e.getEmitterMemoryYML().saveAll();
		try {
			econf.save(yml);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	
	public static String parseLocation(Location l){
		return l.getBlockX() + ":" + l.getBlockY() + ":" + l.getBlockZ() + ":" + l.getWorld().getName();
	}
	
	public static Location parseString(String s2){
		String[] parts = s2.split(":");
		return new Location(Bukkit.getWorld(parts[3]), Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
	}
	
}

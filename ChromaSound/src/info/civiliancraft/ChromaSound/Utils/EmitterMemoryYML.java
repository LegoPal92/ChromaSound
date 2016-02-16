package info.civiliancraft.ChromaSound.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class EmitterMemoryYML {

	private File dir;
	private File yml;
	private FileConfiguration econf;
	private ArrayList<String> playersWhoHaveHeard = new ArrayList<String>();
	
	public EmitterMemoryYML(JavaPlugin plugin, Emitter emi){
		dir = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "emitters");
		dir.mkdir();
		yml = new File(dir, emi.getName() + ".yml");
		if (!yml.exists()){
			try {
				yml.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			econf = YamlConfiguration.loadConfiguration(yml);
			Emitter.emitters = new ArrayList<Emitter>();
		} else {
			econf = YamlConfiguration.loadConfiguration(yml);
			//loadEmitters();
			load();
		}
	}

	private void load(){
		for (String s : econf.getKeys(false)){
			playersWhoHaveHeard.add(s);
		}
	}
	
	public void saveAll(){
		for (String s : playersWhoHaveHeard){
			econf.set(s, true);
		}
		try {
			econf.save(yml);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void save(String name){
		econf.set(name, true);
		try {
			econf.save(yml);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasHeard(String playerName){
		return playersWhoHaveHeard.contains(playerName);
	}
	
	public void hear(String playerName){
		playersWhoHaveHeard.add(playerName);
		save(playerName);
	}
	
	public void delete(){
		yml.delete();
	}
}

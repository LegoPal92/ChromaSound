package info.civiliancraft.ChromaSound.Utils;

import info.civiliancraft.ChromaSound.Main;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class LoadAudioRunnable implements Runnable {

	private Emitter e;
	
	public LoadAudioRunnable(Emitter e){
		this.e = e; 
	}
	
	public void run(){
		File dir = new File(Main.getPlugin(Main.class).getDataFolder(), "httdocs" + File.separator + "sounds");
		File file = null;
		for (File f : dir.listFiles()){
			if (f.getName().toLowerCase().startsWith(e.getFileName().toLowerCase())){
				file = new File(dir.getAbsoluteFile(), f.getName());
				break;
			}
		}
		//System.out.println("FileName: " + fName);
		System.out.println(file.getAbsolutePath());

		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
			AudioFormat format = audioInputStream.getFormat();
		    long audioFileLength = file.length();
		    int frameSize = format.getFrameSize();
		    float frameRate = format.getFrameRate();
		    float durationInSeconds = (audioFileLength / (frameSize * frameRate));
		    e.setLengthInSeconds(durationInSeconds);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			if (ex instanceof UnsupportedAudioFileException){
				if (file.getName().endsWith(".ogg")){
					try {
						Ogg og = new Ogg(file);
						e.setLengthInSeconds(og.getSeconds());
					} catch (Exception e1) {
					}
				} else if (file.getName().endsWith(".mp3")){
					try{
						MP3 mp3 = new MP3(file);
						e.setLengthInSeconds(mp3.getLengthInSeconds());
					} catch (Exception e1){
						e1.printStackTrace();
					}
				}
			}
		}
		System.out.println("Audio length determined.");
	}
	
}

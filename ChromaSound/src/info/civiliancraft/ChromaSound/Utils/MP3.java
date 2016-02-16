package info.civiliancraft.ChromaSound.Utils;


import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

public class MP3 {

	private File audioFile;

	public MP3(File audio) {
		audioFile = audio;
	}

	public float getLengthInSeconds(){
		float sec = 0;
		try {
		  AudioFile audioFile = AudioFileIO.read(this.audioFile);
		  sec = audioFile.getAudioHeader().getTrackLength();

		} catch (Exception e) {
		  e.printStackTrace();

		}
		return sec;
	}

}



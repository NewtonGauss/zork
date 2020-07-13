package zork;
import java.io.*;

import javax.swing.JOptionPane;

import sun.audio.*;

public class Reproductor {

    private AudioStream audios;
    public Reproductor(String path) {
	InputStream music;
	try {
	    music = new FileInputStream(new File(path));
	    audios = new AudioStream(music);
	}
	catch(Exception e){
	    JOptionPane.showMessageDialog(null, "Error al reproducir musica");
	}
    }
    
    
    public Reproductor(AudioStream audio) {
	this.audios = audio;
    }
    
    public AudioStream getAudio() {
	return this.audios;
    }
    
    public void Reproducir() {
	AudioPlayer.player.start(audios);
    }
       
}

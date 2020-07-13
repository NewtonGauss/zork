package zork;
import java.io.*;

import javax.swing.JOptionPane;

import sun.audio.*;

public class Reproductor {

    public Reproductor(String path) {
	InputStream music;
	try {
	    music = new FileInputStream(new File(path));
	    AudioStream audios = new AudioStream(music);
	    AudioPlayer.player.start(audios);
	}
	catch(Exception e){
	    JOptionPane.showMessageDialog(null, "error");
	}
    }
    
}

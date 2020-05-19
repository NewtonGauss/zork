package zork;

public class Salida {
	
	private Room salida;
	private NPC obstacle;
	private boolean defeated;
	
	
	// PARA PROBAR TEST
	public Salida(Room room) {
		this.salida = room;
		this.defeated = true;	
	}
	
	public void addNPC(NPC newObstacle) {
		this.obstacle = newObstacle;
		this.defeated = false;
	}
	
	public boolean removeNPC(String nombre) {
	    boolean rta = false;
	    if (obstacle.getName().equals(nombre)) {
		defeated = rta = true;
		obstacle = null;
	    }
	    return rta;
	}
	
	public boolean isEnemyDefeated() {
		if(!defeated && obstacle.isDead())
			defeated = true;
		return defeated;
	}
	
	public Room getRoom() {
		return this.salida;
	}
	
	public String getNombre() {
		return this.salida.getNombre();
	}

}

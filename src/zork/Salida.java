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
	
	public boolean isEnemyDefeated() {
		if(obstacle.isDead())
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

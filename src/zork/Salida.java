package zork;

public class Salida {
	
	private Habitacion salida;
	private NPC obstaculo;
	private boolean derrotado;
	
	public Salida(Habitacion room) {
		this.salida = room;
		this.derrotado = true;
	}
	
	public void addNPC(NPC nuevoObstaculo) {
		this.obstaculo = nuevoObstaculo;
		getObstaculo().setEnemigo(true);
		this.derrotado = false;
	}
	
	public boolean sacarNPC(String nombre) {
	    boolean rta = false;
	    if (getObstaculo().getNombre().equals(nombre)) {
		derrotado = rta = true;
		obstaculo = null;
	    }
	    return rta;
	}
	
	public boolean isEnemigoDerrotado() {
		if(!derrotado && (getObstaculo().estaMuerto() || !getObstaculo().isEnemigo()))
			derrotado = true;
		return derrotado;
	}
	
	public Habitacion getHabitacion() {
		return this.salida;
	}
	
	public String getNombre() {
		return this.salida.getNombre();
	}

	public NPC getObstaculo() {
	    return obstaculo;
	}

}

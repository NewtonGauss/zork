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
		obstaculo.setEnemigo(true);
		this.derrotado = false;
	}
	
	public boolean sacarNPC(String nombre) {
	    boolean rta = false;
	    if (obstaculo != null && obstaculo.getNombre().equals(nombre)) {
		derrotado = rta = true;
		obstaculo = null;
	    }
	    return rta;
	}
	
	public boolean isEnemigoDerrotado() {
		if(!derrotado && (obstaculo.estaMuerto() || !obstaculo.isEnemigo()))
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

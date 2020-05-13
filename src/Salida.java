
public class Salida {
	
	Room salida;
	boolean defeated;
	
	
	// PARA PROBAR TEST
	public Salida(Room room) {
		this.salida = room;
	}
	
	
	public String getNombre() {
		return this.salida.getNombre();
	}
}

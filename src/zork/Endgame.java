package zork;

public abstract class Endgame {
    
	protected String descripcion;
	
	public abstract boolean esFinal(String comando);

	public String getDescripcion() {
		return this.descripcion;
	}
}

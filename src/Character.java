
public abstract class Character {
	
	private Inventario inventario;
	private String nombre;
	private float salud;
//	float saludMaxima
	private char genero;
	
	public abstract boolean addItem(Item nuevoItem);
	
	public abstract void removeItem(Item sacar);
	
	public void sumarSalud(float healthup) {
		if(isDead() == false) {
			this.salud += healthup;
//			if(this.salud > this.saludMaxima){
//				this.salud = saludMaxima;
//			}
		}
	}
	
	public void restarSalud(float healthdown) {
		if(this.salud - healthdown <= 0 && isDead() == false) {
			this.salud = 0;
		}else {
			this.salud -= healthdown;
		}
	}
	
	public boolean isDead() {
		return this.salud == 0 ? true : false;
	};
	
	public char getGender() {
		return this.genero;
	}
}

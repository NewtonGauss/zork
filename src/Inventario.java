
public class Inventario {
	
	float pesoMax;
	float pesoActual;
	
	
	public boolean addItem(Item nuevoItem) {
		boolean bool = false;
		if(nuevoItem.getPeso() + this.pesoActual <= this.pesoMax) {
			this.pesoActual += nuevoItem.getPeso();
			bool = true;
		}
		return bool;
	}
	
	public void removeItem(Item sacar) {
	}
}

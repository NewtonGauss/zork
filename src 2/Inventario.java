
public class Inventario {
	
	float pesoMax;
	float pesoActual;
	java.util.List<Item> items = new java.util.ArrayList<Item>();
	
	
	public boolean addItem(Item nuevoItem) {
		boolean bool = false;
		if(nuevoItem.getPeso() + this.pesoActual <= this.pesoMax) {
			this.pesoActual += nuevoItem.getPeso();
			items.add(nuevoItem);
			bool = true;
		}
		return bool;
	}
	
	public void removeItem(Item sacar) {
		items.remove(sacar);
	}
}

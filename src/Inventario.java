import java.util.Hashtable;

public class Inventario {
	
	private Double pesoMax=200.0;
	private Double pesoActual=0.0;
	Hashtable<String, Item> inventario = new Hashtable<>();
	
	
	public boolean addItem(Item nuevoItem) {
		boolean bool = false;
		if(nuevoItem.getPeso() + this.pesoActual <= this.pesoMax) {
			this.pesoActual += nuevoItem.getPeso();
			inventario.put(nuevoItem.getNombre(), nuevoItem);
			bool = true;
		}
		return bool;
	}
	
	
	public Item removeItem(Item sacar) {
		inventario.remove(sacar.getNombre());
		pesoActual -= sacar.getPeso();
		return sacar;
	}
	
	public Item getItem(String nombre) {
		return inventario.get(nombre);
	}
	
	
	/* ES DE  PRUEBA, SE PODRIA IMPLEMENTAR PARA QUE DEVUELVA LA HASHTABLE */
	public void getItems() {
		inventario.forEach((k,v)->{
			System.out.println(k.toString() + " " + v.getPeso());
		});
	}
	
	
	public Double getPesoActual() {
		return this.pesoActual;
	}
}

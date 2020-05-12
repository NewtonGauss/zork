import java.util.Hashtable;

public class Item {
	
	String nombre;
	Double peso;
	char gender; //Necesario?
	char number; //Necesario? Podriamos simplificar poniendo un solo item y luego si funciona mejorarlo para que sirva para varios.
	String[] acciones; 
	String[] afecta;
	Hashtable<String, Double> info = new Hashtable<String, Double>(){{put("Espada Larga", 25.3); put("Manzana", 2.0); put("Huevo Kinder", 66.6); put("Mierda",500.0);}};

	public Item(String nombre) {
		this.nombre = nombre;
		this.peso = info.get(nombre);
	}
	public Double getPeso() {
		return this.peso;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}

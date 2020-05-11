import java.util.Hashtable;

public class Item {
	
	private String nombre;
	private Double peso;
	private char gender;
	private char number;
	private String[] acciones; 
	private String[] afecta;
	Hashtable<String, Double> info = new Hashtable<String, Double>(){{put("Espada Larga", 25.3); put("Manzana", 2.0); put("Huevo Kinder", 66.6);}};

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

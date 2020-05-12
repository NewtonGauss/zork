import java.util.Hashtable;
import java.util.Set;

public class Room {
	private String nombre;
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	Hashtable<String, Room> salidas = new Hashtable<String, Room>();

	//Hashtable<String, Room> salidas = new Hashtable<String, Room>(){{put("Arriba",null);put("Abajo",null);put("izquierda",null);put("Derecha",null);put("Adelante",null);put("Atras",null);}};
	private String[] direcciones = { "Arriba", "Abajo", "Izquierda", "Derecha", "Adelante", "Atras" }; //si tienen otras ideas de como implementarlo, cambien lo que consideren necesadio, pero este codigo funciona. comentenlo

	public Room(String nombre, String desc) {
		this.nombre = nombre;
		this.descripcion=desc;
	}

	public Room(String nombre, String desc, Room[] Salidas) {
		this.nombre = nombre;
		this.descripcion=desc;
		for (int i = 0; i < Salidas.length; i++) {
			if(Salidas[i]!=null)
				salidas.put(direcciones[i], Salidas[i]);
		}
	}
	
//	public Room(String nombre, String desc, Hashtable<String, Room> tabla) {
//		this.nombre = nombre;
//		this.descripcion=desc;
//		Set<String> aux = tabla.keySet();
//		for(String key: aux )
//		{
//			salidas.put(key, tabla.get(key));
//		}
//	}

	public Room getSalidas(String Direction) {
		return salidas.get(Direction);
	}

}

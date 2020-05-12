
public class Sitio {
	
	String nombre;
	char gender;
	char number;
	Item[] items;
 	
	public Sitio(String nombre,Item[] item)
	{
		this.nombre=nombre;
		items=item;
	}
	
	public Item[] getContenido()
	{
		return this.items;
	}
}

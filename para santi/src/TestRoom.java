import static org.junit.jupiter.api.Assertions.*;

import java.util.Hashtable;

import org.junit.jupiter.api.Test;

class TestRoom {

	@Test
	void testPrimeraImplementacion() {
		Room prueba = new Room("prueba","donde sea");
		Room[] vecRoom = {prueba}; 
		Room r1 = new Room("Room 1","donde toque a santi",vecRoom);
		assertNotEquals(null,r1.getSalidas("Arriba"));
		System.out.println(r1.getSalidas("Arriba").getDescripcion());
		System.out.println(r1.getDescripcion());
	}
//	@Test
//	void testHashTable()
//	{
//		Room prueba = new Room("prueba","donde sea");
//		Hashtable<String, Room> pruebas = new Hashtable<String, Room>(){{put("Arriba",prueba);put("Abajo",null);put("izquierda",null);put("Derecha",null);put("Adelante",null);put("Atras",null);}};
//		Room r2=new Room("pruebaHash","habitacion del placer",pruebas);
//		assertNotEquals(null,r2.getSalidas("Arriba"));
//		System.out.println(r2.getSalidas("Arriba").getDescripcion());
//		System.out.println(r2.getDescripcion());
//	}
	
}

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

class RoomTest {

	String jsonRoom =  "{\n"
			+ " \"name\": \"muelle\" ,\n"
		    + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" ,\n"
		    + " \"description\": \"Estas en un muelle\" }" ;
	
	String jsonRoom2 =  "{\n"
			+ " \"name\": \"barrio\" ,\n"
		    + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" ,\n"
		    + " \"description\": \"Estas en un barrio\" }" ;
	
	String jsonSitio = "{\n"
			+ " \"name\": \"suelo\" ,\n"
		    + " \"gender\": \"male\" ,\n"
		    + " \"number\": \"singular\" }" ;
			
	 String jsonItem = " {\n" + 
			"      \"name\": \"barreta\",\n" + 
			"      \"gender\": \"female\",\n" + 
			"      \"number\": \"singular\",\n" + 
			"      \"actions\": [\n" + 
			"        \"usar\"\n" + 
			"      ],\n" + 
			"      \"effects_over\": [\n" + 
			"        \"npcs\",\n" + 
			"        \"self\",\n" + 
			"        \"item\"\n" + 
			"      ]\n" + 
			"    }";

			
	@Test // Pruebo la creacion de la habitacion
	void test1() {
		JsonParser jparser = new JsonParser();
		Room room = new Room(jparser.parse(jsonRoom));
		assertEquals("El muelle", room.toString());
	}
	
	@Test //Pruebo agregando Salida...
	void test2() {
		JsonParser jparser = new JsonParser();
		Room room = new Room(jparser.parse(jsonRoom));
		Room room2 = new Room(jparser.parse(jsonRoom2));
		Salida salida = new Salida(room2);
		room.addSalida(salida);
		assertEquals(salida.getClass(), room.getSalidas().get("barrio").getClass());
	}

}

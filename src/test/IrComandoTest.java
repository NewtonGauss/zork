package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.IrComando;
import zork.Jugador;
import zork.NPC;
import zork.Room;
import zork.Salida;

public class IrComandoTest {
	
	String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";	
	
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
	
	String jsonNPC =  "{\n" + 
		 	"      \"name\": \"pirata fantasma\",\n" + 
		 	"      \"gender\": \"male\",\n" + 
		 	"      \"number\": \"singular\",\n" + 
		 	"      \"description\": \"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar\",\n" + 
		 	"      \"talk\": \"¡No hay nada que me digas que me haga cambiar de opinión!\",\n" + 
		 	"			\"points\": \"100\",\n" + 
		 	"			\"enemy\": \"true\",\n" + 
		 	"			\"health\": \"100\",\n" + 
		 	"			\"inventory\": [],\n" + 
		 	"      \"triggers\": [\n" + 
		 	"        {\n" + 
		 	"          \"type\": \"item\",\n" + 
		 	"          \"thing\": \"rociador con cerveza de raiz\",\n" + 
		 	"          \"on_trigger\": \"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.\",\n" + 
		 	"          \"after_trigger\": \"remove\"\n" + 
		 	"        }\n" + 
		 	"      ]\n" + 
		 	"    }";
	
	
	@Test
	public void test01() {  //HAY SALIDA
		NPC npc = new NPC(JsonParser.parseString(jsonNPC));
		Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
		Room roomActual = new Room(JsonParser.parseString(jsonRoom));
		Room roomSalida = new Room(JsonParser.parseString(jsonRoom2));
		Salida salida = new Salida(roomSalida);
		IrComando ir = new IrComando();
		jugador.setHabitacionActual(roomActual);
		
		roomActual.addSalida(salida, "north");
		assertEquals(roomSalida.getDescription(), ir.ejecutar(jugador, "north"));		
	}
	
	@Test
	public void test02() {  //NO HAY SALIDA EN ESA DIRECCION
		NPC npc = new NPC(JsonParser.parseString(jsonNPC));
		Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
		Room roomActual = new Room(JsonParser.parseString(jsonRoom));
		Room roomSalida = new Room(JsonParser.parseString(jsonRoom2));
		//Salida salida = new Salida(roomSalida);
		//roomActual.addSalida(salida, "north");
		IrComando ir = new IrComando();
		jugador.setHabitacionActual(roomActual);
		assertEquals("Hacia el north no hay salida", ir.ejecutar(jugador, "north"));	
		
	}
	
	@Test
	public void test03() {  //NO HAY SALIDA EN ESA DIRECCION
		NPC npc = new NPC(JsonParser.parseString(jsonNPC));
		Jugador jugador = new Jugador(JsonParser.parseString(jsonPlayer));
		Room roomActual = new Room(JsonParser.parseString(jsonRoom));
		Room roomSalida = new Room(JsonParser.parseString(jsonRoom2));
		Salida salida = new Salida(roomSalida);
		salida.addNPC(npc);
		roomActual.addSalida(salida, "north");
		IrComando ir = new IrComando();
		jugador.setHabitacionActual(roomActual);
		assertEquals("Hay un NPC bloqueando la salida", ir.ejecutar(jugador, "north"));	
		
	}
	
}

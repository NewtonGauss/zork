package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Item;
import zork.Jugador;
import zork.Room;
import zork.Sitio;
import zork.comandos.TomarComando;

class TomarTest {
	String jsonPlayer = "{\n" + " \"character\": \"Guybrush Threepwood\"  }";

	String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
			+ " \"number\": \"singular\" ,\n" + " \"description\": \"Estas en un muelle\" }";

	String jsonSitio1 = "{\n" + " \"name\": \"suelo\" ,\n" + " \"gender\": \"male\" ,\n"
			+ " \"number\": \"singular\" }";
	String jsonSitio2 = "{\n" + " \"name\": \"pasarela\" ,\n" + " \"gender\": \"female\" ,\n"
			+ " \"number\": \"singular\" }";
	String jsonSitio3 = "{\n" + " \"name\": \"mesa\" ,\n" + " \"gender\": \"female\" ,\n"
			+ " \"number\": \"singular\" }";

	String masaJson = "{\n" + "      \"name\": \"barreta\",\n" + "      \"gender\": \"female\",\n"
			+ "      \"number\": \"singular\",\n" + "			\"points\": \"100\",\n"
			+ "			\"weight\": \"95\",\n" + "			\"type\": \"weapon\",\n" + "      \"actions\": [\n"
			+ "        \"usar\"\n" + "      ],\n" + "      \"effects_over\": [\n" + "        \"npcs\",\n"
			+ "        \"self\",\n" + "        \"item\"\n" + "      ]\n" + "    }",
			rociadorJson = "{\n" + "      \"name\": \"rociador con cerveza de raiz\",\n"
					+ "      \"gender\": \"male\",\n" + "      \"number\": \"singular\",\n"
					+ "			\"points\": \"100\",\n" + "			\"weight\": \"10\",\n"
					+ "			\"type\": \"vanilla\",\n" + "      \"actions\": [\n" + "        \"usar\"\n"
					+ "      ],\n" + "      \"effects_over\": [\n" + "        \"npcs\",\n" + "        \"self\",\n"
					+ "        \"item\"\n" + "      ]\n" + "    }",
			espejoJson = "{\n" + "      \"name\": \"espejo\",\n" + "      \"gender\": \"male\",\n"
					+ "      \"number\": \"singular\",\n" + "			\"points\": \"100\",\n"
					+ "			\"weight\": \"10\",\n" + "			\"type\": \"vanilla\",\n" + "      \"actions\": [\n"
					+ "        \"usar\"\n" + "      ],\n" + "      \"effects_over\": [\n" + "        \"npcs\",\n"
					+ "        \"self\",\n" + "        \"item\"\n" + "      ]\n" + "    }",
	canicasJson = "{\n" + "      \"name\": \"canicas\",\n" + "      \"gender\": \"female\",\n"
			+ "      \"number\": \"plural\",\n" + "			\"points\": \"100\",\n"
			+ "			\"weight\": \"10\",\n" + "			\"type\": \"vanilla\",\n" + "      \"actions\": [\n"
			+ "        \"usar\"\n" + "      ],\n" + "      \"effects_over\": [\n" + "        \"npcs\",\n"
			+ "        \"self\",\n" + "        \"item\"\n" + "      ]\n" + "    }";
	

	
	
	@Test
	void testTomarEspejo() {
		Jugador j = new Jugador(JsonParser.parseString(jsonPlayer));
		Room r = new Room(JsonParser.parseString(jsonRoom));
		Sitio s1 = new Sitio(JsonParser.parseString(jsonSitio1));
		Sitio s2 = new Sitio(JsonParser.parseString(jsonSitio2));
		Sitio s3 = new Sitio(JsonParser.parseString(jsonSitio3));
		Item barreta = new Item(JsonParser.parseString(masaJson)),
				rociador = new Item(JsonParser.parseString(rociadorJson)),
				espejo = new Item(JsonParser.parseString(espejoJson)),
						canicas = new Item(JsonParser.parseString(canicasJson));
		r.addSitio(s1);
		r.addSitio(s2);
		r.addSitio(s3);
		s1.addItem(barreta);
		s1.addItem(rociador); 
		s3.addItem(espejo);
		s3.addItem(canicas);
		j.setHabitacionActual(r);
		TomarComando c = new TomarComando();
		assertEquals("Tomaste el espejo.", c.ejecutar(j, "espejo"));
	}
	@Test
	void testNoHayItem() {
		Jugador j = new Jugador(JsonParser.parseString(jsonPlayer));
		Room r = new Room(JsonParser.parseString(jsonRoom));
		Sitio s1 = new Sitio(JsonParser.parseString(jsonSitio1));
		Sitio s2 = new Sitio(JsonParser.parseString(jsonSitio2));
		Sitio s3 = new Sitio(JsonParser.parseString(jsonSitio3));
		Item masa = new Item(JsonParser.parseString(masaJson)),
				rociador = new Item(JsonParser.parseString(rociadorJson)),
				espejo = new Item(JsonParser.parseString(espejoJson)),
						canicas = new Item(JsonParser.parseString(canicasJson));
		r.addSitio(s1);
		r.addSitio(s2);
		r.addSitio(s3);
		s1.addItem(masa);
		s1.addItem(rociador); 
		s3.addItem(espejo);
		s3.addItem(canicas);
		j.setHabitacionActual(r);
		TomarComando c = new TomarComando();
		assertEquals("No hay ningun neumatico por aqui", c.ejecutar(j, "neumatico"));
	}
	@Test
	void testNoHayEspacio() {
		Jugador j = new Jugador(JsonParser.parseString(jsonPlayer));
		Room r = new Room(JsonParser.parseString(jsonRoom));
		Sitio s1 = new Sitio(JsonParser.parseString(jsonSitio1));
		Sitio s2 = new Sitio(JsonParser.parseString(jsonSitio2));
		Sitio s3 = new Sitio(JsonParser.parseString(jsonSitio3));
		Item masa = new Item(JsonParser.parseString(masaJson)),
				rociador = new Item(JsonParser.parseString(rociadorJson)),
				espejo = new Item(JsonParser.parseString(espejoJson)),
		canicas = new Item(JsonParser.parseString(canicasJson));
		r.addSitio(s1);
		r.addSitio(s2);
		r.addSitio(s3);
		s1.addItem(masa);
		s1.addItem(rociador); 
		s3.addItem(espejo);
		s3.addItem(canicas);
		j.setHabitacionActual(r);
		TomarComando c = new TomarComando();
		j.addItem(masa);
		assertEquals("No tienes mas espacio en tu inventario!", c.ejecutar(j, "rociador con cerveza de raiz"));
	}
	
	@Test
	void testFemalePlural() {
		Jugador j = new Jugador(JsonParser.parseString(jsonPlayer));
		Room r = new Room(JsonParser.parseString(jsonRoom));
		Sitio s1 = new Sitio(JsonParser.parseString(jsonSitio1));
		Sitio s2 = new Sitio(JsonParser.parseString(jsonSitio2));
		Sitio s3 = new Sitio(JsonParser.parseString(jsonSitio3));
		Item masa = new Item(JsonParser.parseString(masaJson)),
				rociador = new Item(JsonParser.parseString(rociadorJson)),
				espejo = new Item(JsonParser.parseString(espejoJson)),
		canicas = new Item(JsonParser.parseString(canicasJson));
		r.addSitio(s1);
		r.addSitio(s2);
		r.addSitio(s3);
		s1.addItem(masa);
		s1.addItem(rociador); 
		s3.addItem(espejo);
		s3.addItem(canicas);
		j.setHabitacionActual(r);
		TomarComando c = new TomarComando();
		assertEquals("Tomaste las canicas.", c.ejecutar(j, "canicas"));
	}

}

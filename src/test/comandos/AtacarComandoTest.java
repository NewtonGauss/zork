package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

import zork.Habitacion;
import zork.Item;
import zork.Jugador;
import zork.NPC;
import zork.TipoItem;
import zork.comandos.AtacarConComando;
import zork.input.parametro.ItemInputParametro;

class AtacarComandoTest {

    String jsonPlayer = "{\n" + " \"character\": \"Santi\"  }";
    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n" + " \"description\": \"Estas en un muelle\" }";

    String jsonNPC = "{\n" + "\"name\": \"Maxi Hiena\" , \n" + "\"gender\": \"male\" , \n"
	    + "\"number\": \"singular\" , \n"
	    + "\"description\": \"Aqui no puedes pasar! El pirata fantasma no te dejara pasar\" , \n"
	    + "\"points\": \"100\" , \n" + "\"enemy\": \"true\" , \n" + "\"health\": \"100\" , \n"
	    + "\"talk\": \"No hay nada que me digas que me haga cambiar de opinion!\", \n" + "\"triggers\": [{"
	    + " \"type\": \"attack\" ,\n" + " \"thing\": \"espada\" ,\n"
	    + " \"on_trigger\": \"Uhhh me rompiste la gorra\" ,\n" + " \"after_trigger\": \"kill\" } ] }";
    private Item espada, espejo, canicas;
    
    @BeforeEach
    void inicializarItems() {
	ItemInputParametro constructor = new ItemInputParametro("espada");
	constructor.setTipo(TipoItem.ARMA);
	espada = new Item(constructor);
	
	constructor.setNombre("espejo");
	constructor.setTipo(TipoItem.VANILLA);
	espejo = new Item(constructor);
	
	constructor.setNombre("canicas");
	canicas = new Item(constructor);
    }

    @Test
    void testAtacarConComando() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	AtacarConComando acc = new AtacarConComando();

	j1.ponerItem(espada);
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("Maxi Hiena: Uhhh me rompiste la gorra.", acc.ejecutar(j1, npc.getNombre() + ":" + espada.getNombre()));
    }
    
    @Test
    void testNpcInvalido() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	AtacarConComando acc = new AtacarConComando();
	
	j1.ponerItem(espada);
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("pirata no se encuentra en el muelle.", acc.ejecutar(j1,"pirata:" + espada.getNombre()));
    }

    @Test
    void testAtacarSinItems() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	AtacarConComando acc = new AtacarConComando();

	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("No tiene armas para atacar. ¡Busque una!", acc.ejecutar(j1, npc.getNombre() + ":manos"));
    }

    @Test
    void testAtacarSinArmasConItems() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	AtacarConComando acc = new AtacarConComando();

	j1.ponerItem(espejo);
	j1.ponerItem(canicas);
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("No tiene armas para atacar. ¡Busque una!", acc.ejecutar(j1, npc.getNombre() + ":" + espejo.getNombre()));
    }
    
    @Test
    void testItemEquivocado() {
	Jugador j1 = new Jugador(JsonParser.parseString(jsonPlayer));
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	NPC npc = new NPC(JsonParser.parseString(jsonNPC));
	AtacarConComando acc = new AtacarConComando();

	j1.ponerItem(espejo);
	j1.ponerItem(canicas);
	j1.ponerItem(espada);
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("Utilice uno de los siguientes items para atacar: \nespada\n", acc.ejecutar(j1, npc.getNombre() + ":" + espejo.getNombre()));
    }

}

package test.comandos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;

import com.google.gson.JsonParser;

import zork.*;
import zork.comandos.AtacarConComando;
import zork.input.TriggerInput;
import zork.input.parametro.*;

class AtacarComandoTest {
    String jsonRoom = "{\n" + " \"name\": \"muelle\" ,\n" + " \"gender\": \"male\" ,\n"
	    + " \"number\": \"singular\" ,\n" + " \"description\": \"Estas en un muelle\" }";
    private Item espada, espejo, canicas;
    private NPC npc;
    
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

    @BeforeEach
    void initNPC() {
	NPCInputParametro input = new NPCInputParametro("pirata fantasma");
	input.setGender('m');
	input.setNumber('s');
	input.setDescripcion(
		"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar");
	input.setCharla("¡No hay nada que me digas que me haga cambiar de opinión!");
	input.setEnemigo(true);
	TriggerInputParametro trigger = new TriggerInputParametro(TipoTrigger.ATAQUE);
	trigger.setAfterTrigger("nothing");
	trigger.setMensaje(
		"eso no te sirve de nada");
	trigger.setObjetoActivador("espada");	
	input.setListaTriggers(new ArrayList<TriggerInput>(Arrays.asList(trigger)));
	npc = new NPC(input);
    }

    @Test
    void testAtacarConComando() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	AtacarConComando acc = new AtacarConComando();

	j1.ponerItem(espada);
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("pirata fantasma: eso no te sirve de nada.", acc.ejecutar(j1, "pirata fantasma:espada"));
    }
    
    @Test
    void testNpcInvalido() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	AtacarConComando acc = new AtacarConComando();
	
	j1.ponerItem(espada);
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("pirata no se encuentra en el muelle.", acc.ejecutar(j1,"pirata:" + espada.getNombre()));
    }

    @Test
    void testAtacarSinItems() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	AtacarConComando acc = new AtacarConComando();

	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("No tiene armas para atacar. ¡Busque una!", acc.ejecutar(j1, npc.getNombre() + ":manos"));
    }

    @Test
    void testAtacarSinArmasConItems() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	AtacarConComando acc = new AtacarConComando();

	j1.ponerItem(espejo);
	j1.ponerItem(canicas);
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("No tiene armas para atacar. ¡Busque una!", acc.ejecutar(j1, npc.getNombre() + ":" + espejo.getNombre()));
    }
    
    @Test
    void testItemEquivocado() {
	Jugador j1 = new Jugador("Guybrush Threepwood");
	Habitacion room = new Habitacion(JsonParser.parseString(jsonRoom));
	AtacarConComando acc = new AtacarConComando();

	j1.ponerItem(espejo);
	j1.ponerItem(canicas);
	j1.ponerItem(espada);
	room.addNPC(npc);
	j1.setHabitacionActual(room);
	assertEquals("Utilice uno de los siguientes items para atacar: \nespada\n", acc.ejecutar(j1, npc.getNombre() + ":" + espejo.getNombre()));
    }

}

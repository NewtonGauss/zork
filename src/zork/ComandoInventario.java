package zork;

public class ComandoInventario implements Comando{

	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		String listaInventario = "";
		while(jugador.getItems().hasNext()) {
			listaInventario = listaInventario +"\n" + 
					jugador.getItems().next().getNombre();
		}
		return listaInventario;
	}

}

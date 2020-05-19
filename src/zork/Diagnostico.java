package zork;

public class Diagnostico implements Comando{
	@Override
	public String ejecutar(Jugador jugador, String restoDelComando) {
		float salud=jugador.getSalud();
		String diagnostico;
		if(salud==100) {
			diagnostico="Tu estado de salud es perfecto ("+(int)salud+"), solo te podria matar una seria herida";
		}else if(salud<100 && salud>70) {
			diagnostico="Tu estado de salud es bueno ("+(int)salud+")";
		}else if(salud<70 && salud>30) {
			diagnostico="Tu estado de salud no es muy bueno ("+(int)salud+"), sera mejor que encuentres algo para curarte";
		}else if (salud < 30 && salud > 0){
			diagnostico="Tu estado de salud es bajo ("+(int)salud+"), hasta un rasguno podria ser peligroso!";
		} else {
		    diagnostico = "Estas muerto";
		}
		return diagnostico;
	}
}

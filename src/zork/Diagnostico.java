package zork;

public class Diagnostico implements Comando{
	@Override
	public String ejecutar(Jugador jugador) {
		float salud=jugador.getSalud();
		String diagnostico;
		if(salud==100) {
			diagnostico="Tu estado de salud es perfecto ("+salud+"), solo te podría matar una seria herida";
		}else if(salud<100 && salud>70) {
			diagnostico="Tu estado de salud es bueno ("+salud+")";
		}else if(salud<70 && salud>30) {
			diagnostico="Tu estado de salud no es muy bueno ("+salud+"), será mejor que encuentres algo para curarte";
		}else {
			diagnostico="Tu estado de salud es bajo ("+salud+"), ¡hasta un rasguño podría ser peligroso!";
		}
		return diagnostico;
	}
}

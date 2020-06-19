package zork;

public enum Direccion {
    NORTE, SUR, ESTE, OESTE, ARRIBA, ABAJO;

    public static Direccion stringToDireccion(String direcionString) {
	Direccion dir = null;
	switch (direcionString) {
	case "norte":
	case "north":
	    dir = NORTE;
	    break;
	case "sur":
	case "south":
	    dir = SUR;
	    break;
	case "este":
	case "east":
	    dir = ESTE;
	    break;
	case "oeste":
	case "west":
	    dir = OESTE;
	    break;
	case "arriba":
	case "up":
	    dir = ARRIBA;
	    break;
	case "abajo":
	case "down":
	    dir = ABAJO;
	    break;
	}
	return dir;
    }
}

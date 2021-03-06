package zork.endgame;

public enum ComandoCondicion {
    ATACAR, DAR, HABLAR, MIRAR, TOMAR, USAR, IR, AYUDA, DEFAULT;
    
    public static ComandoCondicion stringToComandoCondicion(String comando) {
	ComandoCondicion comandoCondicion;
	switch (comando) {
	case "attack":
	    comandoCondicion = ATACAR;
	    break;
	case "give":
	    comandoCondicion = DAR;
	    break;
	case "talk":
	    comandoCondicion = HABLAR;
	    break;
	case "look":
	    comandoCondicion = MIRAR;
	    break;
	case "take":
	    comandoCondicion = TOMAR;
	    break;
	case "use":
	default:
	    comandoCondicion = USAR;
	    break;
	}
	return comandoCondicion;
    }
}

package zork;

public enum AccionItem {
    USE, TAKE, DROP;
    
    public static AccionItem stringToAccionItem(String accion) {
	AccionItem accionItem;
	switch (accion) {
	case "take":
	    accionItem = TAKE;
	    break;
	case "drop":
	    accionItem = DROP;
	    break;
	case "use":
	default:
	    accionItem = USE;
	    break;
	}
	return accionItem;
    }
}

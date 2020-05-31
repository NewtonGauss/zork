package zork;

public enum TipoItem {
    VENENO, VANILLA, POCION, ARMA;

    public static TipoItem stringToTipo(String tipo) {
	TipoItem tipoItem;
	switch (tipo) {
	case "potion":
	    tipoItem = TipoItem.POCION;
	    break;
	case "poison":
	    tipoItem = TipoItem.VENENO;
	    break;
	case "weapon":
	    tipoItem = TipoItem.ARMA;
	    break;
	case "vanilla":
	default:
	    tipoItem = TipoItem.VANILLA;
	    break;
	}
	return tipoItem;
    }
}

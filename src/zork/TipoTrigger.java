package zork;

public enum TipoTrigger {
    ITEM, ATAQUE, CHARLA;
    
    public static TipoTrigger stringToTipoTrigger(String tipo) {
	TipoTrigger tipoTrigger;
	switch (tipo) {
	case "item":
	    tipoTrigger = ITEM;
	    break;
	case "attack":
	    tipoTrigger = ATAQUE;
	    break;
	case "talk":
	default:
	    tipoTrigger = CHARLA;
	    break;
	}
	return tipoTrigger;
    }
}

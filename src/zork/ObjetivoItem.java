package zork;

public enum ObjetivoItem {
    ITEM, NPCS, SELF;
    
    public static ObjetivoItem stringTObjetivoItem(String objetivo) {
	ObjetivoItem objetivoItem;
	switch (objetivo) {
	case "npcs":
	    objetivoItem = NPCS;
	    break;
	case "self":
	    objetivoItem = SELF;
	    break;
	case "item":
	default:
	    objetivoItem = ITEM;
	    break;
	}
	return objetivoItem;
    }
}

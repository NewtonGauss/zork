package zork.input;

import zork.TipoTrigger;

public interface TriggerInput {
    public TipoTrigger getTipo();
    public String getObjetoActivador();
    public String getMensaje();
    public String getAfterTrigger();
}

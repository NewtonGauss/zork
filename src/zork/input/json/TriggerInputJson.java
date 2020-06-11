package zork.input.json;

import com.google.gson.*;

import zork.TipoTrigger;
import zork.input.TriggerInput;

public class TriggerInputJson implements TriggerInput {
    private JsonObject jobject;
    
    public TriggerInputJson(String jsonString) {
	jobject = JsonParser.parseString(jsonString).getAsJsonObject();
    }

    @Override
    public TipoTrigger getTipo() {
	return TipoTrigger.stringToTipoTrigger(jobject.get("type").getAsString());
    }

    @Override
    public String getObjetoActivador() {
	return jobject.get("thing").getAsString();
    }

    @Override
    public String getMensaje() {
	return jobject.get("on_trigger").getAsString();
    }

    @Override
    public String getAfterTrigger() {
	return jobject.get("after_trigger").getAsString();
    }

}

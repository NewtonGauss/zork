import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.google.gson.JsonParser;

class ItemTest
{

	@Test
	void test() {
		String json = " {\n" + 
				"      \"name\": \"barreta\",\n" + 
				"      \"gender\": \"female\",\n" + 
				"      \"number\": \"singular\",\n" + 
				"      \"actions\": [\n" + 
				"        \"usar\"\n" + 
				"      ],\n" + 
				"      \"effects_over\": [\n" + 
				"        \"npcs\",\n" + 
				"        \"self\",\n" + 
				"        \"item\"\n" + 
				"      ]\n" + 
				"    }";
		Item i = new Item(JsonParser.parseString(json));
	}

}

package org.Rupeek.com.API;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonConverterToHashMap {

	public HashMap<String, String> jsonConverter(String value) {
		String json = value;
		JsonParser p = new JsonParser();
		HashMap<String, JsonElement> map = new HashMap<String, JsonElement>();
		getJsonNodes(p.parse(json), map);
		HashMap<String, String> hm = new HashMap<String, String>();
		for (Map.Entry<String, JsonElement> entry : map.entrySet()) {
			hm.put(entry.getKey(), entry.getValue().toString());
		}
		return hm;
	}

	public void getJsonNodes(JsonElement jsonElement, HashMap<String, JsonElement> map) {
		try {
			if (jsonElement.isJsonObject()) {
				Set<Map.Entry<String, JsonElement>> entrySet = jsonElement.getAsJsonObject().entrySet();
				for (Map.Entry<String, JsonElement> entry : entrySet) {
					String key1 = entry.getKey();
					if (entry.getValue().isJsonObject()) {
						getJsonNodes(entry.getValue(), map);
					} else {
						map.put(key1, entry.getValue());
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

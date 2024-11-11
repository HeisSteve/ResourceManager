package Persistence;

import Model.Collection;
import Model.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads JSON data from the file and parses it into a Collection object
    public Collection read() throws IOException, JSONException {
        StringBuilder content = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(content::append);
        }

        JSONObject jsonObject = new JSONObject(content.toString());
        return parseCollection(jsonObject);
    }

    // EFFECTS: parses a Collection from the JSON object
    private Collection parseCollection(JSONObject jsonObject) throws JSONException {
        String name = jsonObject.getString("name");
        Collection collection = new Collection(name);

        JSONArray itemsArray = jsonObject.getJSONArray("items");
        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject itemObject = itemsArray.getJSONObject(i);
            addItem(collection, itemObject);
        }

        return collection;
    }

    // EFFECTS: parses an Item from the JSON object and adds it to the Collection
    private void addItem(Collection collection, JSONObject jsonObject) throws JSONException {
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        int usage = jsonObject.getInt("usage");

        Item item = new Item(name, price, usage);
        collection.addItem(item);
    }
}

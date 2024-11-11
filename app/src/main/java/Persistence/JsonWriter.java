package Persistence;

import Model.Collection;
import Model.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class JsonWriter {
    private PrintWriter writer;
    private String dest;

    // EFFECTS: constructs a writer object to output data to destination JSON file
    public JsonWriter(String dest) {
        this.dest = dest;
    }

    // MODIFIES: this
    // EFFECTS: writes collection data to JSON file; throws IOException if writer is not opened
    public void write(Collection collection) throws JSONException, IOException {
        if (writer == null) {
            throw new IOException("Writer is not opened. Call open() before writing.");
        }

        JSONObject jsonObject = collectionToJson(collection);
        writer.print(jsonObject.toString(4));  // Indentation of 4 for readability
    }

    // MODIFIES: this
    // EFFECTS: opens writer with UTF-8 encoding; throws IOException if an error occurs while opening
    public void open() throws IOException {
        writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(dest), "UTF-8"));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        if (writer != null) {
            writer.close();
        }
    }

    // EFFECTS: converts a Collection object into a JSONObject
    private JSONObject collectionToJson(Collection collection) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", collection.getName());
        jsonObject.put("items", itemsToJson(collection));
        return jsonObject;
    }

    // EFFECTS: converts items in a Collection to a JSON array
    private JSONArray itemsToJson(Collection collection) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Item item : collection.getItems()) {
            JSONObject itemJson = new JSONObject();
            itemJson.put("name", item.getName());
            itemJson.put("price", item.getPrice());
            itemJson.put("usage", item.getUsage());  // Added missing 'usage' field
            jsonArray.put(itemJson);
        }
        return jsonArray;
    }
}

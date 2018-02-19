package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = "JsonUtils";

    /**
     * This method parses a JSON string that hold a serialized sandwich object.
     * If the parsing process is successful, then it returns a sandwich object,
     * otherwise it returns null.
     *
     * Remark: we choose to implement the List<Sting> interface as an ArrayList<>,
     * and thus for both the list of sandwich's other names and the list
     * of ingredients.
     *
     * @param json JSON string representation of a sandwich object
     *
     * @return A full fledged sandwich object upon successful completion, null otherwise
     *
     */

    public static Sandwich parseSandwichJson(String json) {

        // Sandwich object to be return
        Sandwich parsedSandwich = null;

        if (json == null || json.isEmpty()) {
            // Nothing to do in that case
            Log.d(TAG, "Empty JSON String");
            return null;
        } else {
            // Just to check what string is going to be parsed
            Log.d(TAG, "JSON String to be parsed: " + json);
        }

        try {
            // Fetch the whole JSON object
            JSONObject sandwichJSON = new JSONObject(json);

            // The name object holds the regular name and a list of other names
            JSONObject nameJSON = sandwichJSON.getJSONObject("name");
            String mainName = nameJSON.getString("mainName");
            Log.d(TAG, "Sandwich: " + mainName);

            // Get the list of alternate names
            JSONArray alsoKnownAsJSON = nameJSON.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJSON.length(); i++) {
                String name = alsoKnownAsJSON.getString(i);
                Log.d(TAG, "Other name #" + i + ": " + name);
                alsoKnownAs.add(name);
            }

            // Get the place from where the sandwich was invented
            String placeOfOrigin = sandwichJSON.getString("placeOfOrigin");
            Log.d(TAG, "Place of origin: " + placeOfOrigin);

            // Get sandwich description
            String description = sandwichJSON.getString("description");
            Log.d(TAG, "Description: " + description);

            // Get image's url
            String image = sandwichJSON.getString("image");
            Log.d(TAG, "Image: " + image);

            // Get the list of ingredients
            JSONArray ingredientsJSON = sandwichJSON.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsJSON.length(); i++) {
                String ingredient = ingredientsJSON.getString(i);
                Log.d(TAG, "Ingredient #" + i + ": " + ingredient);
                ingredients.add(ingredient);
            }

            // Parsing was successful so we can build a sandwich
            parsedSandwich =
                    new Sandwich(mainName,
                            alsoKnownAs,
                            placeOfOrigin,
                            description,
                            image,
                            ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Parsed JSON string: " + json);
            return null;
        }

        return parsedSandwich;
    }
}

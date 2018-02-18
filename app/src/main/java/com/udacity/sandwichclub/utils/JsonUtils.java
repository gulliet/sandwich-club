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

            // Get the list of alternate names
            JSONArray alsoKnownAsJSON = nameJSON.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<String>();
            for (int i = 0; i < alsoKnownAsJSON.length(); i++) {
                String name = alsoKnownAsJSON.getString(i);
                alsoKnownAs.add(name);
            }

            // Get the place from where the sandwich was invented
            String placeOfOrigin = sandwichJSON.getString("placeOfOrigin");
            Log.d(TAG, "Place of origin: " + placeOfOrigin);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Parsed JSON string: " + json);
            return null;
        }

        return parsedSandwich;
    }
}

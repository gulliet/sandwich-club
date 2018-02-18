package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) {

        Sandwich parsedSandwich = null;

        if (json == null || json.isEmpty()) {
            Log.d(TAG, "Empty JSON String");
            return null;
        } else {
            Log.d(TAG, "JSON String to be parsed: " + json);
        }

        try {
            JSONObject sandwichJSON = new JSONObject(json);
            JSONObject nameJSON = sandwichJSON.getJSONObject("name");
            String mainName = nameJSON.getString("mainName");

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return parsedSandwich;
    }
}

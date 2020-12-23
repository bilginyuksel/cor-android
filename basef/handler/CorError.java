package com.cor.android.framework.basef.handler;

import org.json.JSONException;
import org.json.JSONObject;

public class CorError {
    private final int code;
    private final String message;

    public CorError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public JSONObject toJson()  {
        JSONObject json = new JSONObject();
        try {
            return json.put("code", code).put("message", message);
        } catch (JSONException ignored) {
            return json;
        }
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

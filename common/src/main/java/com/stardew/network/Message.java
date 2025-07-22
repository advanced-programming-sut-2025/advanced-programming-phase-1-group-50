package com.stardew.network;

import com.stardew.utils.JSONUtils;

import java.util.HashMap;

public class Message {
    private MessageType type;
    private HashMap<String, Object> body;

    public Message() {}

    public Message(HashMap<String, Object> body, MessageType type) {
        this.body = body;
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    public <T> T getFromBody(String fieldName) {
        return (T) body.get(fieldName);
    }

    public <T> T getFromBody(String fieldName, Class<T> clazz) {
        Object rawValue = body.get(fieldName);
        if (rawValue == null) return null;
        String json = JSONUtils.getGson().toJson(rawValue);
        return JSONUtils.fromJson(json, clazz);
    }

    public int getIntFromBody(String fieldName) {
        return (int) ((double) ((Double) body.get(fieldName)));
    }

    public float getFloatFromBody(String fieldName) {
        return  (float) ((Float) body.get(fieldName));
    }

    @Override
    public String toString() {
        return "Message{" +
            "type=" + type +
            ", body=" + body +
            '}';
    }
}

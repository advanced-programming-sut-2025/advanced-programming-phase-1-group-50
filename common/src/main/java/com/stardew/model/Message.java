package com.stardew.model;

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

    public int getIntFromBody(String fieldName) {
        return (int) ((double) ((Double) body.get(fieldName)));
    }

    public float getFloatFromBody(String fieldName) {
        return  (float) ((Float) body.get(fieldName));
    }
}

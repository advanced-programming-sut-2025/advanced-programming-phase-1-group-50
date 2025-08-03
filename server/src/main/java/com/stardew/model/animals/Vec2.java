package com.stardew.model.animals;

public class Vec2 {
    public float x, y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 cpy() {
        return new Vec2(x, y);
    }

    public Vec2 sub(Vec2 other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vec2 nor() {
        float len = len();
        if (len != 0) {
            this.x /= len;
            this.y /= len;
        }
        return this;
    }

    public float len() {
        return (float)Math.sqrt(x * x + y * y);
    }

    public float dst(Vec2 other) {
        float dx = other.x - this.x;
        float dy = other.y - this.y;
        return (float)Math.sqrt(dx * dx + dy * dy);
    }

    public Vec2 scl(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vec2 add(Vec2 other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public void set(Vec2 other) {
        this.x = other.x;
        this.y = other.y;
    }
}

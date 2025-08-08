package com.stardew.model.mapInfo;

import java.util.Objects;

public class Pair <K , V>{
    private K first;
    private V second;

    public Pair(K key, V value) {
        this.first = key;
        this.second = value;
    }


    public void setPosition(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(p.first, first) && Objects.equals(p.second, second);
    }

    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }


    public static <A, B> Pair <A, B> create(A a, B b) {
        return new Pair<>(a, b);
    }
}

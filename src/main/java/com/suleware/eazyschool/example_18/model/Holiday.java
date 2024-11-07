package com.suleware.eazyschool.example_18.model;

public record Holiday(String day, String reason, Type type) {
    public enum Type {
        FESTIVAL, FEDERAL
    }
}

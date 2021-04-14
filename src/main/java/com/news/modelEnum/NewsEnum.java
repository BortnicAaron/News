package com.news.modelEnum;

public enum NewsEnum {

    VIDEO("Video"),
    IMAGE("Image");

    private String descripcion;

    NewsEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static NewsEnum find(String value) {
        for (NewsEnum v : values()) {
            if (v.toString().equalsIgnoreCase(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid Type: %s", value));
    }
}

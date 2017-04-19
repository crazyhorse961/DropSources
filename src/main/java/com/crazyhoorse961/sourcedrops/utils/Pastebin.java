package com.crazyhoorse961.sourcedrops.utils;

/**
 * Created by nini7 on 17.04.2017.
 */
public enum Pastebin {

    KEY("8964fbe0bffabe5a4fe7e9aaeec6e72c");

    private String key;

    Pastebin(String key) {
        this.key = key;
    }

    public String get() {
        return key;
    }
}

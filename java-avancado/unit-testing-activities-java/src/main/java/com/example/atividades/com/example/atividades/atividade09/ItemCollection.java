package com.example.atividades.atividade09;

import java.util.ArrayList;
import java.util.List;

public class ItemCollection {
    private List<Item> items;

    public ItemCollection() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        items.add(item);
    }

    public void removeItem(Item item) {
        if (!items.contains(item)) {
            throw new IllegalArgumentException("Item not found in the collection");
        }
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
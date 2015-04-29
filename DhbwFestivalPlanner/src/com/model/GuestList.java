package com.model;

import java.util.ArrayList;
import java.util.List;

public class GuestList {

    private List<Guest> items = new ArrayList<Guest>();

    public List<Guest> getItems() {
        return items;
    }

    public void setItems(List<Guest> items) {
        this.items = items;
    }
}

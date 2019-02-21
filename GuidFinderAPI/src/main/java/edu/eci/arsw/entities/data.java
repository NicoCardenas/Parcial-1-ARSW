package edu.eci.arsw.entities;

import java.util.UUID;

public class data {
    
    private String date;
    private UUID search;
    private int count;

    public data(String date, UUID search, int count) {
        this.date = date;
        this.search = search;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UUID getSearch() {
        return search;
    }

    public void setSearch(UUID search) {
        this.search = search;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    
}

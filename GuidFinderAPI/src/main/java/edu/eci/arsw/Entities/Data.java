package edu.eci.arsw.Entities;

public class Data {
    
    private String date;
    private String search;
    private int count;

    public Data(String date, String search, int count) {
        this.date = date;
        this.search = search;
        this.count = count;
    }

    public Data() {
    }        

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    
}

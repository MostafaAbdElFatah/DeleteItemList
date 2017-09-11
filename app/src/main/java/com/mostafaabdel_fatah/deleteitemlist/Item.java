package com.mostafaabdel_fatah.deleteitemlist;

/**
 * Created by Mostafa AbdEl_Fatah on 8/9/2017.
 */

public class Item {
    private  int id ;
    private  String text;

    public Item(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}

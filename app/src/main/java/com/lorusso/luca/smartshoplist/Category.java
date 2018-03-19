package com.lorusso.luca.smartshoplist;

import java.util.ArrayList;

/**
 * Created by Luca on 15/03/2018.
 */

public class Category {
    private int idCategory;
    private String name;
    private ArrayList<Product> p;

    public Category(){

    }
    public Category(int idCategory, String name, ArrayList<Product> p) {
        this.idCategory = idCategory;
        this.name = name;
        this.p = p;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getP() {
        return p;
    }

    public void setP(ArrayList<Product> p) {
        this.p = p;
    }
}

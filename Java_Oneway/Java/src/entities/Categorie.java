/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Houda
 */
public class Categorie {
     private int id_categorie;
    private String type ;

    public Categorie() {
    }

    public Categorie(int id_categorie, String type) {
        this.id_categorie = id_categorie;
        this.type = type;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id_categorie=" + id_categorie + ", type=" + type + '}';
    }

   
    
    
    
}

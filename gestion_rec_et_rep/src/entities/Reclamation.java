/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hp
 */
public class Reclamation {
    private 
            int id_rec;
            String sujet,text_rec;

    public Reclamation() {
    }

    public Reclamation(int id_rec, String sujet, String text_rec) {
        this.id_rec = id_rec;
        this.sujet = sujet;
        this.text_rec = text_rec;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getText_rec() {
        return text_rec;
    }

    public void setText_rec(String text_rec) {
        this.text_rec = text_rec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", sujet=" + sujet + ", text_rec=" + text_rec + '}';
    }

    
    
}

    
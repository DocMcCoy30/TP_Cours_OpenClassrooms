package fr.DocMcCoy30.web;

import fr.DocMcCoy30.beans.Livre;

import java.util.ArrayList;
import java.util.List;

public class LivreModele {
    private String motCle;
    private List<Livre>livres = new ArrayList<>();

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }
}

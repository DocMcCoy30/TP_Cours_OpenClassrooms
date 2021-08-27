package fr.DocMcCoy30.dao;

import fr.DocMcCoy30.beans.Livre;

import java.util.List;

public interface ILivreDao {

    public Livre enregistrer (Livre livre);
    public List<Livre> chercher (String motCle);
    public List<Livre> lister ();
    public Livre getLivre (Integer id);
    public Livre update (Livre livre);
    public void delete (Integer id);
}

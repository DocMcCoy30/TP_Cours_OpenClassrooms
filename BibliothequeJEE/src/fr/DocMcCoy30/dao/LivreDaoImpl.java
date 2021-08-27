package fr.DocMcCoy30.dao;

import fr.DocMcCoy30.beans.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreDaoImpl implements ILivreDao {
    /*private DaoFactory daoFactory;
    public LivreDaoImpl (DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }*/

    @Override
    public Livre enregistrer(Livre livre) {
        Connection connexion = DaoFactory.getConnexion();
        try {
            // enregistrement des attributs de l'objet Livre
            PreparedStatement ps = connexion.prepareStatement
                    ("INSERT INTO livre (titre, auteur, genre) VALUES (?,?,?);");
            ps.setString(1, livre.getTitre());
            ps.setString(2, livre.getAuteur());
            ps.setString(3, livre.getGenre());
            ps.executeUpdate();

            // récupération de l'id du Livre enregistré pour pouvoir le retourner avec l'objet
            PreparedStatement ps2 = connexion.prepareStatement
                    ("SELECT MAX(id) AS max_id FROM livre;");
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {  // if lorsqu'un seul enregistrement, while lorsque plusieurs
                livre.setId(rs.getInt("max_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livre;
    }

    @Override
    public List<Livre> chercher(String motCle) {
        List<Livre>livres = new ArrayList<>();

        Connection connexion = DaoFactory.getConnexion();
        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("SELECT * FROM livre WHERE titre LIKE ? OR auteur LIKE ? OR genre LIKE ?");
            ps.setString(1, motCle);
            ps.setString(2, motCle);
            ps.setString(3, motCle);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Livre livre = new Livre();
                livre.setId(rs.getInt("id"));
                livre.setTitre(rs.getString("titre"));
                livre.setAuteur(rs.getString("auteur"));
                livre.setGenre(rs.getString("genre"));
                livres.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    @Override
    public List<Livre> lister() {
        List<Livre>livres = new ArrayList<>();
        Connection connexion = DaoFactory.getConnexion();
        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("SELECT * FROM livre");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Livre livre = new Livre();
                livre.setId(rs.getInt("id"));
                livre.setTitre(rs.getString("titre"));
                livre.setAuteur(rs.getString("auteur"));
                livre.setGenre(rs.getString("genre"));
                livres.add((livre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livres;
    }

    @Override
    public Livre getLivre(Integer id) {
        Livre livre = null;
        Connection connexion = DaoFactory.getConnexion();
        try {
            PreparedStatement ps = connexion.prepareStatement("SELECT * FROM livre WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                livre = new Livre();
                livre.setId(rs.getInt("id"));
                livre.setTitre(rs.getString("titre"));
                livre.setAuteur(rs.getString("auteur"));
                livre.setGenre((rs.getString("genre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livre;
    }

    @Override
    public Livre update(Livre livre) {
        Connection connexion = DaoFactory.getConnexion();
        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("UPDATE livre SET titre=?, auteur=?, genre=? WHERE id=?");
            ps.setString(1, livre.getTitre());
            ps.setString(2, livre.getAuteur());
            ps.setString(3, livre.getGenre());
            ps.setInt(4, livre.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livre;
    }

    @Override
    public void delete(Integer id) {
        Connection connexion = DaoFactory.getConnexion();
        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("DELETE FROM livre WHERE id=?;");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

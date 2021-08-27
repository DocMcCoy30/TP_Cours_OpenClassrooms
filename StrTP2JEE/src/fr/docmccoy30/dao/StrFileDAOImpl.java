package fr.docmccoy30.dao;

import fr.docmccoy30.beans.Langue;
import fr.docmccoy30.beans.StrFile;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StrFileDAOImpl implements StrFileDAO {

    /**
     * Afficher la liste des fichiers sous-titres contenus dans la DB.
     * @return Tableau d'objet StrFile
     */
    @Override
    public List<StrFile> listFiles() throws DaoException {
        Connection connexion = SingletonConnexion.getInstance();
        List<StrFile> strFiles = new ArrayList<>();

        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("SELECT * FROM strfiles;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StrFile strFile = new StrFile();
                strFile.setFileId(rs.getInt("id"));
                strFile.setTitre(rs.getString("titre"));
                Integer langue_id = rs.getInt("langue_id");
                strFile.setLangue(getLanguageById(langue_id));
                strFile.setNomFichier(rs.getString("nom_fichier"));
                strFiles.add(strFile);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Communication avec la base de données impossible ! !");
        }
        return strFiles;
    }

    /**
     * Enregistrer un fichier soustitre (StrFile) dans la DB.
     * @param strFile Le fichier à entegistrer.
     * @return Le nouvel objet StrFile.
     */
    @Override
    public StrFile setFile(StrFile strFile) throws DaoException {
        Connection connexion = SingletonConnexion.getInstance();
        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("INSERT INTO strfiles (titre, langue_id, nom_fichier) VALUES (?, ?, ?);");
            ps.setString(1, strFile.getTitre());
            Langue langue = strFile.getLangue();
            Integer langue_id = langue.getLangue_id();
            ps.setInt(2, langue_id);
            ps.setString(3, strFile.getNomFichier());
            ps.executeUpdate();
            PreparedStatement ps2 = connexion.prepareStatement
                    ("SELECT MAX(id) AS Max_Id FROM strfiles");
            ResultSet rs = ps2.executeQuery();
            if (rs.next()) {
                strFile.setFileId(rs.getInt("Max_Id"));
            }
            ps.close();
            ps2.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Communication avec la base de données impossible ! !");
        }
        return strFile;
    }

    /**
     * Effacer un fichier soustitres de la base de données et du dossier cible.
     * @param fileId L'identifiant du fichier de sous-titres.
     */
    @Override
    public void deleteFile(Integer fileId, String targetFolder) throws DaoException, IOException {
        String path;
        Connection connexion = SingletonConnexion.getInstance();
        try {
            PreparedStatement ps1 = connexion.prepareStatement
                    ("SELECT nom_fichier FROM strfiles WHERE id = ?;");
            ps1.setInt(1, fileId);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                path = targetFolder + rs.getString("nom_fichier");
                File fichier = new File(path);
                if (fichier.delete())
                    System.out.println("Suppression du fichier réussie.");
                else System.out.println("La suppression du fichier a échoué.");
            }
            ps1.close();
            PreparedStatement ps3 = connexion.prepareStatement
                    ("DELETE FROM str WHERE strfiles_id = ?;");
            ps3.setInt(1, fileId);
            ps3.executeUpdate();
            ps3.close();
            PreparedStatement ps2 = connexion.prepareStatement
                    ("DELETE FROM strfiles WHERE id = ?;");
            ps2.setInt(1, fileId);
            ps2.executeUpdate();
            ps2.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Communication avec la base de données impossible ! !");
        }
    }

    /**
     * Modifier les attributs titre et langue d'un fichier soustitre dans la DB
     * @param strFile Le fichier sous-titres à modifier.
     */
    @Override
    public void updateFile(StrFile strFile, String targetFolder) throws DaoException, IOException {
        String nom_fichier = "";
        Connection connexion = SingletonConnexion.getInstance();
        try {
            PreparedStatement ps1 = connexion.prepareStatement
                    ("SELECT * FROM strfiles WHERE id=?;");
            ps1.setInt(1, strFile.getFileId());
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                nom_fichier = rs.getString("nom_fichier");
            }
            File fichier = new File(targetFolder + nom_fichier);
            File fichierDest = new File(targetFolder + strFile.getNomFichier());
            if (fichier.renameTo(fichierDest)) {
                System.out.println("Le nom de fichier est modifié.");
            } else {
                System.out.println("La modification du nom a échoué.");
            }
            PreparedStatement ps = connexion.prepareStatement
                    ("UPDATE strfiles SET titre=?, langue_id=?, nom_fichier=? WHERE id=?;");
            ps.setString(1, strFile.getTitre());
            ps.setInt(2, (strFile.getLangue()).getLangue_id());
            ps.setString(3, strFile.getNomFichier());
            ps.setInt(4, strFile.getFileId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Communication avec la base de données impossible ! !");
        }
    }

    /**
     * Récupérer un fichier soustitre de la DB avec son identifiant.
     * @param fileId L'identifiant du fichier sous-titres.
     * @return Le fichier soustitre visé.
     */
    @Override
    public StrFile getStrFileById(Integer fileId) throws DaoException {
        Connection connexion = SingletonConnexion.getInstance();
        StrFile strFile = null;
        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("SELECT * FROM strfiles WHERE id=?;");
            ps.setInt(1, fileId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                strFile = new StrFile();
                strFile.setFileId(rs.getInt("id"));
                strFile.setTitre(rs.getString("titre"));
                Integer langues_id = rs.getInt("langue_id");
                strFile.setLangue(getLanguageById(langues_id));
                strFile.setNomFichier(rs.getString("nom_fichier"));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Communication avec la base de données impossible ! !");
        }
        return strFile;
    }

    /**
     * Vérifier l'existence d'un fichier sous-titres dans la BDD à l'aide de son nom de fichier.
     * @param nomFichier Le nom de fichier.
     * @return True ou False selon que le fichier existe ou non dans la BDD.
     */
    @Override
    public boolean checkDataBase(String nomFichier) throws DaoException {
        boolean verif = false;
        Connection connexion = SingletonConnexion.getInstance();
        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("SELECT nom_fichier FROM strfiles;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("nom_fichier").equals(nomFichier)) {
                    verif = true;
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Communication avec la base de données impossible ! !");
        }
        return verif;
    }

    /**
     * Récupérer les langues renseignées dans la BDD pour affichage dans le menu de choix.
     * @return Tableau : la liste des langues.
     */
    @Override
    public List<Langue> getLanguageFromDataBase() throws DaoException {
        List<Langue> langues = new ArrayList<>();

        Connection connexion = SingletonConnexion.getInstance();
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement
                    ("SELECT * FROM langues;");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer langueId = rs.getInt("id");
                String langue = rs.getString("langue");
                langues.add(new Langue(langueId, langue));
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException ("Communication avec la base de données impossible ! !");
        } catch (NullPointerException e) {
            throw new DaoException ("Communication avec la base de données impossible ! !");
        }
        return langues;
    }

    /**
     * Récupérer une langue de la BDD avec son identifiant.
     * @param id L'identifiant de la langue visée.
     * @return Un objet langue.
     */
    @Override
    public Langue getLanguageById(Integer id) throws DaoException{
        Langue langue = new Langue();
        Connection connexion = SingletonConnexion.getInstance();
        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("SELECT * FROM langues WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                langue.setLangue_id(rs.getInt("id"));
                langue.setLangueStr(rs.getString("langue"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Communication avec la base de données impossible ! !");
        }
        return langue;
    }
}

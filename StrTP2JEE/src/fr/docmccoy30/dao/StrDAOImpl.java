package fr.docmccoy30.dao;

import fr.docmccoy30.beans.Str;
import fr.docmccoy30.beans.StrFile;
import fr.docmccoy30.utils.SubtitlesHandler;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StrDAOImpl implements StrDAO {

    /**
     * Ajouter les sous-titres originaux à la BDD.
     * @param sousTitres Les sous-titres concernés : tableau d'objet Str.
     * @param strFileId L'identifiant du fichier.
     */
    @Override
    public void addOriginalSubtitlesInDB(List<Str> sousTitres, Integer strFileId) throws DaoException {
        Connection connexion = SingletonConnexion.getInstance();
        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("INSERT INTO str (index, timestamp, texte1original, texte2original, strfiles_id) VALUES (?, ?, ?, ?, ?);");
            for (Str sousTitre : sousTitres) {
                ps.setInt(1, sousTitre.getIndex());
                ps.setString(2, sousTitre.getTimeStamp());
                ps.setString(3, sousTitre.getSousTitreOriginal()[0]);
                ps.setString(4, sousTitre.getSousTitreOriginal()[1]);
                ps.setInt(5, strFileId);
                ps.executeUpdate();
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Communication avec la base de données impossible ! !");
        }
    }

    /**
     * Insérer les sous-titres édités dans la BDD.
     * @param sousTitres Les sous-titres concernés : tableau d'objet Str.
     * @param strFileId L'identifiant du fichier.
     */
    @Override
    public void addEditedSubtitlesInDB(List<Str> sousTitres, Integer strFileId) throws DaoException {
        Connection connexion = SingletonConnexion.getInstance();
        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("UPDATE str SET texte1traduit=?, texte2traduit=? WHERE strfiles_id=? AND index=?;");
            for (Str sousTitre : sousTitres) {
                ps.setString(1, sousTitre.getSousTitreTraduit()[0]);
                ps.setString(2, sousTitre.getSousTitreTraduit()[1]);
                ps.setInt(3, strFileId);
                ps.setInt(4, sousTitre.getIndex());
                ps.executeUpdate();
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Communication avec la base de données impossible ! !");
        }
    }

    /**
     * Extraire les sous-titres d'un fichier .srt .
     * @param strFile L'objet StrFile concerné et préalablement enregistré.
     * @return Un tableau de sous-titres.
     */
    @Override
    public List<Str> getSubtitlesFromFile(StrFile strFile, String targetFolder) {
        String filePath = targetFolder + strFile.getNomFichier();
        List<Str> sousTitres = new SubtitlesHandler(filePath).getSousTitres();
        return sousTitres;
    }

    /**
     * Récupérer des sous-titres enregistrés dans la BD.
     * @param strFileId L'identifiant du fichier visé.
     * @return Un tableau de sous-titres.
     */
    @Override
    public List<Str> getSubtitlesFromDB(Integer strFileId) throws DaoException {
        Connection connexion = SingletonConnexion.getInstance();
        List<Str> sousTitres = new ArrayList<>();
        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("SELECT * FROM str WHERE strfiles_id = ?;");
            ps.setInt(1, strFileId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer strId = rs.getInt("id");
                Integer index = rs.getInt("index");
                String timeStamp = rs.getString("timestamp");
                String texte1Original = rs.getString("texte1original");
                String texte2Original =  rs.getString("texte2original");
                String texte1Traduit = rs.getString("texte1traduit");
                String texte2Traduit =  rs.getString("texte2traduit");
                String [] sousTitreOriginal = {texte1Original,texte2Original};
                String [] sousTitreTraduit = {texte1Traduit, texte2Traduit};
                sousTitres.add(new Str(index, timeStamp, sousTitreOriginal, sousTitreTraduit, strFileId));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Communication avec la base de données impossible ! !");
        }
        return sousTitres;
    }

    /**
     * Exporter les sous-titres édités vers un fichier .srt .
     * @param sousTitresCible Les sous-titres concernés : tableau d'objet Str.
     * @param nomFichier Le nom du fichier.
     */
    @Override
    public void exportEditedSubtitles(List<Str> sousTitresCible, String nomFichier, String targetFolder) throws DaoException {
        String path = targetFolder + nomFichier + ".srt";
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(path)));
            for (Str str : sousTitresCible) {
                bw.write(String.valueOf(str.getIndex()));
                bw.newLine();
                bw.write(str.getTimeStamp());
                bw.newLine();
                if ((str.getSousTitreTraduit())[0] != null) {
                    bw.write((str.getSousTitreTraduit())[0]);
                    bw.newLine();
                }
                if ((str.getSousTitreTraduit())[1] != null) {
                    bw.write((str.getSousTitreTraduit())[1]);
                    bw.newLine();
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new DaoException("Communication avec la base de données impossible ! !");
        }
        finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Vérifier l'existence de sous-titres dans la BDD à l'identifiant du fichier.
     * @param fileId L'identifiant du fichier.
     * @return True ou False selon que les sous-titres existent ou non dans la BDD.
     */
    @Override
    public boolean checkDataBase(Integer fileId) throws DaoException{
        boolean verif = false;
        Connection connexion = SingletonConnexion.getInstance();
        try {
            PreparedStatement ps = connexion.prepareStatement
                    ("SELECT strfiles_id FROM str");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (fileId == rs.getInt("strFiles_id")) {
                    verif = true;
                    break;
                }
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Communication avec la base de données impossible ! !");
        }
        return verif;
    }
}

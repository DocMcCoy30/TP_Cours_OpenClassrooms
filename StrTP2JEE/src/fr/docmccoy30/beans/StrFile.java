package fr.docmccoy30.beans;

import java.util.ArrayList;
import java.util.List;

public class StrFile {
    private Integer fileId = 0;
    private String titre = "";
    private Langue langue;
    private String nomFichier = "";
    private List<StrFile> strFiles = new ArrayList<>();

    public StrFile() {
    }

    public StrFile(String titre, Langue langue, String nomFichier) {
        this.titre = titre;
        this.langue = langue;
        this.nomFichier = nomFichier;
    }

    public StrFile(Integer fileId, String titre, Langue langue, String nomFichier) {
        this.fileId = fileId;
        this.titre = titre;
        this.langue = langue;
        this.nomFichier = nomFichier;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public List<StrFile> getStrFiles() {
        return strFiles;
    }

    public void setStrFiles(List<StrFile> strFiles) {
        this.strFiles = strFiles;
    }

    @Override
    public String toString() {
        return "StrFile{" +
                "fileId=" + fileId +
                ", titre='" + titre + '\'' +
                ", langue='" + langue + '\'' +
                ", nomFichier='" + nomFichier + '\'' +
                '}';
    }
}

package fr.docmccoy30.utils;

import fr.docmccoy30.beans.Str;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SubtitlesHandler {
    private List<Str> sousTitres = new ArrayList<>();

    /**
     * Récupère les données d'un fichier de sous-titre .srt et les ajoute dans un tableau d'objet Str.
     * @param filePath Le chemin vers le fichier visé.
     */
    public SubtitlesHandler(String filePath) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            boolean finDeBloc = false;
            boolean finDeFichier = false;
            Str sousTitre = new Str();
            String [] sousTitreOriginal = {"",""};

            String line = "";
            int cpt = 1;

            while (!finDeFichier) {
                line = br.readLine();
                if (line != null) {
                    switch (cpt) {
                        case 1 :
                            if (line =="") {
                                finDeFichier = true;
                            } else {
                                try {
                                    sousTitre.setIndex(Integer.valueOf(line));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case 2 :
                            sousTitre.setTimeStamp(line);
                            break;
                        case 3 :
                            sousTitreOriginal[0] = line;
                            break;
                        case 4 :
                            if (!line.equals("")) {
                                sousTitreOriginal[1] = line;;
                            } else {
                                finDeBloc = true;
                            }
                            break;
                        case 5 :
                            finDeBloc = true;
                            break;
                    }
                } else {
                    finDeBloc = true;
                    finDeFichier = true;
                }
                cpt++;

                if (finDeBloc) {
                    sousTitre.setSousTitreOriginal(sousTitreOriginal);
                    sousTitres.add(sousTitre);
                    finDeBloc = false;
                    cpt = 1;
                    sousTitre = new Str();
                    sousTitreOriginal = new String[2];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Str> getSousTitres() {
        return sousTitres;
    }

    public void setSousTitres(List<Str> sousTitres) {
        this.sousTitres = sousTitres;
    }
}

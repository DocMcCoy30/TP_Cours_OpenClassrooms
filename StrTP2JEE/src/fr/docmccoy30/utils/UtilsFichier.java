package fr.docmccoy30.utils;

import javax.servlet.http.Part;
import java.io.*;

public class UtilsFichier {

    private static final int TAILLE_TAMPON = 10240;

    /**
     * importe un fichier et l'enregistre dans le dossier cible.
     * @param part Le fichier visé.
     * @param nomFichier Le nom du fichier.
     * @throws IOException L'exception levée par un flux d'entrée/sortie.
     */
    public String ecrireFichier(Part part, String nomFichier, String targetFolder) throws IOException {
        BufferedInputStream entree;
        BufferedOutputStream sortie;
        String message = "";

        File upload = new File(targetFolder);

        if (!upload.exists()) {
            upload.mkdirs();
            message = "Le fichier "+targetFolder+" a été créé.";
        }
        entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
        sortie = new BufferedOutputStream(new FileOutputStream(new File(targetFolder + nomFichier)), TAILLE_TAMPON);

        byte[] tampon = new byte[TAILLE_TAMPON];
        int longueur;
        while ((longueur = entree.read(tampon)) > 0) {
            sortie.write(tampon, 0, longueur);
        }
        try {
            sortie.close();
        } catch (IOException ignore) {
        }
        try {
            entree.close();
        } catch (IOException ignore) {
        }
        return message;
    }

    /**
     * recupere et formate le nom d'un fichier
     * @param part
     * @return
     */
    public static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }
}
